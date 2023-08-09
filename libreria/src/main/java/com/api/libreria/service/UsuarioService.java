package com.api.libreria.service;

import com.api.libreria.model.dto.LoginDTO;
import com.api.libreria.model.dto.RespuestaLoginDTO;
import com.api.libreria.model.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    RespuestaLoginDTO login(LoginDTO loginDTO);
    UsuarioDTO crearUsuario(UsuarioDTO clienteDto);
    List<UsuarioDTO> listarUsuarios();
}
