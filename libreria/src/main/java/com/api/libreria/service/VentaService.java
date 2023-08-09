package com.api.libreria.service;

import com.api.libreria.model.dto.CompraDTO;
import com.api.libreria.model.projection.DetalleVentaProjection;

import java.util.List;

public interface VentaService {
    void realizarCompra(CompraDTO compraDTO);
    List<DetalleVentaProjection> detalleVenta();
}
