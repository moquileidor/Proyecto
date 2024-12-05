package com.proyectopersonal.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "salidas_pacientes")
public class SalidasPacientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_paciente")
    private int idPaciente;

    @Column(name = "nombre_paciente")
    private String nombrePaciente;

    @Column(name = "detalle_cobro")
    private String detalleCobro;

    @Column(name = "total_a_pagar")
    private double totalAPagar;
}
