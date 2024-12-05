package com.proyectopersonal.service;

import com.proyectopersonal.model.PacientesServicios;
import com.proyectopersonal.repository.PacientesServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacientesServicioService {

    @Autowired
    private PacientesServiciosRepository pacientesServiciosRepository;

    public List<PacientesServicios> getAllPacientesServicios(){
        return pacientesServiciosRepository.findAll();
    }

    public void createPacienteServicio(PacientesServicios pacienteServicio){
        pacientesServiciosRepository.save(pacienteServicio);
    }

    public List<PacientesServicios> getServiciosByPacienteId(int idPaciente) {
        return pacientesServiciosRepository.findByPacienteIdPaciente(idPaciente);
    }

    public PacientesServicios getPacienteServicioByPacienteId(int id){
        return pacientesServiciosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    public PacientesServicios updatePacienteServicio(PacientesServicios pacienteServicio){
        return pacientesServiciosRepository.save(pacienteServicio);
    }


    public void deletePacienteServicio(int id){
        pacientesServiciosRepository.deleteById(id);
    }

    public void updateNombrePacienteInServicios(int idPaciente, String nuevoNombre){
        List<PacientesServicios> pacientesServicios = pacientesServiciosRepository.findByPacienteIdPaciente(idPaciente);
        for (PacientesServicios pacienteServicio : pacientesServicios){
            pacienteServicio.setNombrePaciente(nuevoNombre);
            pacientesServiciosRepository.save(pacienteServicio);
        }
    }
}
