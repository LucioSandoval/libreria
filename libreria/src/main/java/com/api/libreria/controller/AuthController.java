package com.api.libreria.controller;

import com.api.libreria.model.dto.LoginDTO;
import com.api.libreria.model.dto.UsuarioDTO;
import com.api.libreria.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final UsuarioService usuarioService;

    @PostMapping(value = "/login",  consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) {
        try{
            return new ResponseEntity<>(usuarioService.login(loginDTO),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHandler.generarRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @PostMapping(value = "/crearUsuario", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> crearUsuario(@RequestBody UsuarioDTO usuarioDTO){
        try {
            return new ResponseEntity<>(this.usuarioService.crearUsuario(usuarioDTO), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseHandler.generarRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }catch (IllegalStateException e){
            e.printStackTrace();
            return ResponseHandler.generarRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generarRespuesta(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Ha ocurrido un error al intentar registrar el usuario.");
        }
    }







}
