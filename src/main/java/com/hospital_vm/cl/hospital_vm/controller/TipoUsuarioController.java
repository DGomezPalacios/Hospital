package com.hospital_vm.cl.hospital_vm.controller;

import com.hospital_vm.cl.hospital_vm.model.TipoUsuario;
import com.hospital_vm.cl.hospital_vm.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tipos-usuario")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @GetMapping
    public ResponseEntity<List<TipoUsuario>> listar() {
        List<TipoUsuario> tipos = tipoUsuarioService.findAll();
        return tipos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> buscar(@PathVariable Long id) {
        return tipoUsuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoUsuario> crear(@RequestBody TipoUsuario tipoUsuario) {
        return ResponseEntity.status(201).body(tipoUsuarioService.save(tipoUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuario> actualizar(@PathVariable Long id, @RequestBody TipoUsuario datos) {
        Optional<TipoUsuario> tipoOpt = tipoUsuarioService.findById(id);
        if (tipoOpt.isPresent()) {
            TipoUsuario tipo = tipoOpt.get();
            tipo.setNombre(datos.getNombre());
            return ResponseEntity.ok(tipoUsuarioService.save(tipo));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        return tipoUsuarioService.delete(id)
                ? ResponseEntity.ok("Tipo de usuario eliminado")
                : ResponseEntity.status(404).body("No encontrado");
    }
}
