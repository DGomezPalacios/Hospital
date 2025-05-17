package com.hospital_vm.cl.hospital_vm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ficha_paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datos_personales_1")
    private String datosPersonales1;

    @Column(name = "datos_personales_2")
    private String datosPersonales2;

    @Column(name = "datos_personales_3")
    private String datosPersonales3;

    @Column(name = "datos_personales_4")
    private String datosPersonales4;

    @Column(name = "datos_personales_5")
    private String datosPersonales5;

    @OneToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
}
