package com.hospital_vm.cl.hospital_vm.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 13)
    private String run;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private String correo;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private TipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Atencion> atenciones;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    private FichaPaciente ficha;
}
