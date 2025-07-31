package com.tienda; // Paquete del proyecto

// Importaciones necesarias para configuración de seguridad
import com.tienda.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Clase de configuración
@EnableWebSecurity // Habilita la seguridad web de Spring Security
public class SecurityConfig {

    // Manejador personalizado que se ejecuta cuando el login es exitoso
    private final AppAuthenticationSuccessHandler successHandler;

    // Servicio que implementa UserDetailsService para cargar usuarios desde la base de datos
    @Autowired
    private UserService userService;

    // Inyección del manejador de éxito en el constructor
    public SecurityConfig(AppAuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    // Bean que define el codificador de contraseñas. En este caso, se usa BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("Usando BCryptPasswordEncoder");
        return new BCryptPasswordEncoder();
    }

    // Proveedor de autenticación que utiliza el servicio de usuarios y el codificador de contraseñas
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService); // Establece el servicio que carga usuarios
        authProvider.setPasswordEncoder(passwordEncoder()); // Establece el encoder para validar contraseñas
        return authProvider;
    }

    // Configura las reglas de seguridad para las rutas HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authenticationProvider(authenticationProvider()) // Se usa el proveedor de autenticación configurado
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas (no requieren autenticación)
                .requestMatchers("/","/login", "/home", "/api/productos", "/css/**", "/js/**", "/images/**").permitAll()

                // Rutas que requieren rol ADMIN
                .requestMatchers("/persona", "/personas/nuevo", "/personas/lista").hasRole("ADMIN")

                // Rutas accesibles por USER, VENDEDOR o ADMIN
                .requestMatchers("/personas", "/").hasAnyRole("USER", "VENDEDOR", "ADMIN")

                // Cualquier otra solicitud requiere autenticación
                .anyRequest().authenticated()
            )
            // Configuración del formulario de login
            .formLogin(form -> form
                .loginPage("/login")
                .successHandler(successHandler) // Se ejecuta cuando el login es exitoso
                .permitAll()
            )
            // Configuración del logout
            .logout(logout -> logout
                .logoutUrl("/logout") // Ruta para cerrar sesión
                .logoutSuccessUrl("/home") // Redirige al login después del logout
                .permitAll()
            );

        return http.build(); // Devuelve el objeto HttpSecurity ya configurado
    }
}
