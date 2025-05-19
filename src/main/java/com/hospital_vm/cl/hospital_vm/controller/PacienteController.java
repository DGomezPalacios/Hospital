package com.hospital_vm.cl.hospital_vm.controller;

import com.hospital_vm.cl.hospital_vm.model.Paciente;
import com.hospital_vm.cl.hospital_vm.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        List<Paciente> pacientes = pacienteService.findAll();
        if (pacientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        Paciente nuevo = pacienteService.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.findById(id);
        return paciente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody Paciente datosActualizados) {
        Optional<Paciente> pacienteOpt = pacienteService.findById(id);
        if (pacienteOpt.isPresent()) {
            Paciente pac = pacienteOpt.get();
            pac.setRun(datosActualizados.getRun());
            pac.setNombre(datosActualizados.getNombre());
            pac.setApellido(datosActualizados.getApellido());
            pac.setFechaNacimiento(datosActualizados.getFechaNacimiento());
            pac.setCorreo(datosActualizados.getCorreo());

            pacienteService.save(pac);
            return ResponseEntity.ok(pac);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        if (pacienteService.delete(id)) {
            return ResponseEntity.ok("registro eliminado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("paciente no encontrado");
        }
    }

    //buscar por rut
    @GetMapping("/rut/{rut}")
    public ResponseEntity<Paciente> buscarPorRun(@PathVariable String run) {
        return pacienteService.findByRun(run)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //buscar por correo
    @GetMapping("/correo/{correo}")
    public ResponseEntity<Paciente> buscarPorCorreo(@PathVariable String correo) {
        Paciente paciente = pacienteService.findByCorreo(correo);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        }
        return ResponseEntity.notFound().build();
    }

    //buscar por apellido
    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<List<Paciente>> buscarPorApellido(@PathVariable String apellido) {
        List<Paciente> pacientes = pacienteService.findByApellido(apellido);
        if (pacientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pacientes);
    }

    //buscar por nombre y apellido
    @GetMapping("/nombre-apellido/{nombre}/{apellido}")
    public ResponseEntity<List<Paciente>> buscarPorNombreYApellido(@PathVariable String nombre, @PathVariable String apellido) {
        List<Paciente> pacientes = pacienteService.findByNombreAndApellido(nombre, apellido);
        if (pacientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pacientes);
    }
    //total de costos
    @GetMapping("/reportes/costos-por-tipo")
    public ResponseEntity<List<Object[]>> reporteCostosPorTipoUsuario() {
        List<Object[]> resultados = pacienteService.obtenerCostosTotalesPorTipoUsuario();
        return resultados.isEmpty()
            ? ResponseEntity.noContent().build()
            : ResponseEntity.ok(resultados);
}
    //historial paciente (ficha + atenciones)
    @GetMapping("/{id}/historial")
    public ResponseEntity<Paciente> historialCompleto(@PathVariable Long id) {
        return pacienteService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}


}
