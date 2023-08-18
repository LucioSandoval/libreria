package com.api.libreria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    /**
     * Método que genera respuesta de tipo HTTP
     * @param status contiene el código de estado HTTP.
     * @param mensaje contiene un texto el cual es el mensaje de respuesta.
     * @return retorna un objeto de tipo ResponseEntity.
     */
    public static ResponseEntity<Object> generarRespuesta(HttpStatus status, String mensaje){
        Map<String, Object> map = new HashMap<>();
        map.put("message", mensaje);
        map.put("timestamp", new Date());
        map.put("status", status.value());

        return new ResponseEntity<Object>(map, status);
    }
}