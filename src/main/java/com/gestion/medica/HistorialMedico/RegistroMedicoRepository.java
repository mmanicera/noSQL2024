package com.gestion.medica.HistorialMedico;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroMedicoRepository extends MongoRepository<RegistroMedico, String> {

    Page<RegistroMedico> findByPacienteCi(String ci, Pageable pageable);
    
    Page<RegistroMedico> findByTipoAndDiagnosticoAndMedicoAndInstitucion(
            String tipo, String diagnostico, String medico, String institucion, Pageable pageable);
}
