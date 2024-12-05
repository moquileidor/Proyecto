package com.proyectopersonal.service;

import com.proyectopersonal.model.Servicios;
import com.proyectopersonal.repository.ServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiciosService {

    @Autowired
    private ServiciosRepository serviciosRepository;

    public List<Servicios> getAllServicios(){
        return serviciosRepository.findAll();
    }

    public Servicios getServiciosByNombre(String nombreServicio){
        return serviciosRepository.findByNombreServicio(nombreServicio);
    }

    public Servicios createServicio(Servicios servicio){
        return serviciosRepository.save(servicio);
    }

    public Servicios getServicioById(int id){
        return serviciosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
    }

    public Servicios updateServicio(Servicios servicio){
        return serviciosRepository.save(servicio);
    }

    public void deleteServicio(int id){
        serviciosRepository.deleteById(id);
    }

}
