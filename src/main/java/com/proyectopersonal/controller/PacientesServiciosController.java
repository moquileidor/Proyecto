package com.proyectopersonal.controller;
import com.proyectopersonal.model.Pacientes;
import com.proyectopersonal.model.PacientesServicios;
import com.proyectopersonal.model.Servicios;
import com.proyectopersonal.service.PacientesService;
import com.proyectopersonal.service.PacientesServicioService;
import com.proyectopersonal.service.ServiciosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/pacientesServicios")
public class PacientesServiciosController {

    @Autowired
    private PacientesServicioService pacientesServicioService;

    @Autowired
    private PacientesService pacientesService;

    @Autowired
    private ServiciosService serviciosService;

    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model){
        List<Pacientes> pacientes = pacientesService.getAllPacientes();
        List<Servicios> servicios = serviciosService.getAllServicios();


        model.addAttribute("pacientes", pacientes);
        model.addAttribute("servicios", servicios);

        model.addAttribute("pacientesServicios", new PacientesServicios());
        return "registrarPacientesServicios";
    }

    @GetMapping("/listarPacientesServicios")
    public String listarIngresosPacientes(Model model){
        List<PacientesServicios> pacientesServicios = pacientesServicioService.getAllPacientesServicios();
        model.addAttribute("pacientesServicios", pacientesServicios);
        return "listarPacientesServicios";
    }


    @PostMapping("/guardar")
    public String registrarPacienteServicio(
            @RequestParam("id_paciente") int idPaciente,
            @RequestParam("nombre_servicio") String nombreServicio,
            @RequestParam("precio_servicio") double precioServicio,
            Model model){

        Pacientes paciente = pacientesService.getPacienteById(idPaciente);
        PacientesServicios pacienteServicio = new PacientesServicios();
        pacienteServicio.setPaciente(paciente);
        pacienteServicio.setNombrePaciente(paciente.getNombre_paciente());
        pacienteServicio.setNombreServicio(nombreServicio);
        pacienteServicio.setPrecioServicio(precioServicio);
        pacientesServicioService.createPacienteServicio(pacienteServicio);
        return "redirect:/pacientesServicios/listarPacientesServicios";
        }
    }

