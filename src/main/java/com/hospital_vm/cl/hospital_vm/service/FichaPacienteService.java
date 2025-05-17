package com.hospital_vm.cl.hospital_vm.service;

import com.hospital_vm.cl.hospital_vm.model.FichaPaciente;
import com.hospital_vm.cl.hospital_vm.repository.FichaPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FichaPacienteService {

    @Autowired
    private FichaPacienteRepository fichaRepository;

    public Optional<FichaPaciente> findById(Long id) {
        return fichaRepository.findById(id);
    }

    public FichaPaciente save(FichaPaciente ficha) {
        return fichaRepository.save(ficha);
    }

    public boolean delete(Long id) {
        if (fichaRepository.existsById(id)) {
            fichaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
