package com.hospital_vm.cl.hospital_vm.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ficha_paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String datosPersonales1;
    private String datosPersonales2;
    private String datosPersonales3;
    private String datosPersonales4;
    private String datosPersonales5;

    @OneToOne
    @JoinColumn(name = "id_paciente", unique = true)
    private Paciente paciente;
}
