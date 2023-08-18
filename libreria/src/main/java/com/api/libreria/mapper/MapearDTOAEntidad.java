package com.api.libreria.mapper;

import com.api.libreria.model.dto.RolDTO;
import com.api.libreria.model.dto.UsuarioDTO;
import com.api.libreria.model.entity.Rol;
import com.api.libreria.model.entity.Usuario;


public class MapearDTOAEntidad {

    /**
     * Método que mapea un objeto UsuarioDTO a un objeto Usuario
     * @param usuarioDTO contiene un objeto de tipo UsuarioDTO
     * @return retorna un objeto de tipo Usuario
     */
    public static Usuario mapearDTOAUsuario(UsuarioDTO usuarioDTO) {
        if(usuarioDTO == null){
            throw new IllegalStateException("El objeto usuarioDTO no puede ser nulo.");
        }
        //List<RolDTO> listaRolDTO = usuarioDTO.getListaRolDTO();
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setRut(usuarioDTO.getRut());
        usuario.setCorreo(usuarioDTO.getCorreo());
        return usuario;
    }

    /**
     * Método que mapea un objeto RolDTO a un objeto Rol
     * @param rolDTO contiene un obejto de tipo RolDTO
     * @return retorna un obejto de tipo Rol
     */
    public static Rol mapearDTOARol(RolDTO rolDTO) {
        if(rolDTO == null){
            throw new IllegalStateException("El objeto rolDTO no puede ser nulo.");
        }
        Rol rol = new Rol();
        rol.setId(rolDTO.getId());
        rol.setRolNombre(rolDTO.getRolnombre());
        return rol;
    }
}
