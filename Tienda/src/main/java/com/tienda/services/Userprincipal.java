/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.services;

import com.tienda.entities.Persona;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Usuario
 */

/*UserDetails: Implementacion de SpringSecurity para el usuario
Userprincipal : Es el usuario loggeado/autenticado/ el usuario actual
*/

public class Userprincipal implements UserDetails {

    private Persona persona;

    public Userprincipal(Persona persona) {
        this.persona = persona;
    }

    /*GrantedAuthority: representar el rol o permiso que tiene el usuario
      Los roles son un tipo de GrantedAuthority que requiere el prefijo ROLE_
    */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("Roles en Persona: " + persona.getRoles());
        System.out.println("RoleList: " + persona.getRoleList());
        List<GrantedAuthority> authorities = new ArrayList<>();

        //roles
         this.persona.getRoleList().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" +r.trim());
            authorities.add(authority);
        });
        System.out.println("Authorities generadas: " + authorities);
        return authorities;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String getPassword() {return persona.getPassword();}

    @Override
    public String getUsername() {
        return persona.getNombre();
    }

    @Override
    public boolean isAccountNonExpired() {
       return true;
    }

    @Override
    public boolean isAccountNonLocked() {
           return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
          return true;
    }

    @Override
    public boolean isEnabled() {
        return this.persona.isEnabled();
    }

}
