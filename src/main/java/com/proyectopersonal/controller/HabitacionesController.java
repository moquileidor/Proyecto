package com.proyectopersonal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.proyectopersonal.service.HabitacionesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.proyectopersonal.model.Habitaciones;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/habitaciones")
public class HabitacionesController {

    @Autowired
    private HabitacionesService habitacionesService;

    //Mostrar formulario para registrar una habitacion
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model){
        model.addAttribute("habitacion", new Habitaciones());
        return "registrarHabitacion";
    }

    //Registrar una habitacion a través del formulario
    @PostMapping("/guardar")
    public String registrarHabitacion(@ModelAttribute("habitacion") Habitaciones habitacion, Model model){
        try{
            habitacionesService.createHabitacion(habitacion);
            return "redirect:/habitaciones/listar";
        }catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            return "registrarHabitacion";
        }
    }

    @GetMapping("/listarConArticulos")
    public String listarHabitacionesConArticulos(Model model) {
        List<Habitaciones> habitaciones = habitacionesService.getAllHabitaciones();
        model.addAttribute("habitaciones", habitaciones);
        return "listarHabitacionesConArticulos";
    }

    //Listar todas las habitaciones
    @GetMapping("/listar")
    public String listarHabitaciones(Model model){
        List<Habitaciones> habitaciones = habitacionesService.getAllHabitaciones();
        model.addAttribute("habitaciones", habitaciones);
        return "listarHabitaciones";
    }

    //Mostrar el formulario de edición de una habitacion
    @GetMapping("/editar/{num_habitacion}")
    public String mostrarFormularioEdicion(@PathVariable("num_habitacion") int num_habitacion, Model model){
        Habitaciones habitacion = habitacionesService.getHabitacionByNum(num_habitacion);
        model.addAttribute("habitacion", habitacion);
        return "editarHabitacion";
    }

    //Editar una habitacion
    @PostMapping("/editar/{num_habitacion}")
    public String actualizarHabitacion(@PathVariable("num_habitacion") int num_habitacion, @ModelAttribute("habitacion") Habitaciones habitacion, Model model){
        Habitaciones habitacionExistente = habitacionesService.getHabitacionByNum(num_habitacion);

        if (habitacionExistente.getEstado().equals("Ocupada")) {
            model.addAttribute("error", "No se puede editar una habitación que está ocupada.");
            return "editarHabitacion";
        }

        habitacion.setNum_habitacion(num_habitacion);
        habitacionesService.updateHabitacion(habitacion);
        return "redirect:/habitaciones/listar";
    }

    //Eliminar una habitacion
    @GetMapping("/eliminar/{num_habitacion}")
    public String eliminarHabitacion(@PathVariable("num_habitacion") int num_habitacion, Model model){
        Habitaciones habitacion = habitacionesService.getHabitacionByNum(num_habitacion);

        if (habitacion.getEstado().equals("Ocupada")) {
            model.addAttribute("error", "No se puede eliminar una habitación que está ocupada.");
            List<Habitaciones> habitaciones = habitacionesService.getAllHabitaciones();
            model.addAttribute("habitaciones", habitaciones);
            return "listarHabitaciones";
        } else {
            habitacionesService.deleteHabitacion(num_habitacion);
        }



        return "redirect:/habitaciones/listar";
    }
}


