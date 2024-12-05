    package com.proyectopersonal.model;

    import com.fasterxml.jackson.annotation.JsonBackReference;
    import jakarta.persistence.*;
    import lombok.Data;

    @Data
    @Entity
    @Table(name = "articulos")
    public class Articulos {

        @Id
        private int codigo_articulo;
        private String nombre_articulo;
        private int cantidad_articulo;
        private String descripcion_articulo;

        @ManyToOne
        @JoinColumn(name = "codigo_habitacion")
        @JsonBackReference
        private Habitaciones habitaciones;
    }
