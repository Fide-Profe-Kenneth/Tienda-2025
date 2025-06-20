/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.repositories;

/**
 *
 * @author Usuario
 */
import com.tienda.entities.Producto;
import org.springframework.data.repository.CrudRepository;


/*

Interface (tipo de clase que define metodos, pero no como se implementan) 
Extends : Heredar (herencia)
Clase Generica : 


*/
public interface ProductoRepository extends CrudRepository<Producto, Long> {
}