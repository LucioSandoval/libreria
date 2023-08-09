package com.api.libreria.mapper;

import com.api.libreria.model.dto.RespuestaLoginDTO;
import com.api.libreria.model.dto.UsuarioDTO;
import com.api.libreria.model.entity.Usuario;

import java.util.stream.Collectors;

public class MapearEntidadADTO {
    public static UsuarioDTO mapearUsuarioADTO(Usuario usuario) {
        if(usuario == null){
            throw new IllegalStateException("El objeto usuario no puede ser nulo.");
        }
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .rut(usuario.getRut())
                .correo(usuario.getCorreo())
                .contrasena(usuario.getContrasena())
                .rol(usuario.getRoles().stream().map(rol -> rol.getRolNombre()).collect(Collectors.joining()))
                .build();
    }

    public static RespuestaLoginDTO mapearRespuestaLoginADTO(Usuario usuario, String token) {
        return RespuestaLoginDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .rut(usuario.getRut())
                .rol(usuario.getRoles().stream().map(rol -> rol.getRolNombre()).collect(Collectors.joining()))
                .token(token)
                .build();
    }
}
