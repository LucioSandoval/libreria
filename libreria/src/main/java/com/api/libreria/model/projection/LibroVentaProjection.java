package com.api.libreria.model.projection;

import org.springframework.beans.factory.annotation.Value;

public interface LibroVentaProjection {
    @Value("#{target.libro.titulo}")
    String getTitulo();

    Integer getCantidad();

    Float getSubTotal();

}
