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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        List<GrantedAuthority> authorities = new ArrayList<>();
        System.out.println("ENTRE2");
        //extract list of permissions (name)
        this.persona.getPermissionList().forEach(p -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add(authority);
        });
        //roles
         this.persona.getRoleList().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" +r);
            authorities.add(authority);
        });
        return authorities;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String getPassword() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "12345"; // tu contraseña en texto plano
        String hashedPassword = encoder.encode(rawPassword);

        System.out.println("Password en texto plano: " + rawPassword);
        System.out.println("Password hasheado (BCrypt): " + hashedPassword);    
        System.out.println("Password desde Userprincipal: " + persona.getPassword());
    
        System.out.println("¿Password coincide?: " + encoder.matches(rawPassword, persona.getPassword()));
        return persona.getPassword();
    }

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
