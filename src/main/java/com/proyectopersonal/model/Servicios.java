package com.proyectopersonal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "servicios")
public class Servicios {

    @Id
    @Column(name = "id_servicio")
    private int idServicio;
    @Column(name = "nombre_servicio")
    private String nombreServicio;
    @Column(name = "precio_servicio")
    private double precioServicio;
}
