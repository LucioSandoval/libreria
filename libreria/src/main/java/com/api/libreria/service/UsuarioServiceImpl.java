package com.api.libreria.service;

import com.api.libreria.mapper.MapearDTOAEntidad;
import com.api.libreria.mapper.MapearEntidadADTO;
import com.api.libreria.model.dto.LoginDTO;
import com.api.libreria.model.dto.RespuestaLoginDTO;
import com.api.libreria.model.dto.UsuarioDTO;
import com.api.libreria.model.entity.Rol;
import com.api.libreria.model.entity.Usuario;
import com.api.libreria.repository.RolReposirory;
import com.api.libreria.repository.UsuarioRepository;
import com.api.libreria.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final RolReposirory rolReposirory;
    @Override
    public RespuestaLoginDTO login(LoginDTO loginDTO) {


        Usuario usuario = usuarioRepository.findByRut(loginDTO.getRut())
                .orElseThrow( () -> new IllegalStateException("Usuario no encontrado con rut: "+ loginDTO.getRut()));

        log.info(usuario.toString());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getRut(), loginDTO.getContrasena()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Obtener el token y el jwtTokenProvider
        String token = jwtTokenProvider.generateToken(authentication);


        return MapearEntidadADTO.mapearRespuestaLoginADTO(usuario, token);
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {

        // Validar el formato del correo electrónico
        if (!validarCorreoElectronico(usuarioDTO.getCorreo())) {
            throw new IllegalArgumentException("El correo electrónico no es válido");
        }

        if(this.usuarioRepository.existsByRut(usuarioDTO.getRut())){
            throw new IllegalStateException("Ya existe un usuario con el rut "+ usuarioDTO.getRut());
        }
        if(this.usuarioRepository.existsByCorreo(usuarioDTO.getCorreo())){
            throw new IllegalStateException("Ya existe un usuario con el correo "+ usuarioDTO.getCorreo());
        }

        Usuario usuario = MapearDTOAEntidad.mapearDTOAUsuario(usuarioDTO);
        usuario.setContrasena(this.passwordEncoder.encode(usuarioDTO.getContrasena()));
        Rol rol = this.rolReposirory.findByRolNombre(usuarioDTO.getRol()).get();
        usuario.setRoles(Collections.singleton(rol));
        usuario = this.usuarioRepository.save(usuario);
        return MapearEntidadADTO.mapearUsuarioADTO(usuario);
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario>listaClientes = this.usuarioRepository.findAll();
        return listaClientes.stream()
                .map( cliente -> MapearEntidadADTO.mapearUsuarioADTO(cliente)).collect(Collectors.toList());
    }

    public Boolean validarCorreoElectronico(String correo) {
        // Expresión regular para validar el formato del correo electrónico
        String patron =  "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        return correo.matches(patron);
    }

}
