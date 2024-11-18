package com.gestion.medica.HistorialMedico;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;
import org.springframework.data.domain.PageImpl;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public class RegistroMedicoService {

    @Autowired
    private RegistroMedicoRepository registroMedicoRepository;
    
    @Autowired
    private DatosPacienteService datosPacienteService;
    
    @Autowired
    private MongoTemplate mongoTemplate;

    // Guardar un registro médico asociado a un paciente
    public RegistroMedico guardarRegistroMedico(String ci, RegistroMedico registroMedico) {
        Optional<DatosPaciente> paciente = datosPacienteService.obtenerPacientePorCi(ci);
        if (!paciente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED, "No existe un paciente con la cédula aportada como parámetro");
        }
        // Se asocia el paciente con el registro
        registroMedico.setPaciente(paciente.get()); 
        return registroMedicoRepository.save(registroMedico);
    }

    // Obtener registros médicos asociados a un paciente (paginados)
    public Page<RegistroMedico> obtenerRegistrosMedicosPorPaciente(String ci, int page, int pageSize) {
        // Se verifica si el paciente existe
        Optional<DatosPaciente> paciente = datosPacienteService.obtenerPacientePorCi(ci);
        if (!paciente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED, "No existe un paciente con la cédula aportada como parámetro");
        }

        // Paginación
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("fecha"))); 

        // Retornamos un Page de registros médicos, paginados por la cédula del paciente
        return registroMedicoRepository.findByPacienteCi(ci, pageable);
    }

    // Obtener todos los registros médicos
    public List<RegistroMedico> obtenerTodosRegistrosMedicos() {
        return registroMedicoRepository.findAll();
    }

    // Obtener un registro médico por ID
    public Optional<RegistroMedico> obtenerRegistroMedicoPorId(String id) {
        return registroMedicoRepository.findById(id);
    }
    
    public Page<RegistroMedico> obtenerRegistrosPorCriterio(String tipo, String diagnostico, String medico, String institucion, int page, int size) {
        // Crea una página de paginación
        Pageable pageable = PageRequest.of(page - 1, size);
        
        // Crear la consulta
        Query query = new Query();
        
        if (tipo != null) {
            query.addCriteria(Criteria.where("tipo").is(tipo));
        }
        if (diagnostico != null) {
            query.addCriteria(Criteria.where("diagnostico").is(diagnostico));
        }
        if (medico != null) {
            query.addCriteria(Criteria.where("medico").is(medico));
        }
        if (institucion != null) {
            query.addCriteria(Criteria.where("institucion").is(institucion));
        }
        
        // Establecer el límite y el desplazamiento (skip y limit) según la paginación
        query.with(pageable);
        
        // Ejecutar la consulta y devolver los resultados paginados
        long total = mongoTemplate.count(query, RegistroMedico.class); // Obtén el total de registros

        List<RegistroMedico> registros = mongoTemplate.find(query, RegistroMedico.class);

        // Convertir la lista a un objeto Page
        return new PageImpl<>(registros, pageable, total);
    }
}
