package com.proyectopersonal.controller;

import com.proyectopersonal.model.Habitaciones;
import com.proyectopersonal.model.IngresosPacientes;
import com.proyectopersonal.model.Pacientes;
import com.proyectopersonal.service.HabitacionesService;
import com.proyectopersonal.service.IngresosPacientesService;
import com.proyectopersonal.service.PacientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ingresos")
public class IngresosPacientesController {

    @Autowired
    private IngresosPacientesService ingresosPacientesService;

    @Autowired
    private PacientesService pacientesService;

    @Autowired
    private HabitacionesService habitacionesService;

    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        List<Habitaciones> habitaciones = habitacionesService.getHabitacionesDisponibles();
        List<Pacientes> pacientes = pacientesService.getAllPacientes();

        List<IngresosPacientes> ingresosPacientes = ingresosPacientesService.getAllIngresosPacientes();
        List<Integer> pacientesConIngresos = ingresosPacientes.stream()
                        .map(ingreso -> ingreso.getPaciente().getIdPaciente())
                        .collect(Collectors.toList());

        List<Pacientes> pacientesDisponible = pacientes.stream()
                        .filter(paciente -> !pacientesConIngresos.contains(paciente.getIdPaciente()))
                        .collect(Collectors.toList());


        model.addAttribute("habitaciones", habitaciones);
        model.addAttribute("pacientes", pacientesDisponible);
        return "registrarIngreso";
    }

    @PostMapping("/registrar")
    public String registrarIngreso(@ModelAttribute IngresosPacientes ingreso, Model model) {
        try {
            Habitaciones habitacion = habitacionesService.getHabitacionByNum(ingreso.getHabitacion().getNum_habitacion());

            if(ingreso.isTiene_acompanante() && !habitacion.getTipo_habitacion().equals("Compartida")){
                throw new IllegalArgumentException("Si el paciente tiene acompañante, solo se permite una habitación compartida.");
            }

            ingreso.setHabitacion(habitacion);
            ingresosPacientesService.registrarIngreso(
                    ingreso.getPaciente().getIdPaciente(),
                    ingreso.getCiudad_domicilio(),
                    ingreso.getMotivo_hospitalizacion(),
                    ingreso.isTiene_acompanante(),
                    habitacion.getNum_habitacion()
            );
            return "redirect:/ingresos/listar";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            List<Habitaciones> habitaciones = habitacionesService.getHabitacionesDisponibles();
            List<Pacientes> pacientes = pacientesService.getAllPacientes();
            model.addAttribute("habitaciones", habitaciones);
            model.addAttribute("pacientes", pacientes);
            return "registrarIngreso";
        }
    }

    @GetMapping("/listar")
    public String listarIngresosPacientes(Model model){
        List<IngresosPacientes> ingresosPacientes = ingresosPacientesService.getAllIngresosPacientes();
        model.addAttribute("ingresosPacientes", ingresosPacientes);
        return "listarIngresosPacientes";
    }
}
