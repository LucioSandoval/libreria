package com.api.libreria.controller;

import com.api.libreria.model.dto.CompraDTO;
import com.api.libreria.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/venta")
public class VentaController {

    private final VentaService ventaService;
    @PostMapping(value = "/crearVenta", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> CrearVenta(@RequestBody CompraDTO compraDTO){
        try{
            this.ventaService.realizarCompra(compraDTO);
            return ResponseHandler.generarRespuesta( HttpStatus.OK, "Se realizo la compra con exito");
        }catch (IllegalStateException e){
            e.printStackTrace();
            return ResponseHandler.generarRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHandler.generarRespuesta(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Ha ocurrido un error al intentar realizar la venta.");
        }
    }

    @GetMapping(value = "/detalleVenta", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> detalleVenta(){
        try{
            return new ResponseEntity<>(this.ventaService.detalleVenta(), HttpStatus.OK);
        }catch (IllegalStateException e){
            e.printStackTrace();
            return ResponseHandler.generarRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHandler.generarRespuesta(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Ha ocurrido un error al intentar obtener el detalle de la venta.");
        }
    }
}
