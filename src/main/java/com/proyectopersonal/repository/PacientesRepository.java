package com.proyectopersonal.repository;

import com.proyectopersonal.model.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientesRepository extends JpaRepository<Pacientes, Integer> {
}
