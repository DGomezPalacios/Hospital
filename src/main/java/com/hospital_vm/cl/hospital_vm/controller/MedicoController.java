package com.hospital_vm.cl.hospital_vm.controller;

import com.hospital_vm.cl.hospital_vm.model.Medico;
import com.hospital_vm.cl.hospital_vm.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    // 🔍 Obtener todos los médicos
    @GetMapping
    public ResponseEntity<List<Medico>> listarMedicos() {
        List<Medico> medicos = medicoService.findAll();
        if (medicos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medicos);
    }

    // 🔍 Obtener médico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerMedicoPorId(@PathVariable Long id) {
        Optional<Medico> medicoOpt = medicoService.findById(id);
        return medicoOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 📝 Crear médico
    @PostMapping
    public ResponseEntity<Medico> crearMedico(@RequestBody Medico medico) {
        Medico nuevo = medicoService.save(medico);
        return ResponseEntity.status(201).body(nuevo);
    }

    // 🔁 Actualizar médico
    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizarMedico(@PathVariable Long id, @RequestBody Medico datosActualizados) {
        Optional<Medico> medicoOpt = medicoService.findById(id);
        if (medicoOpt.isPresent()) {
            Medico med = medicoOpt.get();
            med.setRunMedico(datosActualizados.getRunMedico());
            med.setNombreCompleto(datosActualizados.getNombreCompleto());
            med.setEspecialidad(datosActualizados.getEspecialidad());
            med.setJefeTurno(datosActualizados.getJefeTurno());
            medicoService.save(med);
            return ResponseEntity.ok(med);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🗑️ Eliminar médico
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMedico(@PathVariable Long id) {
        if (medicoService.delete(id)) {
            return ResponseEntity.ok("Médico eliminado correctamente");
        } else {
            return ResponseEntity.status(404).body("Médico no encontrado");
        }
    }

    // 📊 Reporte: Lista de médicos con especialidades
    @GetMapping("/reportes/especialidades")
    public ResponseEntity<List<Medico>> reporteEspecialidades() {
        List<Medico> medicos = medicoService.findAll();
        return medicos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(medicos);
    }
}
