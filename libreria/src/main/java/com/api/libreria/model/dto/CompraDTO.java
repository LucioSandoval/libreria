package com.api.libreria.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompraDTO {
    private Long idUsuario;
    private List<DetalleCompraDTO> detalleCompraDTO;
}
