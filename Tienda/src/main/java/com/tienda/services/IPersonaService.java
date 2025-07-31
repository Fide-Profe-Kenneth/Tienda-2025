/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.services;

import com.tienda.entities.Persona;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Usuario
 */
public interface IPersonaService {
    //Esta lista va a guardar objectos de tipo persona
    public List<Persona> findAll();
    
    //vamos a obtener a una persona de la base de datos con el id
    public Optional<Persona> getById ( long id);
    //guardar una nueva fila en la base de datos
    public Persona save(Persona id);
    //elimina una fila de la base de datos
    public void delete(long id);   
    public Persona findByNombre(String nombre);
}