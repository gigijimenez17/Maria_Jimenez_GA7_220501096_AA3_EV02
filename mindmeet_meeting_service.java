package com.deloitte.mindmeet.service;

import com.deloitte.mindmeet.dto.MeetingDTO;
import com.deloitte.mindmeet.dto.MeetingRequest;
import com.deloitte.mindmeet.dto.MeetingStatsDTO;
import com.deloitte.mindmeet.model.Meeting;
import com.deloitte.mindmeet.model.User;
import com.deloitte.mindmeet.repository.MeetingRepository;
import com.deloitte.mindmeet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio de gestión de reuniones
 * Implementa la lógica de negocio para operaciones CRUD y funcionalidades de reuniones
 * 
 * @author MindMeet Team
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;
    private final TranscriptionService transcriptionService;
    private final NotificationService notificationService;

    /**
     * Crea una nueva reunión
     * 
     * @param request Datos de la reunión
     * @param userEmail Email del usuario organizador
     * @return DTO de la reunión creada
     * @throws IllegalArgumentException si el usuario no existe
     */
    public MeetingDTO createMeeting(MeetingRequest request, String userEmail) {
        log.info("Creando nueva reunión: {} por usuario: {}", request.getTitle(), userEmail);

        // Buscar organizador
        User organizer = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Crear entidad Meeting
        Meeting meeting = new Meeting();
        meeting.setTitle(request.getTitle());
        meeting.setDescription(request.getDescription());
        meeting.setStartTime(request.getStartTime() != null ? 
            request.getStartTime() : LocalDateTime.now());
        meeting.setOrganizer(organizer);
        
        // Agregar participantes si existen
        if (request.getParticipantEmails() != null) {
            for (String participantEmail : request.getParticipantEmails()) {
                userRepository.findByEmail(participantEmail)
                    .ifPresent(meeting::addParticipant);
            }
        }

        // Guardar reunión
        meeting = meetingRepository.save(meeting);

        log.info("Reunión creada exitosamente con ID: {}", meeting.getId());

        // Enviar notificaciones a participantes (asíncrono)
        notificationService.notifyMeetingCreated(meeting);

        return convertToDTO(meeting);
    }

    /**
     * Obtiene todas las reuniones de un usuario con paginación
     * 
     * @param userEmail Email del usuario
     * @param pageable Configuración de paginación
     * @return Página de reuniones
     */
    @Transactional(readOnly = true)
    public Page<MeetingDTO> getUserMeetings(String userEmail, Pageable pageable) {
        log.info("Obteniendo reuniones del usuario: {}", userEmail);

        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Buscar reuniones donde el usuario es organizador o participante
        Page<Meeting> meetings = meetingRepository
            .findByOrganizerOrParticipants(user, user, pageable);

        return meetings.map(this::convertToDTO);
    }

    /**
     * Obtiene una reunión por ID
     * 
     * @param id ID de la reunión
     * @param userEmail Email del usuario
     * @return DTO de la reunión
     * @throws IllegalArgumentException si la reunión no existe o el usuario no tiene acceso
     */
    @Transactional(readOnly = true)
    public MeetingDTO getMeetingById(Long id, String userEmail) {
        log.info("Obteniendo reunión ID: {} para usuario: {}", id, userEmail);

        Meeting meeting = meetingRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Reunión no encontrada"));

        // Verificar que el usuario tiene acceso
        if (!hasAccessToMeeting(meeting, userEmail)) {
            throw new IllegalArgumentException("No tienes acceso a esta reunión");
        }

        return convertToDTO(meeting);
    }

    /**
     * Actualiza una reunión existente
     * 
     * @param id ID de la reunión
     * @param request Nuevos datos
     * @param userEmail Email del usuario
     * @return DTO de la reunión actualizada
     */
    public MeetingDTO updateMeeting(Long id, MeetingRequest request, String userEmail) {
        log.info("Actualizando reunión ID: {}", id);

        Meeting meeting = meetingRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Reunión no encontrada"));

        // Verificar que el usuario es el organizador
        if (!meeting.getOrganizer().getEmail().equals(userEmail)) {
            throw new IllegalArgumentException("Solo el organizador puede modificar la reunión");
        }

        // Actualizar campos
        if (request.getTitle() != null) {
            meeting.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            meeting.setDescription(request.getDescription());
        }
        if (request.getStartTime() != null) {
            meeting.setStartTime(request.getStartTime());
        }

        meeting = meetingRepository.save(meeting);

        log.info("Reunión actualizada exitosamente");

        return convertToDTO(meeting);
    }

    /**
     * Elimina una reunión
     * 
     * @param id ID de la reunión
     * @param userEmail Email del usuario
     */
    public void deleteMeeting(Long id, String userEmail) {
        log.info("Eliminando reunión ID: {}", id);

        Meeting meeting = meetingRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Reunión no encontrada"));

        // Verificar que el usuario es el organizador
        if (!meeting.getOrganizer().getEmail().equals(userEmail)) {
            throw new IllegalArgumentException("Solo el organizador puede eliminar la reunión");
        }

        meetingRepository.delete(meeting);

        log.info("Reunión eliminada exitosamente");
    }

    /**
     * Inicia una reunión
     * 
     * @param id ID de la reunión
     * @param userEmail Email del usuario
     * @return DTO de la reunión actualizada
     */
    public MeetingDTO startMeeting(Long id, String userEmail) {
        log.info("Iniciando reunión ID: {}", id);

        Meeting meeting = meetingRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Reunión no encontrada"));

        if (!meeting.getOrganizer().getEmail().equals(userEmail)) {
            throw new IllegalArgumentException("Solo el organizador puede iniciar la reunión");
        }

        meeting.start();
        meeting = meetingRepository.save(meeting);

        log.info("Reunión iniciada exitosamente");

        // Notificar a participantes
        notificationService.notifyMeetingStarted(meeting);

        return convertToDTO(meeting);
    }

    /**
     * Finaliza una reunión
     * 
     * @param id ID de la reunión
     * @param userEmail Email del usuario
     * @return DTO de la reunión actualizada
     */
    public MeetingDTO finishMeeting(Long id, String userEmail) {
        log.info("Finalizando reunión ID: {}", id);

        Meeting meeting = meetingRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Reunión no encontrada"));

        if (!meeting.getOrganizer().getEmail().equals(userEmail)) {
            throw new IllegalArgumentException("Solo el organizador puede finalizar la reunión");
        }

        meeting.finish();
        meeting = meetingRepository.save(meeting);

        log.info("Reunión finalizada. Duración: {} segundos", meeting.getDurationSeconds());

        // Iniciar procesamiento de transcripción si hay grabación
        if (meeting.getRecordingUrl() != null) {
            transcriptionService.processRecording(meeting.getId());
        }

        return convertToDTO(meeting);
    }

    /**
     * Sube el archivo de grabación
     * 
     * @param id ID de la reunión
     * @param file Archivo de grabación
     * @param userEmail Email del usuario
     * @return URL de la grabación almacenada
     */
    public String uploadRecording(Long id, MultipartFile file, String userEmail) {
        log.info("Subiendo grabación para reunión ID: {}", id);

        Meeting meeting = meetingRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Reunión no encontrada"));

        if (!meeting.getOrganizer().getEmail().equals(userEmail)) {
            throw new IllegalArgumentException("Solo el organizador puede subir grabaciones");
        }

        // Validar tipo de archivo
        String contentType = file.getContentType();
        if (contentType == null || 
            (!contentType.startsWith("audio/") && !contentType.startsWith("video/"))) {
            throw new IllegalArgumentException("Formato de archivo no válido");
        }

        // Guardar archivo
        String recordingUrl = fileStorageService.storeFile(file, "recordings");
        meeting.setRecordingUrl(recordingUrl);
        meetingRepository.save(meeting);

        log.info("Grabación subida exitosamente: {}", recordingUrl);

        // Iniciar transcripción automática
        transcriptionService.processRecording(meeting.getId());

        return recordingUrl;
    }

    /**
     * Obtiene las reuniones recientes del usuario
     * 
     * @param userEmail Email del usuario
     * @param limit Número máximo de reuniones
     * @return Lista de reuniones recientes
     */
    @Transactional(readOnly = true)
    public List<MeetingDTO> getRecentMeetings(String userEmail, int limit) {
        log.info("Obteniendo {} reuniones recientes del usuario: {}", limit, userEmail);

        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Pageable pageable = PageRequest.of(0, limit, 
            Sort.by(Sort.Direction.DESC, "createdAt"));

        return meetingRepository
            .findByOrganizerOrParticipants(user, user, pageable)
            .getContent()
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    /**
     * Obtiene estadísticas de reuniones del usuario
     * 
     * @param userEmail Email del usuario
     * @return DTO con estadísticas
     */
    @Transactional(readOnly = true)
    public MeetingStatsDTO getUserMeetingStats(String userEmail) {
        log.info("Obteniendo estadísticas para usuario: {}", userEmail);

        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Calcular estadísticas
        long totalMeetings = meetingRepository.countByOrganizer(user);
        long completedMeetings = meetingRepository.countByOrganizerAndStatus(
            user, MeetingStatus.COMPLETED);
        
        // Calcular promedio de precisión de transcripción
        Double avgAccuracy = meetingRepository.averageTranscriptionAccuracyByOrganizer(user);

        // Calcular tiempo total ahorrado (estimado: 15 min por reunión en actas manuales)
        int timeSavedHours = (int) (completedMeetings * 15 / 60);

        return new MeetingStatsDTO(
            totalMeetings,
            completedMeetings,
            avgAccuracy != null ? avgAccuracy : 0.0,
            timeSavedHours
        );
    }

    /**
     * Agrega un participante a la reunión
     * 
     * @param id ID de la reunión
     * @param participantEmail Email del participante
     * @param userEmail Email del usuario organizador
     * @return DTO de la reunión actualizada
     */
    public MeetingDTO addParticipant(Long id, String participantEmail, String userEmail) {
        log.info("Agregando participante {} a reunión ID: {}", participantEmail, id);

        Meeting meeting = meetingRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Reunión no encontrada"));

        if (!meeting.getOrganizer().getEmail().equals(userEmail)) {
            throw new IllegalArgumentException("Solo el organizador puede agregar participantes");
        }

        User participant = userRepository.findByEmail(participantEmail)
            .orElseThrow(() -> new IllegalArgumentException("Usuario participante no encontrado"));

        meeting.addParticipant(participant);
        meeting = meetingRepository.save(meeting);

        // Notificar al nuevo participante
        notificationService.notifyParticipantAdded(meeting, participant);

        return convertToDTO(meeting);
    }

    /**
     * Verifica si un usuario tiene acceso a una reunión
     * 
     * @param meeting Reunión a verificar
     * @param userEmail Email del usuario
     * @return true si tiene acceso, false en caso contrario
     */
    private boolean hasAccessToMeeting(Meeting meeting, String userEmail) {
        return meeting.getOrganizer().getEmail().equals(userEmail) ||
               meeting.getParticipants().stream()
                   .anyMatch(p -> p.getEmail().equals(userEmail));
    }

    /**
     * Convierte una entidad Meeting a DTO
     * 
     * @param meeting Entidad Meeting
     * @return DTO de la reunión
     */
    private MeetingDTO convertToDTO(Meeting meeting) {
        return new MeetingDTO(
            meeting.getId(),
            meeting.getTitle(),
            meeting.getDescription(),
            meeting.getStartTime(),
            meeting.getEndTime(),
            meeting.getDurationSeconds(),
            meeting.getStatus().toString(),
            meeting.getProcessingStatus().toString(),
            meeting.getRecordingUrl(),
            meeting.getTranscript(),
            meeting.getSummary(),
            meeting.getTranscriptionAccuracy(),
            meeting.getOrganizer().getFullName(),
            meeting.getOrganizer().getEmail(),
            meeting.getParticipants().size(),
            meeting.getCreatedAt(),
            meeting.getUpdatedAt()
        );
    }
}
