package com.proyectopersonal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ingresos_pacientes")
public class IngresosPacientes implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ingreso;


    @ManyToOne
    @JoinColumn(name = "idPaciente")
    @JsonBackReference
    private Pacientes paciente;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "num_habitacion")
    @JsonManagedReference
    private Habitaciones habitacion;

    private String ciudad_domicilio;
    private String motivo_hospitalizacion;
    private boolean tiene_acompanante;
    private LocalDateTime fecha_ingreso = LocalDateTime.now();
}
