package com.deloitte.mindmeet.controller;

import com.deloitte.mindmeet.dto.MeetingDTO;
import com.deloitte.mindmeet.dto.MeetingRequest;
import com.deloitte.mindmeet.service.MeetingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Controlador REST para gestión de reuniones
 * Maneja operaciones CRUD y funcionalidades de grabación
 * 
 * Endpoints principales:
 * - POST /api/meetings - Crear nueva reunión
 * - GET /api/meetings - Listar reuniones
 * - GET /api/meetings/{id} - Obtener detalles de reunión
 * - PUT /api/meetings/{id} - Actualizar reunión
 * - DELETE /api/meetings/{id} - Eliminar reunión
 * - POST /api/meetings/{id}/start - Iniciar reunión
 * - POST /api/meetings/{id}/finish - Finalizar reunión
 * 
 * @author MindMeet Team
 * @version 1.0
 */
@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Reuniones", description = "API de gestión de reuniones")
@SecurityRequirement(name = "Bearer Authentication")
public class MeetingController {

    private final MeetingService meetingService;

    /**
     * Crea una nueva reunión
     * 
     * @param request Datos de la reunión a crear
     * @param authentication Información del usuario autenticado
     * @return ResponseEntity con la reunión creada
     */
    @PostMapping
    @Operation(summary = "Crear reunión", description = "Crea una nueva reunión en el sistema")
    public ResponseEntity<MeetingDTO> createMeeting(
            @Valid @RequestBody MeetingRequest request,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName();
            MeetingDTO meeting = meetingService.createMeeting(request, userEmail);
            return ResponseEntity.status(HttpStatus.CREATED).body(meeting);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Obtiene todas las reuniones del usuario autenticado
     * Soporta paginación y filtros
     * 
     * @param pageable Configuración de paginación
     * @param authentication Información del usuario autenticado
     * @return ResponseEntity con página de reuniones
     */
    @GetMapping
    @Operation(summary = "Listar reuniones", description = "Obtiene todas las reuniones del usuario")
    public ResponseEntity<Page<MeetingDTO>> getUserMeetings(
            Pageable pageable,
            Authentication authentication) {
        String userEmail = authentication.getName();
        Page<MeetingDTO> meetings = meetingService.getUserMeetings(userEmail, pageable);
        return ResponseEntity.ok(meetings);
    }

    /**
     * Obtiene los detalles de una reunión específica
     * 
     * @param id ID de la reunión
     * @param authentication Información del usuario autenticado
     * @return ResponseEntity con los detalles de la reunión
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener reunión", description = "Obtiene los detalles de una reunión específica")
    public ResponseEntity<MeetingDTO> getMeetingById(
            @PathVariable Long id,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName();
            MeetingDTO meeting = meetingService.getMeetingById(id, userEmail);
            return ResponseEntity.ok(meeting);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Actualiza una reunión existente
     * 
     * @param id ID de la reunión
     * @param request Nuevos datos de la reunión
     * @param authentication Información del usuario autenticado
     * @return ResponseEntity con la reunión actualizada
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar reunión", description = "Actualiza los datos de una reunión")
    public ResponseEntity<MeetingDTO> updateMeeting(
            @PathVariable Long id,
            @Valid @RequestBody MeetingRequest request,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName();
            MeetingDTO meeting = meetingService.updateMeeting(id, request, userEmail);
            return ResponseEntity.ok(meeting);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Elimina una reunión
     * 
     * @param id ID de la reunión
     * @param authentication Información del usuario autenticado
     * @return ResponseEntity sin contenido
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reunión", description = "Elimina una reunión del sistema")
    public ResponseEntity<Void> deleteMeeting(
            @PathVariable Long id,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName();
            meetingService.deleteMeeting(id, userEmail);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Inicia una reunión programada
     * Cambia el estado a IN_PROGRESS
     * 
     * @param id ID de la reunión
     * @param authentication Información del usuario autenticado
     * @return ResponseEntity con la reunión actualizada
     */
    @PostMapping("/{id}/start")
    @Operation(summary = "Iniciar reunión", description = "Inicia una reunión y comienza la grabación")
    public ResponseEntity<MeetingDTO> startMeeting(
            @PathVariable Long id,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName();
            MeetingDTO meeting = meetingService.startMeeting(id, userEmail);
            return ResponseEntity.ok(meeting);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Finaliza una reunión en progreso
     * Cambia el estado a COMPLETED y calcula duración
     * 
     * @param id ID de la reunión
     * @param authentication Información del usuario autenticado
     * @return ResponseEntity con la reunión actualizada
     */
    @PostMapping("/{id}/finish")
    @Operation(summary = "Finalizar reunión", description = "Finaliza una reunión y detiene la grabación")
    public ResponseEntity<MeetingDTO> finishMeeting(
            @PathVariable Long id,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName();
            MeetingDTO meeting = meetingService.finishMeeting(id, userEmail);
            return ResponseEntity.ok(meeting);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Sube el archivo de grabación de audio/video
     * 
     * @param id ID de la reunión
     * @param file Archivo de grabación
     * @param authentication Información del usuario autenticado
     * @return ResponseEntity con URL de la grabación
     */
    @PostMapping("/{id}/upload-recording")
    @Operation(summary = "Subir grabación", description = "Sube el archivo de grabación de la reunión")
    public ResponseEntity<String> uploadRecording(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName();
            String recordingUrl = meetingService.uploadRecording(id, file, userEmail);
            return ResponseEntity.ok(recordingUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al subir grabación");
        }
    }

    /**
     * Obtiene las reuniones recientes del usuario
     * Retorna las últimas 5 reuniones
     * 
     * @param authentication Información del usuario autenticado
     * @return ResponseEntity con lista de reuniones recientes
     */
    @GetMapping("/recent")
    @Operation(summary = "Reuniones recientes", description = "Obtiene las reuniones más recientes del usuario")
    public ResponseEntity<List<MeetingDTO>> getRecentMeetings(Authentication authentication) {
        String userEmail = authentication.getName();
        List<MeetingDTO> meetings = meetingService.getRecentMeetings(userEmail, 5);
        return ResponseEntity.ok(meetings);
    }

    /**
     * Obtiene estadísticas de reuniones del usuario
     * 
     * @param authentication Información del usuario autenticado
     * @return ResponseEntity con estadísticas
     */
    @GetMapping("/stats")
    @Operation(summary = "Estadísticas", description = "Obtiene estadísticas de reuniones del usuario")
    public ResponseEntity<?> getMeetingStats(Authentication authentication) {
        String userEmail = authentication.getName();
        var stats = meetingService.getUserMeetingStats(userEmail);
        return ResponseEntity.ok(stats);
    }

    /**
     * Agrega un participante a la reunión
     * 
     * @param id ID de la reunión
     * @param participantEmail Email del participante
     * @param authentication Información del usuario autenticado
     * @return ResponseEntity con la reunión actualizada
     */
    @PostMapping("/{id}/participants")
    @Operation(summary = "Agregar participante", description = "Agrega un participante a la reunión")
    public ResponseEntity<MeetingDTO> addParticipant(
            @PathVariable Long id,
            @RequestParam String participantEmail,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName();
            MeetingDTO meeting = meetingService.addParticipant(id, participantEmail, userEmail);
            return ResponseEntity.ok(meeting);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
