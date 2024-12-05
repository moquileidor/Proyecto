package com.proyectopersonal.controller;

import com.proyectopersonal.model.*;
import com.proyectopersonal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/salidas")
public class SalidasPacientesController {


    @Autowired
    private SalidasPacientesService salidasPacientesService;

    @Autowired
    private IngresosPacientesService ingresosPacientesService;

    @Autowired
    private PacientesServicioService pacientesServicioService;

    @Autowired
    private PacientesService pacientesService;
    @Autowired
    private HabitacionesService habitacionesService;


    @GetMapping("/listar")
    public String listarSalidasPacientes(Model model) {
        List<SalidasPacientes> salidasPacientes  = salidasPacientesService.getAllSalidasPacientes();
        model.addAttribute("salidasPacientes", salidasPacientes);
        return "listarSalidasPacientes";
    }

    @GetMapping("/registrar")
    public String mostrarFormularioSalida(Model model) {
        // Obtener la lista de pacientes para popular el select
        List<Pacientes> pacientes = pacientesService.getAllPacientes();
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("salidaPaciente", new SalidasPacientes());
        return "registrarSalidaPaciente"; // nombre de tu vista de formulario
    }

    @PostMapping("/registrar")
    public String registrarSalidaPaciente(@RequestParam("idPaciente") int idPaciente, Model model) {
        // Obtener el paciente y sus servicios
        Pacientes paciente = pacientesService.getPacienteById(idPaciente);
        List<PacientesServicios> servicios = pacientesServicioService.getServiciosByPacienteId(idPaciente);

        // Calcular el total a pagar y el detalle de cobro
        double totalAPagar = servicios.stream().mapToDouble(PacientesServicios::getPrecioServicio).sum();
        String detalleCobro = servicios.stream().map(PacientesServicios::getNombreServicio).collect(Collectors.joining(", "));

        // Crear la salida del paciente
        SalidasPacientes salidaPaciente = new SalidasPacientes();
        salidaPaciente.setIdPaciente(idPaciente);
        salidaPaciente.setNombrePaciente(paciente.getNombre_paciente());
        salidaPaciente.setDetalleCobro(detalleCobro);
        salidaPaciente.setTotalAPagar(totalAPagar);

        // Guardar la salida del paciente
        salidasPacientesService.createSalidaPaciente(salidaPaciente);

        //Obtener el ingreso del paciente para actualizar la habitacion
        IngresosPacientes ingreso = ingresosPacientesService.getIngresoByPacienteId(idPaciente);
        Habitaciones habitacion = ingreso.getHabitacion();
        habitacion.setEstado("Disponible");
        habitacionesService.updateHabitacion(habitacion);

        // Eliminar el ingreso del paciente
        ingresosPacientesService.deleteIngresoByPacienteId(idPaciente);

        return "redirect:/salidas/listar";
    }
}
