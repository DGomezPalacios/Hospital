package com.hospital_vm.cl.hospital_vm.controller;

import com.hospital_vm.cl.hospital_vm.model.FichaPaciente;
import com.hospital_vm.cl.hospital_vm.service.FichaPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fichas")
public class FichaPacienteController {

    @Autowired
    private FichaPacienteService fichaService;


    @GetMapping("/{id}")
    public ResponseEntity<FichaPaciente> obtenerPorId(@PathVariable Long id) {
        Optional<FichaPaciente> ficha = fichaService.findById(id);
        return ficha.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FichaPaciente> crear(@RequestBody FichaPaciente ficha) {
        FichaPaciente nueva = fichaService.save(ficha);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FichaPaciente> actualizar(@PathVariable Long id, @RequestBody FichaPaciente datos) {
        Optional<FichaPaciente> fichaOpt = fichaService.findById(id);
        if (fichaOpt.isPresent()) {
            FichaPaciente f = fichaOpt.get();
            f.setDatosPersonales1(datos.getDatosPersonales1());
            f.setDatosPersonales2(datos.getDatosPersonales2());
            f.setDatosPersonales3(datos.getDatosPersonales3());
            f.setDatosPersonales4(datos.getDatosPersonales4());
            f.setDatosPersonales5(datos.getDatosPersonales5());
            f.setPaciente(datos.getPaciente()); // Asocia paciente si es necesario
            return ResponseEntity.ok(fichaService.save(f));
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        return fichaService.delete(id)
                ? ResponseEntity.ok("Ficha eliminada")
                : ResponseEntity.status(404).body("Ficha no encontrada");
    }
}
