package com.proyectopersonal.controller;

import com.proyectopersonal.model.Citas;
import com.proyectopersonal.service.CitasService;
import com.proyectopersonal.service.MedicosService;
import com.proyectopersonal.service.PacientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/citas")
public class CitasController {

    @Autowired
    private CitasService citasService;
    @Autowired
    private PacientesService pacientesService;
    @Autowired
    private MedicosService medicosService;

    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model){
        model.addAttribute("cita", new Citas());
        model.addAttribute("pacientes", pacientesService.getAllPacientes());
        model.addAttribute("medicos", medicosService.getAllMedicos());
        return "registrarCitas";
    }

    @GetMapping("/listarCitas")
    public String listarCitas(Model model){
        model.addAttribute("citas", citasService.getAllCitas());
        return "listarCitas";
    }

    @PostMapping("/guardar")
    public String registrarCita(
            @RequestParam("nombre_paciente") String nombrePaciente,
            @RequestParam("nombre_medico") String nombreMedico,
            @RequestParam("fecha_cita") String fechaCita,
            @RequestParam("idPaciente") int idPaciente,
            @RequestParam("idMedico") int idMedico,
            Model model){

        try{
            Citas cita = new Citas();
            cita.setNombre_paciente(nombrePaciente);
            cita.setIdPaciente(idPaciente);
            cita.setNombre_medico(nombreMedico);
            cita.setIdMedico(idMedico);
            cita.setFecha_cita(fechaCita);

            citasService.createCita(cita);
            return "redirect:/citas/listarCitas";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "registrarCitas";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") int id, Model model){
        Citas cita = citasService.getCitaById(id);
        model.addAttribute("cita", cita);
        model.addAttribute("pacientes", pacientesService.getAllPacientes());
        model.addAttribute("medicos", medicosService.getAllMedicos());
        return "editarCitas";
    }

    @PostMapping("/editar/{id}")
    public String actualizarCita(
            @PathVariable("id") int id,
            @RequestParam("nombre_paciente") String nombrePaciente,
            @RequestParam("nombre_medico") String nombreMedico,
            @RequestParam("fecha_cita") String fechaCita,
            @RequestParam("idPaciente") int idPaciente,
            @RequestParam("idMedico") int idMedico,
            Model model){
        try {
            Citas cita = citasService.getCitaById(id);
            cita.setNombre_paciente(nombrePaciente);
            cita.setIdPaciente(idPaciente);
            cita.setNombre_medico(nombreMedico);
            cita.setIdMedico(idMedico);
            cita.setFecha_cita(fechaCita);
            citasService.updateCita(cita);
            return "redirect:/citas/listarCitas";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "editarCitas";
        }

    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable("id") int id){
        citasService.deleteCita(id);
        return "redirect:/citas/listarCitas";
    }
}
