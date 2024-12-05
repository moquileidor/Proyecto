package com.proyectopersonal.controller;

import com.proyectopersonal.model.Articulos;
import com.proyectopersonal.service.ArticulosService;
import com.proyectopersonal.service.HabitacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/articulos")
public class ArticuloController {

    @Autowired
    private ArticulosService articulosService;

    @Autowired
    private HabitacionesService habitacionesService;

    //Mostrar formulario para registrar un articulo
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model){
        model.addAttribute("articulo", new Articulos());
        model.addAttribute("habitaciones", habitacionesService.getAllHabitaciones());
        return "registrarArticulo";
    }

    @PostMapping("/guardar")
    public String registrarArticulo(@ModelAttribute("articulo") Articulos articulo, Model model) {
        try {
            // Verifica que el campo 'habitaciones' no esté vacío
            if (articulo.getHabitaciones() == null || articulo.getHabitaciones().getNum_habitacion() == 0) {
                model.addAttribute("error", "Debe seleccionar una habitación.");
                return "registrarArticulo";
            }

            // Llama al servicio para guardar el artículo con la habitación seleccionada
            articulosService.createArticulo(articulo);
            return "redirect:/articulos/listar";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "registrarArticulo";
        }
    }


    //Listar todos los articulos
    @GetMapping("/listar")
    public String listarArticulos(Model model){
        List<Articulos> articulos = articulosService.getAllArticulos();
        model.addAttribute("articulos", articulos);
        return "listarArticulos";
    }

    //Mostrar el formulario de edición de un articulo
    @GetMapping("/editar/{codigo_articulo}")
    public String mostrarFormularioEdicion(@PathVariable("codigo_articulo") int codigo_articulo, Model model){
        Articulos articulo = articulosService.getArticuloByCodigo(codigo_articulo);
        model.addAttribute("articulo", articulo);
        model.addAttribute("habitaciones", habitacionesService.getAllHabitaciones());
        return "editarArticulo";
    }

    //Editar un articulo
    @PostMapping("/editar/{codigo_articulo}")
    public String actualizarArticulo(@PathVariable("codigo_articulo") int codigo_articulo, @ModelAttribute("articulo") Articulos articulo){
        articulo.setCodigo_articulo(codigo_articulo);
        articulosService.updateArticulo(articulo);
        return "redirect:/articulos/listar";
    }

    //Eliminar un articulo
    @GetMapping("/eliminar/{codigo_articulo}")
    public String eliminarArticulo(@PathVariable("codigo_articulo") int codigo_articulo){
        articulosService.deleteArticulo(codigo_articulo);
        return "redirect:/articulos/listar";
    }

}
