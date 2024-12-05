package com.proyectopersonal.repository;

import com.proyectopersonal.model.PacientesServicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacientesServiciosRepository extends JpaRepository<PacientesServicios, Integer> {
    List<PacientesServicios> findByPacienteIdPaciente( int idPaciente);
}
