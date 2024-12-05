package com.proyectopersonal.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pacientes_servicios")
public class PacientesServicios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Pacientes paciente;

    @Column(name = "nombre_paciente")
    private String nombrePaciente;

    @Column(name = "nombre_servicio")
    private String nombreServicio;

    @Column(name = "precio_servicio")
    private double precioServicio;
}
