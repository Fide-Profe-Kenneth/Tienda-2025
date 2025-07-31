package com.tienda; // Paquete del proyecto

// Importaciones necesarias para manejar la autenticación y redirección
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
/*
 * Esta clase es un componente de Spring que se ejecuta automáticamente
 * después de una autenticación exitosa.
 * Permite personalizar el comportamiento post-login, como redirigir
 * al usuario a diferentes páginas según su rol.
 */
public class AppAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        // Obtiene los roles (autoridades) del usuario autenticado
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Valor por defecto para redirección después del login
        String redirectUrl = "/"; 
        
        // Recorre los roles del usuario y decide la URL de redirección
        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority(); // Ej: "ROLE_ADMIN", "ROLE_USER", etc.

            // Redirige según el rol
            if (role.equals("ROLE_ADMIN")) {
                redirectUrl = "/home"; // Admin va al home de administración
                break;
            } else if (role.equals("ROLE_VENDEDOR")) {
                redirectUrl = "/ventas"; // Vendedor va a su módulo de ventas
                break;
            } else if (role.equals("ROLE_USER")) {
                redirectUrl = "/home"; // Usuario normal va al home general
                break;
            }
        }

        // Redirige al usuario a la URL determinada
        response.sendRedirect(redirectUrl);
    }
}
