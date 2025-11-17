package com.deloitte.mindmeet.controller;

import com.deloitte.mindmeet.dto.AuthRequest;
import com.deloitte.mindmeet.dto.AuthResponse;
import com.deloitte.mindmeet.dto.RegisterRequest;
import com.deloitte.mindmeet.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para operaciones de autenticación
 * Gestiona login, registro y recuperación de contraseña
 * 
 * Endpoints:
 * - POST /api/auth/login - Iniciar sesión
 * - POST /api/auth/register - Registrar nuevo usuario
 * - POST /api/auth/forgot-password - Recuperar contraseña
 * 
 * @author MindMeet Team
 * @version 1.0
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Autenticación", description = "API de autenticación y registro de usuarios")
public class AuthController {

    private final AuthService authService;

    /**
     * Endpoint para iniciar sesión
     * Valida credenciales y retorna token JWT
     * 
     * @param request Objeto con email y contraseña
     * @return ResponseEntity con token JWT y datos del usuario
     */
    @PostMapping("/login")
    @Operation(
        summary = "Iniciar sesión",
        description = "Autentica un usuario con email y contraseña"
    )
    @ApiResponse(responseCode = "200", description = "Login exitoso")
    @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        try {
            AuthResponse response = authService.authenticateUser(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new AuthResponse(null, "Credenciales inválidas", false));
        }
    }

    /**
     * Endpoint para registrar un nuevo usuario
     * Crea una cuenta nueva en el sistema
     * 
     * @param request Objeto con datos del nuevo usuario
     * @return ResponseEntity con token JWT y datos del usuario creado
     */
    @PostMapping("/register")
    @Operation(
        summary = "Registrar usuario",
        description = "Crea una nueva cuenta de usuario en el sistema"
    )
    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos o email ya existe")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        try {
            AuthResponse response = authService.registerUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new AuthResponse(null, e.getMessage(), false));
        }
    }

    /**
     * Endpoint para recuperar contraseña
     * Envía un email con enlace de recuperación
     * 
     * @param email Correo electrónico del usuario
     * @return ResponseEntity con mensaje de confirmación
     */
    @PostMapping("/forgot-password")
    @Operation(
        summary = "Recuperar contraseña",
        description = "Envía un email con enlace para restablecer la contraseña"
    )
    @ApiResponse(responseCode = "200", description = "Email enviado exitosamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        try {
            authService.sendPasswordResetEmail(email);
            return ResponseEntity.ok("Enlace de recuperación enviado al email");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Usuario no encontrado");
        }
    }

    /**
     * Endpoint para validar token JWT
     * Verifica si un token es válido y no ha expirado
     * 
     * @param token Token JWT a validar
     * @return ResponseEntity con resultado de la validación
     */
    @GetMapping("/validate-token")
    @Operation(
        summary = "Validar token",
        description = "Verifica la validez de un token JWT"
    )
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        try {
            boolean isValid = authService.validateToken(token.replace("Bearer ", ""));
            return ResponseEntity.ok(isValid);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

    /**
     * Endpoint para login con proveedores externos (Google, GitHub)
     * 
     * @param provider Nombre del proveedor (google, github)
     * @param token Token de autenticación del proveedor
     * @return ResponseEntity con token JWT
     */
    @PostMapping("/social-login/{provider}")
    @Operation(
        summary = "Login social",
        description = "Autentica usuario con proveedor externo (Google, GitHub)"
    )
    public ResponseEntity<AuthResponse> socialLogin(
            @PathVariable String provider,
            @RequestParam String token) {
        try {
            AuthResponse response = authService.authenticateWithProvider(provider, token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new AuthResponse(null, "Error en autenticación social", false));
        }
    }
}
