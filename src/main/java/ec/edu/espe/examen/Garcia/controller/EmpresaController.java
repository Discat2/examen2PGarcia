package ec.edu.espe.examen.Garcia.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.examen.Garcia.domain.Empresa;
import ec.edu.espe.examen.Garcia.service.EmpresaService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;

@Slf4j
@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("{ruc}")
    public ResponseEntity<Empresa> obtenerPorRUC(@PathVariable("ruc") String ruc) {
        log.info("Obteniendo empresa por RUC: {}", ruc);
        try {
            return ResponseEntity.ok(this.empresaService.buscarPorRUC(ruc));
        } catch (RuntimeException rte) {
            log.error("Error al obtener la empresa con el ruc {}", ruc);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Empresa> crearEmpresa(@RequestBody Empresa empresa) {
        log.info("Creando la empresa: {}", empresa);
        try {
            this.empresaService.crearEmpresa(empresa);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException rte) {

            log.error("Error al crear la empresa {}", rte);
            return ResponseEntity.badRequest().build();
        }
    }
    
}
