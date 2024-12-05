package com.proyectopersonal.repository;

import com.proyectopersonal.model.IngresosPacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IngresosPacientesRepository extends JpaRepository<IngresosPacientes, Integer> {
    void deleteByPacienteIdPaciente(int idPaciente);
    boolean existsByPacienteIdPaciente(int idPaciente);
    Optional<IngresosPacientes> findByPacienteIdPaciente(int idPaciente);
}
