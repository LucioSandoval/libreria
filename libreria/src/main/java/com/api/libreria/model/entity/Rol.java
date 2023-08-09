package com.api.libreria.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rol_nombre")
    private String rolNombre;

}
