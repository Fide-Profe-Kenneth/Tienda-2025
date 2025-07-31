/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.services;


import com.tienda.entities.Persona;
import com.tienda.repositories.PersonaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersonaService  {

    @Autowired
    private PersonaRepository personaRepository;
    @Override
    public List<Persona> findAll() {
        return (List<Persona>) this.personaRepository.findAll();
    }
    @Override
    public Persona save(Persona persona) {

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = persona.getPassword(); // tu contraseña en texto plano
        String hashedPassword = encoder.encode(rawPassword);
        persona.setPassword(hashedPassword);
        // Aqui se debe manejar la logica del tipo de usuario que se va agregar, si es ADMIN, USUARIO O VENDEDOR
        // Por ejemplo, por default lo seteo en ADMIN

        persona.setRoles("ADMIN");
        persona.setPermissions("ADMIN");


        return personaRepository.save(persona);
    }

    @Override
    public Optional<Persona> getById(long id) {
       return personaRepository.findById(id);}

    @Override
    public void delete(long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public Persona findByNombre(String nombre) {
        return personaRepository.findByNombre(nombre);
    }


}