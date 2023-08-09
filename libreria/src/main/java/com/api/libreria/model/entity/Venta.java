package com.api.libreria.model.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha_creacion")
    @CreatedDate
    private LocalDateTime fechaCreacion;

    @Column(name = "monto_neto",nullable = false)
    private Float montoNeto;

    @Column(name = "iva",nullable = false)
    private Float iva;

    @Column(name = "total_venta", nullable = false)
    private Float totalVenta;

    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LibroVenta> librosVentas = new ArrayList<>();

    @PrePersist
    public void prePersist(){this.fechaCreacion = LocalDateTime.now();}


}
