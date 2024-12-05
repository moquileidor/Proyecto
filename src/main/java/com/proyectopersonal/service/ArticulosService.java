package com.proyectopersonal.service;

import com.proyectopersonal.model.Articulos;
import com.proyectopersonal.model.Habitaciones;
import com.proyectopersonal.repository.ArticulosRepository;
import com.proyectopersonal.repository.HabitacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticulosService {

    @Autowired
    private ArticulosRepository articulosRepository;


    @Autowired
    private HabitacionesRepository habitacionesRepository;

    //Mostrar todos los articulos
    public List<Articulos> getAllArticulos(){
        return articulosRepository.findAll();
    }

    public void createArticulo(Articulos articulo) {
        Habitaciones habitacion = habitacionesRepository.findById(articulo.getHabitaciones().getNum_habitacion())
                .orElseThrow(() -> new IllegalArgumentException("La habitación con el número " + articulo.getHabitaciones().getNum_habitacion() + " no existe."));

        articulo.setHabitaciones(habitacion);
        articulosRepository.save(articulo);
    }

    public Articulos getArticuloByCodigo(int codigo_articulo){
        return articulosRepository.findById(codigo_articulo)
                .orElseThrow(() -> new RuntimeException("Articulo no encontrado"));
    }

    //Editar un articulo
    public Articulos updateArticulo(Articulos articulo){
        return articulosRepository.save(articulo);
    }

    //Eliminar un articulo
    public void deleteArticulo(int id){
        articulosRepository.deleteById(id);
    }
}
