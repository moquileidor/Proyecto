package com.proyectopersonal.repository;

import com.proyectopersonal.model.Habitaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HabitacionesRepository extends JpaRepository<Habitaciones, Integer> {
    List<Habitaciones> findByEstado(String estado);
}
