package ec.edu.espe.examen.Garcia.service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import ec.edu.espe.examen.Garcia.dao.ProductoRepository;
import ec.edu.espe.examen.Garcia.domain.Comentario;
import ec.edu.espe.examen.Garcia.domain.Producto;

@Slf4j
@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> obtenerPorRUC(String ruc) {
        List<Producto> lisProducto = this.productoRepository.findByRudEmpresaOrderByDescripcion(ruc);
        return lisProducto;
    }

    public Producto buscarPorCodigo(String codProducto) {
        Optional<Producto> producto = this.productoRepository.findById(codProducto);
        if (producto.isPresent()) {
            log.info("El producto con el codigo: {} se encontro", codProducto);
            log.debug("El producto encotrado es: ", producto.get());
            return producto.get();
        } else {
            throw new RuntimeException("No se encontro el producto con el codigo: " + codProducto);
        }
    }

    public List<Comentario> obtenerComentario(String codProducto) {
        Optional<Producto> producto = this.productoRepository.findById(codProducto);
        if (producto.isPresent()) {
            log.debug("Los comentarios del producto {} son: {}", codProducto, producto.get().getComentarios());
            return producto.get().getComentarios();
        } else {
            throw new RuntimeException("No se encontro el producto con el codigo: " + codProducto);
        }
    }

    public void crearProducto(Producto producto) {
        try {
            producto.setCodProducto(new DigestUtils("MD2").digestAsHex(producto.toString()));
            this.productoRepository.save(producto);
            log.info("El prodcuto {} se ha creado exitosamente", producto.toString());
        } catch (Exception e) {
            throw new RuntimeException("Error al crear lel producto " + producto.toString());
        }
    }

    public Producto agregarComentario(String codProducto, Comentario comentario) {
        try {
            Optional<Producto> producto = this.productoRepository.findById(codProducto);
            if (producto.isPresent()) {
                Producto productoAct = producto.get();
                List<Comentario> comentarios = productoAct.getComentarios();
                comentarios.add(comentario);
                productoAct.setComentarios(comentarios);
                this.productoRepository.save(productoAct);
                return productoAct;
            } else {
                throw new RuntimeException("No se encontro el producto con el codigo: " + codProducto);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al cambiar agregar el comentario");
        }
    }
}
