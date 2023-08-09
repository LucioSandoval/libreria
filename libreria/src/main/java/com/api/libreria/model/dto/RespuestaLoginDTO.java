package com.api.libreria.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RespuestaLoginDTO {
    private Long id;

    private String nombre;

    private String apellido;

    private String rut;
    private String rol;
    private String token;
}
