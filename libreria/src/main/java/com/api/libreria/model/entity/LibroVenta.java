package com.api.libreria.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "libro_venta")
public class LibroVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_libro", nullable = false)
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta venta;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "subTotal", nullable = false)
    private Float subTotal;

}
