package com.proyectopersonal.repository;

import com.proyectopersonal.model.SalidasPacientes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalidasPacientesRepository extends JpaRepository<SalidasPacientes, Integer> {
    List<SalidasPacientes> findByIdPaciente(int idPaciente);
}
