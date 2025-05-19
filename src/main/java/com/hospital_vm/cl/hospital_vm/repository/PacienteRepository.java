package com.hospital_vm.cl.hospital_vm.repository;

import com.hospital_vm.cl.hospital_vm.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Por convención
    Optional<Paciente> findByRun(String run);
    List<Paciente> findByApellido(String apellido);
    Paciente findByCorreo(String correo);
    List<Paciente> findByNombreAndApellido(String nombre, String apellido);

    // JPQL
    @Query("SELECT p FROM Paciente p WHERE p.apellido = :apellido")
    List<Paciente> buscarPorApellidos(@Param("apellido") String apellido);

    // SQL nativo
    @Query(value = "SELECT * FROM paciente WHERE correo = :correo", nativeQuery = true)
    Paciente buscarPorCorreo(@Param("correo") String correo);

    // costos por tipo de suuario
    @Query("SELECT p.tipoUsuario.nombre, SUM(a.costo) " +
           "FROM Paciente p JOIN p.atenciones a " +
           "GROUP BY p.tipoUsuario.nombre")
    List<Object[]> obtenerCostosTotalesPorTipoUsuario();
}
