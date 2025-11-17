package com.deloitte.mindmeet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad que representa una reunión en el sistema MindMeet
 * Almacena información sobre reuniones, incluyendo grabaciones y transcripciones
 * 
 * @author MindMeet Team
 * @version 1.0
 */
@Entity
@Table(name = "meetings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {

    /**
     * Identificador único de la reunión (clave primaria)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título descriptivo de la reunión
     */
    @NotBlank(message = "El título de la reunión es obligatorio")
    @Column(nullable = false, length = 200)
    private String title;

    /**
     * Descripción o agenda de la reunión
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Fecha y hora de inicio de la reunión
     */
    @NotNull(message = "La fecha de inicio es obligatoria")
    @Column(nullable = false)
    private LocalDateTime startTime;

    /**
     * Fecha y hora de finalización de la reunión
     */
    private LocalDateTime endTime;

    /**
     * Duración de la reunión en segundos
     */
    private Integer durationSeconds;

    /**
     * Estado actual de la reunión
     * (SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED)
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MeetingStatus status = MeetingStatus.SCHEDULED;

    /**
     * Estado del procesamiento de IA
     * (PENDING, PROCESSING, COMPLETED, FAILED)
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ProcessingStatus processingStatus = ProcessingStatus.PENDING;

    /**
     * URL de la grabación de audio/video
     */
    @Column(length = 500)
    private String recordingUrl;

    /**
     * Transcripción generada por IA
     */
    @Column(columnDefinition = "TEXT")
    private String transcript;

    /**
     * Resumen generado por IA
     */
    @Column(columnDefinition = "TEXT")
    private String summary;

    /**
     * Porcentaje de precisión de la transcripción (0-100)
     */
    @Column(precision = 5, scale = 2)
    private Double transcriptionAccuracy;

    /**
     * Organizador de la reunión
     * Relación Many-to-One con User
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

    /**
     * Participantes de la reunión
     * Relación Many-to-Many con User
     */
    @ManyToMany
    @JoinTable(
        name = "meeting_participants",
        joinColumns = @JoinColumn(name = "meeting_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> participants = new HashSet<>();

    /**
     * Documentos generados asociados a la reunión
     * Relación One-to-Many con Document
     */
    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Document> documents = new HashSet<>();

    /**
     * Mapa mental asociado a la reunión
     * Relación One-to-One con MindMap
     */
    @OneToOne(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true)
    private MindMap mindMap;

    /**
     * Fecha y hora de creación del registro
     */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Fecha y hora de última actualización del registro
     */
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Constructor para crear una nueva reunión
     * 
     * @param title Título de la reunión
     * @param startTime Fecha y hora de inicio
     * @param organizer Usuario organizador
     */
    public Meeting(String title, LocalDateTime startTime, User organizer) {
        this.title = title;
        this.startTime = startTime;
        this.organizer = organizer;
        this.status = MeetingStatus.SCHEDULED;
        this.processingStatus = ProcessingStatus.PENDING;
    }

    /**
     * Agrega un participante a la reunión
     * 
     * @param participant Usuario participante
     */
    public void addParticipant(User participant) {
        this.participants.add(participant);
    }

    /**
     * Remueve un participante de la reunión
     * 
     * @param participant Usuario participante
     */
    public void removeParticipant(User participant) {
        this.participants.remove(participant);
    }

    /**
     * Calcula la duración de la reunión en segundos
     * 
     * @return Duración en segundos, o null si no ha finalizado
     */
    public Integer calculateDuration() {
        if (endTime != null && startTime != null) {
            return (int) java.time.Duration.between(startTime, endTime).getSeconds();
        }
        return null;
    }

    /**
     * Inicia la reunión estableciendo el estado y la hora de inicio
     */
    public void start() {
        this.status = MeetingStatus.IN_PROGRESS;
        if (this.startTime == null) {
            this.startTime = LocalDateTime.now();
        }
    }

    /**
     * Finaliza la reunión estableciendo el estado y calculando la duración
     */
    public void finish() {
        this.status = MeetingStatus.COMPLETED;
        this.endTime = LocalDateTime.now();
        this.durationSeconds = calculateDuration();
    }

    /**
     * Cancela la reunión
     */
    public void cancel() {
        this.status = MeetingStatus.CANCELLED;
    }
}

/**
 * Enumeración de estados de reunión
 */
enum MeetingStatus {
    SCHEDULED,      // Reunión programada
    IN_PROGRESS,    // Reunión en curso
    COMPLETED,      // Reunión completada
    CANCELLED       // Reunión cancelada
}

/**
 * Enumeración de estados de procesamiento de IA
 */
enum ProcessingStatus {
    PENDING,        // Pendiente de procesamiento
    PROCESSING,     // Procesamiento en curso
    COMPLETED,      // Procesamiento completado
    FAILED          // Procesamiento fallido
}
