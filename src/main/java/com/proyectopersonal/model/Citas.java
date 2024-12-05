package com.proyectopersonal.model;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "citas")
public class Citas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id_cita;
    @Column(name = "id_paciente")
    private int idPaciente;
    @Column(name = "nombre_paciente")
    private String nombre_paciente;
    @Column(name = "id_medico")
    private int idMedico;
    @Column(name = "nombre_medico")
    private String nombre_medico;
    @Column(name = "fecha_cita")
    private String fecha_cita;
}
