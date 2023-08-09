package com.api.libreria.model.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "autor", nullable = false)
    private String autor;

    @Column(name = "precio", nullable = false)
    private Float precio;

    @Column(name = "stock", nullable = false)
    private Integer stock;


}
