package com.proyectopersonal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.proyectopersonal.service.PacientesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.proyectopersonal.model.Pacientes;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/pacientes")
public class PacientesController {

    @Autowired
    private PacientesService pacientesService;

    // Mostrar formulario para registrar un paciente
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("paciente", new Pacientes());  // Crear un objeto vacío para que Thymeleaf lo enlace
        return "registrarPaciente";  // Este es el nombre del archivo HTML
    }

    // Registrar un paciente a través del formulario
    @PostMapping("/guardar")
    public String registrarPaciente(@ModelAttribute("paciente") Pacientes paciente, Model model) {
        try {
            pacientesService.createPaciente(paciente);  // Guardar el paciente
            return "redirect:/pacientes/listar";  // Redirige a la lista de pacientes
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());  // Agregar mensaje de error al modelo si hay un conflicto
            return "registrarPaciente";  // Retorna al formulario con el error
        }
    }

    // Listar todos los pacientes
    @GetMapping("/listar")
    public String listarPacientes(Model model) {
        List<Pacientes> pacientes = pacientesService.getAllPacientes();
        model.addAttribute("pacientes", pacientes);  // Pasar la lista de pacientes al modelo
        return "listarPacientes";  // Nombre de la vista para mostrar los pacientes
    }


    // Mostrar el formulario de edición de un paciente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") int id, Model model) {
        Pacientes paciente = pacientesService.getPacienteById(id);  // Obtener el paciente por ID
        model.addAttribute("paciente", paciente);
        return "editarPaciente";  // Vista para editar el paciente
    }

    @PostMapping("/editar/{id}")
    public String actualizarPaciente(@PathVariable("id") int id, @ModelAttribute("paciente") Pacientes paciente) {
        paciente.setIdPaciente(id);
        pacientesService.updatePaciente(paciente);
        return "redirect:/pacientes/listar";
    }

    // Eliminar un paciente
    @GetMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable("id") int id) {
        pacientesService.deletePaciente(id);
        return "redirect:/pacientes/listar";  // Redirigir a la lista después de eliminar
    }
}

