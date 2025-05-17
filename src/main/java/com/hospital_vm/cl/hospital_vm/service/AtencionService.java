package com.hospital_vm.cl.hospital_vm.service;

import com.hospital_vm.cl.hospital_vm.model.Atencion;
import com.hospital_vm.cl.hospital_vm.repository.AtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AtencionService {

    @Autowired
    private AtencionRepository atencionRepository;

    public List<Atencion> findAll() {
        return atencionRepository.findAll();
    }

    public Optional<Atencion> findById(Long id) {
        return atencionRepository.findById(id);
    }

    public Atencion save(Atencion atencion) {
        return atencionRepository.save(atencion);
    }

    public boolean delete(Long id) {
        if (atencionRepository.existsById(id)) {
            atencionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Atencion> buscarPorPaciente(Long pacienteId) {
        return atencionRepository.findByPacienteId(pacienteId);
    }

    public List<Atencion> buscarPorMedico(Long medicoId) {
        return atencionRepository.findByMedicoId(medicoId);
    }

    public List<Atencion> buscarPorFecha(Date fecha) {
        return atencionRepository.findByFechaAtencion(fecha);
    }
}
