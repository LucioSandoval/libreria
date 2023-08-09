package com.api.libreria.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String rut;
    private String contrasena;
    private Long id;

}
