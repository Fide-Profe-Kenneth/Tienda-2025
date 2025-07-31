/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.services;

import com.tienda.entities.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 *
 * @author Usuario
 */

/*
 * Servicio personalizado de Spring Security para cargar usuarios desde la base de datos
 * utilizando el nombre de usuario (en este caso, el campo "nombre" de Persona).
 */ 
@Service
public class UserService implements UserDetailsService{
    // Inyección de dependencia del servicio que maneja la lógica relacionada con "Persona"
    @Autowired
    public IPersonaService personaService;


    /**
     * Método que Spring Security llama durante el proceso de autenticación.
     * Busca una Persona por su nombre (username), y construye un UserDetails (Userprincipal).
     * 
     * @param username el nombre de usuario ingresado por el cliente (campo "nombre" de Persona)
     * @return un objeto que implementa UserDetails (necesario para autenticar)
     * @throws UsernameNotFoundException si no se encuentra la persona con ese nombre
     */    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar a la persona en la base de datos usando el nombre
        Persona persona = this.personaService.findByNombre(username);     
        // Si no se encuentra, lanzar excepción para que Spring maneje el error de login
        if (persona == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        // Crear y devolver un objeto UserDetails personalizado con los datos de Persona
        Userprincipal userPrincipal = new Userprincipal(persona);
        return userPrincipal;       
    } 
}