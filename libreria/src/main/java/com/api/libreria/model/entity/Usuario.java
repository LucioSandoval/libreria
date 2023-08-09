package com.api.libreria.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "rut", nullable = false)
    private String rut;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "correo", nullable = false)
    private String correo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "usuario_role", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
            @JoinColumn(name = "rol_id") })
    private Set<Rol> roles;

}
