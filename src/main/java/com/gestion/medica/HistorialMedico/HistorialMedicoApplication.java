package com.gestion.medica.HistorialMedico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})  // Excluye la configuración de MongoDB
public class HistorialMedicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HistorialMedicoApplication.class, args);
    }

}
