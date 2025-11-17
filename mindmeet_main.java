package com.deloitte.mindmeet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Clase principal de la aplicación MindMeet
 * Sistema de Reuniones con Inteligencia Artificial
 * 
 * @author MindMeet Team - Deloitte Colombia
 * @version 1.0
 * @since 2025-03-15
 */
@SpringBootApplication
public class MindMeetApplication {

    /**
     * Método principal que inicia la aplicación Spring Boot
     * 
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(MindMeetApplication.class, args);
        System.out.println("===============================================");
        System.out.println("MindMeet - Sistema de Reuniones con IA");
        System.out.println("Aplicación iniciada correctamente");
        System.out.println("===============================================");
    }

    /**
     * Bean para encriptación de contraseñas
     * Utiliza BCrypt para seguridad robusta
     * 
     * @return PasswordEncoder configurado
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
