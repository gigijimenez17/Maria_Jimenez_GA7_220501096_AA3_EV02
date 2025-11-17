package com.deloitte.mindmeet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad que representa un usuario del sistema MindMeet
 * Almacena información de autenticación y perfil del usuario
 * 
 * @author MindMeet Team
 * @version 1.0
 */
@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * Identificador único del usuario (clave primaria)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre completo del usuario
     * Validación: No puede estar vacío, mínimo 2 caracteres, máximo 100
     */
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String fullName;

    /**
     * Correo electrónico del usuario (único en el sistema)
     * Validación: Formato de email válido
     */
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    /**
     * Contraseña encriptada del usuario
     * Validación: Mínimo 8 caracteres
     */
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Column(nullable = false)
    private String password;

    /**
     * Estado de activación de la cuenta
     * true = cuenta activa, false = cuenta desactivada
     */
    @Column(nullable = false)
    private Boolean active = true;

    /**
     * Proveedor de autenticación (LOCAL, GOOGLE, GITHUB)
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AuthProvider provider = AuthProvider.LOCAL;

    /**
     * Roles asignados al usuario
     * Relación Many-to-Many con la entidad Role
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    /**
     * Reuniones creadas por el usuario
     * Relación One-to-Many con Meeting
     */
    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    private Set<Meeting> organizedMeetings = new HashSet<>();

    /**
     * Fecha y hora de creación del registro
     * Se establece automáticamente al crear el usuario
     */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Fecha y hora de última actualización del registro
     * Se actualiza automáticamente en cada modificación
     */
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Constructor para crear un nuevo usuario con datos básicos
     * 
     * @param fullName Nombre completo del usuario
     * @param email Correo electrónico
     * @param password Contraseña (será encriptada)
     */
    public User(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.active = true;
        this.provider = AuthProvider.LOCAL;
    }

    /**
     * Agrega un rol al usuario
     * 
     * @param role Rol a agregar
     */
    public void addRole(Role role) {
        this.roles.add(role);
    }

    /**
     * Remueve un rol del usuario
     * 
     * @param role Rol a remover
     */
    public void removeRole(Role role) {
        this.roles.remove(role);
    }
}

/**
 * Enumeración de proveedores de autenticación
 */
enum AuthProvider {
    LOCAL,      // Autenticación local con email/password
    GOOGLE,     // Autenticación con Google OAuth
    GITHUB      // Autenticación con GitHub OAuth
}
