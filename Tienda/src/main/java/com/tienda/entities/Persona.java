package com.tienda.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String nombre;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String apellido;

    @NotBlank
    @Email
    @Column(length = 191, nullable = false, unique = true)
    private String email;

    @Column(length = 30)
    private String telefono;

    @Column(length = 255)
    private String direccion;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String password;

    /** 1 = activo/habilitado; 0 = deshabilitado */
    @Column(nullable = false)
    private int active = 1;

    /**
     * Roles separados por coma. Ej: "ADMIN,USER,VENDEDOR"
     * Recomendación: guardar SIN prefijo ROLE_.
     */
    @Column(length = 255)
    private String roles = "";

    /**
     * Permisos separados por coma. Ej: "producto.read,producto.write"
     */
    @Column(length = 500)
    private String permissions = "";

    /* ====== Getters / Setters ====== */

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getPassword() {
        System.out.println(this.password);
        System.out.println(password);
        return this.password; }
    public void setPassword(String password) { this.password = password; }

    public int getActive() { return active; }
    public void setActive(int active) { this.active = active; }

    public String getRoles() { return roles; }
    public void setRoles(String roles) { this.roles = roles != null ? roles : ""; }

    public String getPermissions() { return permissions; }
    public void setPermissions(String permissions) { this.permissions = permissions !=  null ? permissions : ""; }

    /* ====== Helpers ====== */

    /** Devuelve true si el usuario está habilitado. */
    @Transient
    public boolean isEnabled() {
        return this.active == 1;
    }

    /** Lista de roles normalizados (sin espacios, en MAYÚSCULAS). */
    @Transient
    public List<String> getRoleList() {
        if (roles == null || roles.isBlank()) return new ArrayList<>();
        return Arrays.stream(roles.split(",")) /*ROLE_ADMIN*/
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    /** Lista de permisos normalizados (sin espacios). */
    @Transient
    public List<String> getPermissionList() {
        if (permissions == null || permissions.isBlank()) return new ArrayList<>();
        return Arrays.stream(permissions.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    /* Métodos de conveniencia para agregar roles/permissions */

    public void addRole(String role) {
        if (role == null || role.isBlank()) return;
        List<String> list = getRoleList();
        String r = role.trim().toUpperCase();
        if (!list.contains(r)) {
            list.add(r);
            this.roles = String.join(",", list);
        }
    }

    public void addPermission(String permission) {
        if (permission == null || permission.isBlank()) return;
        List<String> list = getPermissionList();
        String p = permission.trim();
        if (!list.contains(p)) {
            list.add(p);
            this.permissions = String.join(",", list);
        }
    }
}
