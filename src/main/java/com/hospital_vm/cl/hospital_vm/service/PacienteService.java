package com.hospital_vm.cl.hospital_vm.service;

import com.hospital_vm.cl.hospital_vm.model.Paciente;
import com.hospital_vm.cl.hospital_vm.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public boolean delete(Long id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Paciente> findByRun(String run) {
        return pacienteRepository.findByRun(run);
    }

    public Paciente findByCorreo(String correo) {
        return pacienteRepository.findByCorreo(correo);
    }

    public List<Paciente> findByApellido(String apellido) {
        return pacienteRepository.findByApellido(apellido);
    }

    public List<Paciente> findByNombreAndApellido(String nombre, String apellido) {
        return pacienteRepository.findByNombreAndApellido(nombre, apellido);
    }

    public List<Object[]> obtenerCostosTotalesPorTipoUsuario() {
        return pacienteRepository.obtenerCostosTotalesPorTipoUsuario();
    }
}
