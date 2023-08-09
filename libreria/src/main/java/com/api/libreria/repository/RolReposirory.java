package com.api.libreria.repository;

import com.api.libreria.model.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RolReposirory extends JpaRepository<Rol,Long>{
        Optional<Rol> findByRolNombre(String rol);

}
