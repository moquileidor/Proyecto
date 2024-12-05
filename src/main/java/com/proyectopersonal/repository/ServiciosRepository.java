package com.proyectopersonal.repository;

import com.proyectopersonal.model.Servicios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiciosRepository extends JpaRepository<Servicios, Integer> {
    Servicios findByNombreServicio(String nombreServicio);
}
