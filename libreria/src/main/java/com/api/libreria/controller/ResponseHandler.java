package com.api.libreria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generarRespuesta(HttpStatus status, String mensaje){
        Map<String, Object> map = new HashMap<>();
        map.put("message", mensaje);
        map.put("timestamp", new Date());
        map.put("status", status.value());

        return new ResponseEntity<Object>(map, status);
    }
}