package com.api.libreria.service;

import com.api.libreria.model.dto.VentaDTO;
import com.api.libreria.model.projection.DetalleVentaProjection;

import java.util.List;

public interface VentaService {
    void crearVenta(VentaDTO compraDTO);
    List<DetalleVentaProjection> detalleVenta();
}
