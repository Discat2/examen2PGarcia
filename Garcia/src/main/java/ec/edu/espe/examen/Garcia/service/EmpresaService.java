package ec.edu.espe.examen.Garcia.service;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import ec.edu.espe.examen.Garcia.dao.EmpresaRepository;
import ec.edu.espe.examen.Garcia.domain.Empresa;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa buscarPorRUC(String ruc){
        Empresa empresa = this.empresaRepository.findByRuc(ruc);
        if(empresa != null){
            log.info("La empresa con el ruc: {} se encontro", ruc);
            log.debug("La empresa encotrada es: {}", empresa);
            return empresa;
        }else{
            throw new RuntimeException("No se encontro la empresa con el ruc: " + ruc); 
        }
    }

    public void crearEmpresa(Empresa empresa){
        try {
            empresa.setFechaCreacion(new Date());
            empresa.setCodEmpresa(new DigestUtils("MD2").digestAsHex(empresa.toString()));
            this.empresaRepository.save(empresa);
            log.info("La empresa {} se ha creado exitosamente", empresa.toString());
        } catch (Exception e) {
            throw new RuntimeException("Erro al crear la empresa " + empresa.toString());
        }
    }
}
