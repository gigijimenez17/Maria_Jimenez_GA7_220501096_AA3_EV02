package com.deloitte.mindmeet.service;

import com.deloitte.mindmeet.dto.AuthRequest;
import com.deloitte.mindmeet.dto.AuthResponse;
import com.deloitte.mindmeet.dto.RegisterRequest;
import com.deloitte.mindmeet.model.User;
import com.deloitte.mindmeet.model.Role;
import com.deloitte.mindmeet.repository.UserRepository;
import com.deloitte.mindmeet.repository.RoleRepository;
import com.deloitte.mindmeet.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Servicio de autenticación y gestión de usuarios
 * Implementa la lógica de negocio para login, registro y recuperación de contraseña
 * 
 * @author MindMeet Team
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;

    /**
     * Autentica un usuario con email y contraseña
     * 
     * @param request Objeto con credenciales del usuario
     * @return AuthResponse con token JWT y datos del usuario
     * @throws IllegalArgumentException si las credenciales son inválidas
     */
    public AuthResponse authenticateUser(AuthRequest request) {
        log.info("Intentando autenticar usuario: {}", request.getEmail());

        try {
            // Autenticar usuario con Spring Security
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
                )
            );

            // Establecer autenticación en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Buscar usuario en la base de datos
            User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

            // Verificar si el usuario está activo
            if (!user.getActive()) {
                throw new IllegalArgumentException("Usuario desactivado");
            }

            // Generar token JWT
            String token = jwtTokenProvider.generateToken(authentication);

            log.info("Usuario autenticado exitosamente: {}", request.getEmail());

            return new AuthResponse(
                token,
                "Autenticación exitosa",
                true,
                user.getId(),
                user.getFullName(),
                user.getEmail()
            );

        } catch (Exception e) {
            log.error("Error en autenticación: {}", e.getMessage());
            throw new IllegalArgumentException("Credenciales inválidas");
        }
    }

    /**
     * Registra un nuevo usuario en el sistema
     * 
     * @param request Objeto con datos del nuevo usuario
     * @return AuthResponse con token JWT y datos del usuario creado
     * @throws IllegalArgumentException si el email ya existe
     */
    public AuthResponse registerUser(RegisterRequest request) {
        log.info("Intentando registrar nuevo usuario: {}", request.getEmail());

        // Verificar si el email ya existe
        if (userRepository.existsByEmail(request.getEmail())) {
            log.warn("Intento de registro con email existente: {}", request.getEmail());
            throw new IllegalArgumentException("El email ya está registrado");
        }

        // Validar longitud de contraseña
        if (request.getPassword().length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }

        // Crear nuevo usuario
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(true);

        // Asignar rol por defecto (USER)
        Role userRole = roleRepository.findByName("ROLE_USER")
            .orElseGet(() -> {
                Role newRole = new Role();
                newRole.setName("ROLE_USER");
                return roleRepository.save(newRole);
            });
        
        user.addRole(userRole);

        // Guardar usuario
        user = userRepository.save(user);

        log.info("Usuario registrado exitosamente: {}", user.getEmail());

        // Generar token JWT automáticamente
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            user.getEmail(),
            request.getPassword()
        );
        String token = jwtTokenProvider.generateToken(authentication);

        // Enviar email de bienvenida (asíncrono)
        try {
            emailService.sendWelcomeEmail(user.getEmail(), user.getFullName());
        } catch (Exception e) {
            log.error("Error al enviar email de bienvenida: {}", e.getMessage());
        }

        return new AuthResponse(
            token,
            "Usuario registrado exitosamente",
            true,
            user.getId(),
            user.getFullName(),
            user.getEmail()
        );
    }

    /**
     * Envía email de recuperación de contraseña
     * 
     * @param email Email del usuario
     * @throws IllegalArgumentException si el usuario no existe
     */
    public void sendPasswordResetEmail(String email) {
        log.info("Solicitando recuperación de contraseña para: {}", email);

        // Buscar usuario
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Generar token de recuperación (válido por 1 hora)
        String resetToken = jwtTokenProvider.generatePasswordResetToken(email);

        // Enviar email con enlace de recuperación
        try {
            emailService.sendPasswordResetEmail(email, user.getFullName(), resetToken);
            log.info("Email de recuperación enviado a: {}", email);
        } catch (Exception e) {
            log.error("Error al enviar email de recuperación: {}", e.getMessage());
            throw new RuntimeException("Error al enviar email de recuperación");
        }
    }

    /**
     * Restablece la contraseña del usuario
     * 
     * @param token Token de recuperación
     * @param newPassword Nueva contraseña
     * @throws IllegalArgumentException si el token es inválido
     */
    public void resetPassword(String token, String newPassword) {
        log.info("Intentando restablecer contraseña");

        // Validar token
        if (!jwtTokenProvider.validateToken(token)) {
            throw new IllegalArgumentException("Token inválido o expirado");
        }

        // Obtener email del token
        String email = jwtTokenProvider.getEmailFromToken(token);

        // Buscar usuario
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Validar nueva contraseña
        if (newPassword.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }

        // Actualizar contraseña
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        log.info("Contraseña restablecida exitosamente para: {}", email);
    }

    /**
     * Valida un token JWT
     * 
     * @param token Token JWT a validar
     * @return true si el token es válido, false en caso contrario
     */
    public boolean validateToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }

    /**
     * Autentica usuario con proveedor externo (Google, GitHub)
     * 
     * @param provider Nombre del proveedor
     * @param token Token de autenticación del proveedor
     * @return AuthResponse con token JWT
     */
    public AuthResponse authenticateWithProvider(String provider, String token) {
        log.info("Autenticación social con proveedor: {}", provider);

        // Validar token del proveedor y obtener información del usuario
        // Esta implementación dependerá del proveedor específico
        
        // Por ahora, retornamos una respuesta genérica
        // En producción, aquí se integraría con la API del proveedor
        
        throw new UnsupportedOperationException(
            "Autenticación social en desarrollo para: " + provider
        );
    }

    /**
     * Obtiene un usuario por email
     * 
     * @param email Email del usuario
     * @return Optional con el usuario si existe
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Cambia el estado de activación de un usuario
     * 
     * @param userId ID del usuario
     * @param active Nuevo estado de activación
     */
    public void setUserActive(Long userId, boolean active) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        
        user.setActive(active);
        userRepository.save(user);

        log.info("Usuario {} {}", user.getEmail(), active ? "activado" : "desactivado");
    }
}
