package com.proyectopersonal.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "pacientes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pacientes {
    @Id
    @Column(name = "id_paciente")
    private int idPaciente;

    @Column(name = "nombre_paciente")
    private String nombre_paciente;

    @Column(name = "edad_paciente")
    private int edad_paciente;

    @Column(name = "direccion_paciente")
    private String direccion_paciente;


    @Column(name = "telefono_paciente")
    private String telefono_paciente;

    @Column(name = "eps_paciente")
    private String eps_paciente;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<IngresosPacientes> ingresos;

    public Pacientes() {
    }
    public Pacientes(int idPaciente) {
        this.idPaciente = idPaciente;
    }
}
