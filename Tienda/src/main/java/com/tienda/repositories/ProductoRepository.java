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

Interface :
Extends :
Clase Generica :

*/
public interface ProductoRepository extends CrudRepository<Producto, Long> {
}