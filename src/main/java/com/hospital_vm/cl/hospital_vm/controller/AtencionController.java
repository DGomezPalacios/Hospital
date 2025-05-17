package com.hospital_vm.cl.hospital_vm.controller;

import com.hospital_vm.cl.hospital_vm.model.Atencion;
import com.hospital_vm.cl.hospital_vm.service.AtencionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/atenciones")
public class AtencionController {

    @Autowired
    private AtencionService atencionService;

    @GetMapping
    public ResponseEntity<List<Atencion>> listarAtenciones() {
        List<Atencion> atenciones = atencionService.findAll();
        return atenciones.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(atenciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atencion> obtenerPorId(@PathVariable Long id) {
        Optional<Atencion> atencion = atencionService.findById(id);
        return atencion.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Atencion> crearAtencion(@RequestBody Atencion atencion) {
        Atencion nueva = atencionService.save(atencion);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atencion> actualizarAtencion(@PathVariable Long id, @RequestBody Atencion datos) {
        Optional<Atencion> existente = atencionService.findById(id);
        if (existente.isPresent()) {
            Atencion at = existente.get();
            at.setFechaAtencion(datos.getFechaAtencion());
            at.setCosto(datos.getCosto());
            at.setComentario(datos.getComentario());
            at.setMedico(datos.getMedico());
            at.setPaciente(datos.getPaciente());
            return ResponseEntity.ok(atencionService.save(at));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        return atencionService.delete(id)
                ? ResponseEntity.ok("Atención eliminada")
                : ResponseEntity.status(404).body("No encontrada");
    }

    // Reporte: atenciones por paciente
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Atencion>> atencionesPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(atencionService.buscarPorPaciente(pacienteId));
    }

    // Reporte: atenciones por médico
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Atencion>> atencionesPorMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(atencionService.buscarPorMedico(medicoId));
    }

    // Reporte: atenciones por fecha
    @GetMapping("/fecha")
    public ResponseEntity<List<Atencion>> atencionesPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha) {
        return ResponseEntity.ok(atencionService.buscarPorFecha(fecha));
    }
}
