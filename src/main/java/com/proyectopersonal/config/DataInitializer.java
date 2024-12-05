package com.proyectopersonal.config;

import com.proyectopersonal.model.Servicios;
import com.proyectopersonal.repository.ServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Autowired
    private ServiciosRepository serviciosRepository;


    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (!serviciosRepository.existsById(1)) {
                Servicios servicio1 = new Servicios();
                servicio1.setIdServicio(1);
                servicio1.setNombreServicio("Consulta General");
                servicio1.setPrecioServicio(10000);
                serviciosRepository.save(servicio1);
            }
            if (!serviciosRepository.existsById(2)) {
                Servicios servicio2 = new Servicios();
                servicio2.setIdServicio(2);
                servicio2.setNombreServicio("Consulta Especializada");
                servicio2.setPrecioServicio(20000);
                serviciosRepository.save(servicio2);
            }

            if (!serviciosRepository.existsById(3)) {
                Servicios servicio3 = new Servicios();
                servicio3.setIdServicio(3);
                servicio3.setNombreServicio("Consulta Domiciliaria");
                servicio3.setPrecioServicio(30000);
                serviciosRepository.save(servicio3);
            }


        };
    }
}
