package com.tienda;

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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final AppAuthenticationSuccessHandler successHandler;

    @Autowired
    private UserService userService; // Ya anotado con @Service

    public SecurityConfig(AppAuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("Usando BCryptPasswordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authenticationProvider(authenticationProvider()) // <<--- Esto es clave
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login","/api/productos", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/persona", "/personasN","/personas/lista").hasRole("ADMIN")
                .requestMatchers("/personas", "/").hasAnyRole("USER", "VENDEDOR", "ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form

                .successHandler(successHandler)
                .permitAll()
            )
            .logout(logout -> logout.permitAll());

        return http.build();
    }
}