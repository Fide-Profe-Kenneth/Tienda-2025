/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.repositories;

import com.tienda.entities.Persona;
import org.springframework.data.repository.CrudRepository;


/*

Interface (tipo de clase que define metodos, pero no como se implementan) 
Extends : Heredar (herencia)
Clase Generica : 

*/

public interface PersonaRepository extends CrudRepository<Persona, Long> {
     Persona findByNombre(String nombre); // Este método hace la magia
}