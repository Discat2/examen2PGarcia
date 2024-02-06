package ec.edu.espe.examen.Garcia.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.examen.Garcia.domain.Comentario;
import ec.edu.espe.examen.Garcia.domain.Producto;
import ec.edu.espe.examen.Garcia.service.ProductoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springframework.http.ResponseEntity;

@Slf4j
@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    private final ProductoService productoService;


    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    @GetMapping("{codProducto}")
    public ResponseEntity<Producto> obtenerPorCodigo(@PathVariable("codProducto") String codProducto) {
        log.info("Obteniendo producto por el codProducto: {}", codProducto);
        try {
            return ResponseEntity.ok(this.productoService.buscarPorCodigo(codProducto));
        } catch (RuntimeException rte) {
            log.error("Error al obtener el producto por el codProducto {}", codProducto);
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("ruc/{ruc}")
    public ResponseEntity<List<Producto>> obtenerPorRUC(@PathVariable("ruc") String ruc) {
        log.info("Obteniendo producto por el RUC: {}", ruc);
        try {
            return ResponseEntity.ok(this.productoService.obtenerPorRUC(ruc));
        } catch (RuntimeException rte) {
            log.error("Error al obtener el producto por el ruc ", ruc);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("comentario/{codProducto}")
    public ResponseEntity<List<Comentario>> obtenerComentario(@PathVariable("codProducto") String codProducto) {
        log.info("Obteniendo comentarios del producto: {}", codProducto);
        try {
            return ResponseEntity.ok(this.productoService.obtenerComentario(codProducto));
        } catch (RuntimeException rte) {
            log.error("Error al obtener el producto por el ruc {}", codProducto);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        log.info("Creando la producto: {}", producto);
        try {
            this.productoService.crearProducto(producto);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException rte) {

            log.error("Error al crear el producto {}", rte);
            return ResponseEntity.badRequest().build();
        }
    }    

    @PostMapping("{codProducto}")
    public ResponseEntity<Producto> crearProducto(@PathVariable("codProducto") String codProducto, @RequestBody Comentario comentario) {
        log.info("Ingresando el comentario: {} al producto", comentario, codProducto);
        try {
            return ResponseEntity.ok(this.productoService.agregarComentario(codProducto, comentario));
        } catch (RuntimeException rte) {

            log.error("Error al crear el comentario {}", codProducto);
            return ResponseEntity.badRequest().build();
        }
    }    
}
