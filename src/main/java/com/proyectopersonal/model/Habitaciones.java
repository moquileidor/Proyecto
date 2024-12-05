package com.proyectopersonal.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "habitaciones")
public class Habitaciones {

    @Id
    @Column(name = "num_habitacion")
    private int num_habitacion;

    @Column(name = "tipo_Habitacion")
    private String tipo_habitacion;

    @Column(name = "estado_Habitacion")
    private String estado;


    @OneToMany(mappedBy = "habitaciones", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Articulos> articulos;
}

