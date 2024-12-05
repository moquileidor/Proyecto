package com.proyectopersonal.repository;

import com.proyectopersonal.model.Citas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitasRepository extends JpaRepository<Citas, Integer> {
    List<Citas> findByIdMedico(int idMedico);
    List<Citas> findByIdPaciente(int idPaciente);

}
