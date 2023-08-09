package com.api.libreria.model.projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;

public interface DetalleVentaProjection {
    Long getId();

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    LocalDateTime getFechaCreacion();

    @Value("#{target.usuario.nombre}" +" "+ "#{target.usuario.apellido}")
    String getNombreCompleto();


    List<LibroVentaProjection> getLibrosVentas();

    Float getMontoNeto();

    Float getIva();

    Float getTotalVenta();


}
