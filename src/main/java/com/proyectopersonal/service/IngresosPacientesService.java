package com.proyectopersonal.service;

import com.proyectopersonal.model.Habitaciones;
import com.proyectopersonal.model.IngresosPacientes;
import com.proyectopersonal.model.Pacientes;
import com.proyectopersonal.repository.HabitacionesRepository;
import com.proyectopersonal.repository.IngresosPacientesRepository;
import com.proyectopersonal.repository.PacientesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngresosPacientesService {

    @Autowired
    private IngresosPacientesRepository ingresosPacientesRepository;

    @Autowired
    private PacientesRepository pacientesRepository;

    @Autowired
    private HabitacionesRepository habitacionesRepository;

    @Transactional
    public IngresosPacientes registrarIngreso(int idPaciente, String ciudadDomicilio, String motivoHospitalizacion, boolean tieneAcompanante, int numHabitacion) {
        // Verificar que el paciente exista
        Pacientes paciente = pacientesRepository.findById(idPaciente).orElseThrow(() ->
                new IllegalArgumentException("Paciente con id " + idPaciente + " no encontrado.")
        );

        //Verificar si el paciente ya está ingresado
        boolean pacienteIngresado = ingresosPacientesRepository.existsByPacienteIdPaciente(idPaciente);
        if (pacienteIngresado){
            throw new IllegalArgumentException("El paciente con id " + idPaciente + " ya está ingresado.");
        }

        // Verificar que la habitación exista y esté disponible
        Habitaciones habitacion = habitacionesRepository.findById(numHabitacion).orElseThrow(() ->
                new IllegalArgumentException("Habitación con número " + numHabitacion + " no encontrada.")
        );

        if (habitacion.getEstado().equals("Ocupada")) {
            throw new IllegalArgumentException("La habitación " + numHabitacion + " ya está ocupada.");
        }

        // Crear el ingreso del paciente
        IngresosPacientes ingreso = new IngresosPacientes();
        ingreso.setPaciente(paciente);
        ingreso.setCiudad_domicilio(ciudadDomicilio);
        ingreso.setMotivo_hospitalizacion(motivoHospitalizacion);
        ingreso.setTiene_acompanante(tieneAcompanante);
        ingreso.setHabitacion(habitacion);


        // Actualizar el estado de la habitación a "Ocupada"
        habitacion.setEstado("Ocupada");
        habitacionesRepository.save(habitacion);

        // Guardar el ingreso en la base de datos
        return ingresosPacientesRepository.save(ingreso);
    }

    public List<IngresosPacientes> getAllIngresosPacientes(){
        return ingresosPacientesRepository.findAll();
    }

    @Transactional
    public void deleteIngresoByPacienteId(int idPaciente) {
        ingresosPacientesRepository.deleteByPacienteIdPaciente(idPaciente);
    }

    public IngresosPacientes getIngresoByPacienteId(int idPaciente) {
        return ingresosPacientesRepository.findByPacienteIdPaciente(idPaciente).orElseThrow(() ->
                new IllegalArgumentException("Ingreso del paciente con id " + idPaciente + " no encontrado.")
        );
    }
}
