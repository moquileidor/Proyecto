package com.proyectopersonal.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "medicos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Medicos  {

    @Id
    @Column(name = "id_medico")
    private int id_medico;
    private String nombre_medico;
    private int edad_medico;
    private long telefono_medico;
    private String fecha_nacimiento_medico;
}
