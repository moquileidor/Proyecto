package com.proyectopersonal.service;

import com.proyectopersonal.model.SalidasPacientes;
import com.proyectopersonal.repository.SalidasPacientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalidasPacientesService {

    @Autowired
    private SalidasPacientesRepository salidasPacientesRepository;

    public List<SalidasPacientes> getAllSalidasPacientes() {
        return salidasPacientesRepository.findAll();
    }

    public SalidasPacientes createSalidaPaciente(SalidasPacientes salidaPaciente) {
        return salidasPacientesRepository.save(salidaPaciente);
    }

    public void deleteSalidaPaciente (int id){
        salidasPacientesRepository.deleteById(id);
    }

    public void updateNombrePacienteInSalidas(int idPaciente, String nuevoNombre){
        List<SalidasPacientes> salidasPacientes = salidasPacientesRepository.findByIdPaciente(idPaciente);
        for (SalidasPacientes salidaPaciente : salidasPacientes){
            salidaPaciente.setNombrePaciente(nuevoNombre);
            salidasPacientesRepository.save(salidaPaciente);
        }
    }
}
