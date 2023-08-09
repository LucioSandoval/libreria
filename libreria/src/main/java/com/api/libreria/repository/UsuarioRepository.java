package com.api.libreria.repository;

import com.api.libreria.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Boolean existsByRut(String rut);

    Boolean existsByCorreo(String correo);

    Optional<Usuario> findByRut(String rut);
}

