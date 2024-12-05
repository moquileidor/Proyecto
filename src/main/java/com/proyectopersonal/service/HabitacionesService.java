package com.proyectopersonal.service;

import com.proyectopersonal.model.Habitaciones;
import com.proyectopersonal.repository.HabitacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HabitacionesService {

    @Autowired
    private HabitacionesRepository habitacionesRepository;

    //Mostrar todas las habitaciones
    public List<Habitaciones> getAllHabitaciones(){
        return habitacionesRepository.findAll();
    }

    //Mostrar habitaciones disponibles
    public List<Habitaciones> getHabitacionesDisponibles(){
        return habitacionesRepository.findByEstado("Disponible");
    }

    //Crear una habitacion
    public Habitaciones createHabitacion(Habitaciones habitacion){
        Optional<Habitaciones> extisteHabitacion = habitacionesRepository.findById(habitacion.getNum_habitacion());
        if (extisteHabitacion.isPresent()){
            throw new IllegalArgumentException("Habitacion con numero " + habitacion.getNum_habitacion() + " ya existe.");
        }
        return habitacionesRepository.save(habitacion);
    }

    //Obtener una habitacion por su numero
    public Habitaciones getHabitacionByNum(int num_habitacion){
        Optional<Habitaciones> habitacion = habitacionesRepository.findById(num_habitacion);
        if (!habitacion.isPresent()){
            throw new IllegalArgumentException("Habitacion con numero " + num_habitacion + " no encontrada.");
        }
        return habitacion.get();
    }

    //Editar una habitacion
    public Habitaciones updateHabitacion(Habitaciones habitacion){
        return habitacionesRepository.save(habitacion);
    }

    //Eliminar una habitacion
    public void deleteHabitacion(int num_habitacion){
        habitacionesRepository.deleteById(num_habitacion);
    }
}
