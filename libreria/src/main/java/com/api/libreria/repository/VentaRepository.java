package com.api.libreria.repository;

import com.api.libreria.model.entity.Venta;
import com.api.libreria.model.projection.DetalleVentaProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long > {
    List<DetalleVentaProjection> findBy();
}
