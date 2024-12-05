package com.proyectopersonal.service;

import com.proyectopersonal.model.Pacientes;
import com.proyectopersonal.repository.PacientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacientesService {

    @Autowired
    private PacientesRepository pacientesRepository;

    //PRUEBA
    @Autowired
    private CitasService citasService;

    @Autowired
    private PacientesServicioService pacientesServicioService;

    @Autowired
    private SalidasPacientesService salidasPacientesService;

    // Obtener todos los pacientes
    public List<Pacientes> getAllPacientes() {
        return pacientesRepository.findAll();
    }

    // Crear un nuevo paciente
    public Pacientes createPaciente(Pacientes paciente) {
        // Verificar si el paciente ya existe por su ID
        Optional<Pacientes> existePaciente = pacientesRepository.findById(paciente.getIdPaciente());
        if (existePaciente.isPresent()) {
            throw new IllegalArgumentException("Paciente con ID " + paciente.getIdPaciente() + " ya existe.");
        }
        return pacientesRepository.save(paciente);
    }


    // Obtener un paciente por su ID
    public Pacientes getPacienteById(int id) {
        Optional<Pacientes> paciente = pacientesRepository.findById(id);
        if (!paciente.isPresent()) {
            throw new IllegalArgumentException("Paciente con ID " + id + " no encontrado.");
        }
        return paciente.get();
    }

    public void updatePaciente(Pacientes paciente){
        pacientesRepository.save(paciente);
        citasService.updateNombrePacienteInCitas(paciente.getIdPaciente(), paciente.getNombre_paciente());
        pacientesServicioService.updateNombrePacienteInServicios(paciente.getIdPaciente(), paciente.getNombre_paciente());
        salidasPacientesService.updateNombrePacienteInSalidas(paciente.getIdPaciente(), paciente.getNombre_paciente());
        
    }

    // Eliminar un paciente
    public void deletePaciente(int id) {
        // Verificar si el paciente existe antes de eliminar
        if (!pacientesRepository.existsById(id)) {
            throw new IllegalArgumentException("Paciente con ID " + id + " no existe.");
        }
        pacientesRepository.deleteById(id);
    }
}
