/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.entities;

/**
 *
 * @author Usuario
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "productos")   /*tablas plural, clase singular*/
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*Id es un primaryKey, es autoincremental*/
    private Long id;
    @NotBlank
    private String nombre;
    @Min(value = 100, message = "El precio es obligatorio")
    @NotNull(message = "El precio no puede ser nulo")
    private Double precio;

    public Producto() {
        // Constructor vacío necesario para JPA
    }

    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}