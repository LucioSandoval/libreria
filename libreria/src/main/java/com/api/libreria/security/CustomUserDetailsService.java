package com.api.libreria.security;

import com.api.libreria.model.entity.Rol;
import com.api.libreria.model.entity.Usuario;
import com.api.libreria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Método que crea un Usuario de tipo  UserDetails
     * @param rut contiene el rut del usuario
     * @return retorna un objeto de tipo UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String rut) {
        Usuario usuario = usuarioRepository.findByRut(rut)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado  con el rut: " + rut));
        return new User(usuario.getRut(), usuario.getContrasena(), mapearRoles(usuario.getRoles()));
    }

    /**
     * Método que mapea los roles de usuario
     * @param usuarioRoles contiene los roles de usuario
     * @return retorna  colección de objetos SimpleGrantedAuthority
     */
    private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> usuarioRoles){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        usuarioRoles.forEach(usuarioRole -> authorities.add(new SimpleGrantedAuthority("ROLE_" + usuarioRole.getRolNombre())));
        return authorities;
    }


}