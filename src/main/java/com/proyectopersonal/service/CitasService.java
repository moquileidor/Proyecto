package com.proyectopersonal.service;

import com.proyectopersonal.model.Citas;
import com.proyectopersonal.repository.CitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CitasService {

    @Autowired
    private CitasRepository citasRepository;

    public void createCita(Citas cita){
        citasRepository.save(cita);
    }

    public List<Citas> getAllCitas(){
        return citasRepository.findAll();
    }

    public Citas getCitaById(int id){
        return citasRepository.findById(id).orElse(null);
    }

    public void deleteCita(int id){
        citasRepository.deleteById(id);
    }

    public void updateCita(Citas cita){
        citasRepository.save(cita);
    }
    //
    public void updateNombreMedicoInCitas (int idMedico, String nuevoNombre){
        List<Citas> citas = citasRepository.findByIdMedico(idMedico);
        for (Citas cita : citas){
            cita.setNombre_medico(nuevoNombre);
            citasRepository.save(cita);
        }
    }

    //Prueba de pacientes
    public void updateNombrePacienteInCitas (int idPaciente, String nuevoNombre){
        List<Citas> citas = citasRepository.findByIdPaciente(idPaciente);
        for (Citas cita : citas){
            cita.setNombre_paciente(nuevoNombre);
            citasRepository.save(cita);
        }
    }
}
