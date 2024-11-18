package com.gestion.medica.HistorialMedico;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatosPacienteRepository extends MongoRepository<DatosPaciente, String> {
    // MongoRepository ya tiene implementados los métodos básicos como save, findById, findAll, etc.
}
