package com.hospital_vm.cl.hospital_vm.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "atencion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_atencion", nullable = false)
    private Date fechaAtencion;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_atencion", nullable = true)
    private Date horaAtencion;

    @Column(nullable = false)
    private Double costo;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @Column(length = 300)
    private String comentario;
}
