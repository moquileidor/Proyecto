package com.proyectopersonal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.proyectopersonal.model.Medicos;
import com.proyectopersonal.service.MedicosService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/medicos")
public class MedicosController {

    @Autowired
    private MedicosService medicosService;

    //Mostrar formulario para registrar un medico
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model){
        model.addAttribute("medico", new Medicos());
        return "registrarMedico";
    }

    //Registrar un medico a través del formulario
    @PostMapping("/guardar")
    public String registrarMedico(@ModelAttribute("medico") Medicos medico, Model model){
        try{
            medicosService.createMedico(medico);
            return "redirect:/medicos/listar";
        } catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            return "registrarMedico";
        }
    }

    //Listar todos los medicos
    @GetMapping("/listar")
    public String listarMedicos(Model model){
        List<Medicos> medicos = medicosService.getAllMedicos();
        model.addAttribute("medicos", medicos);
        return "listarMedicos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") int id, Model model){
        Medicos medico = medicosService.getMedicoById(id);
        model.addAttribute("medico", medico);
        return "editarMedico";
    }

    @PostMapping("/editar/{id}")
    public String actualizarMedico(@PathVariable("id") int id, @ModelAttribute("medico") Medicos medico, Model model){
    try {
        Medicos medicoExistente = medicosService.getMedicoById(id);
        // Mantener el ID original
        medico.setId_medico(medicoExistente.getId_medico());
        medicosService.updateMedico(medico);
        return "redirect:/medicos/listar";
    } catch (IllegalArgumentException e) {
        model.addAttribute("error", "Error al actualizar el médico: " + e.getMessage());
        return "editarMedico";
    }
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarMedico(@PathVariable("id") int id){
        medicosService.deleteMedico(id);
        return "redirect:/medicos/listar";
    }
}
