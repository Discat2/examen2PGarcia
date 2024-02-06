package ec.edu.espe.examen.Garcia.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.examen.Garcia.domain.Empresa;

@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String>{
    Empresa findByRuc(String ruc);
}