package com.tienda; // Define el paquete en el que se encuentra esta clase

// Importaciones necesarias para la configuración de Spring y Thymeleaf
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;


/*Este archivo de configuración le dice a Spring que debe incluir el dialecto 
de seguridad de Thymeleaf (SpringSecurityDialect). Esto permite usar etiquetas 
como sec:authorize en las vistas (html) Thymeleaf, lo que es útil para mostrar u ocultar 
contenido según los roles o permisos del usuario autenticado.*/

/**
 *
 * @author Usuario
 */

@Configuration // Indica que esta clase contiene definiciones de beans para el contexto de Spring
public class ThymeleafConfig {

    // Define un bean de tipo SpringSecurityDialect para habilitar el uso de expresiones de seguridad en las plantillas Thymeleaf
    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}
