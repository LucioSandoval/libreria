package com.api.libreria.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDTO {

    private Long id;

    private String nombre;

    private String apellido;

    private String rut;

    private String contrasena;

    private String correo;

    private String rol;

}
