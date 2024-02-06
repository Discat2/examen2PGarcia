package ec.edu.espe.examen.Garcia.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.examen.Garcia.domain.Producto;
import java.util.List;


@Repository
public interface ProductoRepository extends MongoRepository<Producto, String>{

    List<Producto> findByRudEmpresaOrderByDescripcion(String rudEmpresa);
}