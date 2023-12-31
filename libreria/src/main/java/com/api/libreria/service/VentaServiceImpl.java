package com.api.libreria.service;

import com.api.libreria.model.dto.VentaDTO;
import com.api.libreria.model.dto.DetalleVentaDTO;
import com.api.libreria.model.entity.Usuario;
import com.api.libreria.model.entity.Libro;
import com.api.libreria.model.entity.LibroVenta;
import com.api.libreria.model.entity.Venta;
import com.api.libreria.model.projection.DetalleVentaProjection;
import com.api.libreria.repository.UsuarioRepository;
import com.api.libreria.repository.LibroRepository;
import com.api.libreria.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VentaServiceImpl implements VentaService{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private VentaRepository ventaRepository;

    /**
     * Método que permite crear venta
     * @param ventaDTO contiene un objeto de tipo VentaDTO
     */
    public void crearVenta(VentaDTO ventaDTO) {

        if(ventaDTO == null){
            throw new IllegalStateException("El objeto compraDTO no puede ser nulo.");
        }

        this.mapearCompraDTOAVenta(ventaDTO);

    }

    @Override
    public List<DetalleVentaProjection> detalleVenta() {

        return this.ventaRepository.findBy();
    }

    /**
     * Método que realiza la venta de libros
     * @param ventaDTO contiene un objeto de tipo VentaDTO
     */
    private void mapearCompraDTOAVenta(VentaDTO ventaDTO) {
        Usuario usuario = this.usuarioRepository.findById(ventaDTO.getIdUsuario())
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));
        Float totalVenta = (float) 0;
        List<DetalleVentaDTO> listaDetalleDTO = ventaDTO.getDetalleCompraDTO();
        Venta venta = new Venta();
        venta.setUsuario(usuario);


        for (DetalleVentaDTO detalleCompraDTO: listaDetalleDTO) {
            Libro libro = this.libroRepository.findById(detalleCompraDTO.getIdLibro())
                    .orElseThrow(() -> new IllegalStateException("libro no encontrado"));
            // Validar que el stock sea mayor a la cantidad de la compra
            if(libro.getStock() < detalleCompraDTO.getCantidad()){
                throw new IllegalStateException("Stock insuficiente para el libro: " + libro.getTitulo());
            }
            libro.setStock(libro.getStock() - detalleCompraDTO.getCantidad());
            this.libroRepository.save(libro);

            LibroVenta libroVenta = new LibroVenta();
            libroVenta.setVenta(venta);
            libroVenta.setLibro(libro);
            libroVenta.setCantidad(detalleCompraDTO.getCantidad());
            libroVenta.setSubTotal(libro.getPrecio() * detalleCompraDTO.getCantidad());

            venta.getLibrosVentas().add(libroVenta);
            totalVenta =totalVenta + (libro.getPrecio() * detalleCompraDTO.getCantidad());

        }
        venta.setTotalVenta(totalVenta);
        Float iva = (float) (totalVenta * 0.19);
        venta.setIva(iva);
        venta.setMontoNeto(totalVenta - iva);
        this.ventaRepository.save(venta);


    }


}
