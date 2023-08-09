package com.api.libreria.controller;

import com.api.libreria.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping(value = "/listarUsuarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listarUsuarios(){
        try{
            return new ResponseEntity<>(this.usuarioService.listarUsuarios(), HttpStatus.OK);

        }catch (IllegalStateException e){
            e.printStackTrace();
            return ResponseHandler.generarRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHandler.generarRespuesta(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Ha ocurrido un error al intentar obtener los datos de los usuarios.");
        }
    }
}
