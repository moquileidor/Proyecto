package com.proyectopersonal.service;

import com.proyectopersonal.model.Medicos;
import com.proyectopersonal.repository.MedicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MedicosService {

    @Autowired
    private MedicosRepository medicosRepository;
    //
    @Autowired
    private CitasService citasService;

    //Mostrar todos los medicos
    public List<Medicos> getAllMedicos(){
        return medicosRepository.findAll();
    }

    //Crear un medico
    public Medicos createMedico(Medicos medico){
        Optional<Medicos> extisteMedico = medicosRepository.findById(medico.getId_medico());
        if(extisteMedico.isPresent()){
            throw new IllegalArgumentException("Medico con ID " + medico.getId_medico() + " ya existe.");
        }
        return medicosRepository.save(medico);
    }

    //Obtener un medico por su ID
    public Medicos getMedicoById(int id){
        Optional<Medicos> medico = medicosRepository.findById(id);
        if(!medico.isPresent()){
            throw new IllegalArgumentException("Medico con ID " + id + " no encontrado.");
        }
        return medico.get();
    }

    public Medicos updateMedico(Medicos medico){
        Medicos medicoExistente = getMedicoById(medico.getId_medico());
        // Actualizar solo los campos necesarios
        medicoExistente.setNombre_medico(medico.getNombre_medico());
        medicoExistente.setEdad_medico(medico.getEdad_medico());
        medicoExistente.setTelefono_medico(medico.getTelefono_medico());
        medicoExistente.setFecha_nacimiento_medico(medico.getFecha_nacimiento_medico());
        
        Medicos updatedMedico = medicosRepository.save(medicoExistente);
        citasService.updateNombreMedicoInCitas(medico.getId_medico(), medico.getNombre_medico());
        return updatedMedico;
    }

    //Eliminar un medico
    public void deleteMedico(int id){
        if (!medicosRepository.existsById(id)) {
            throw new IllegalArgumentException("Medico con ID " + id + " no existe.");
        }
        medicosRepository.deleteById(id);
    }
}
