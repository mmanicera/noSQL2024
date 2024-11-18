package com.gestion.medica.HistorialMedico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DatosPacienteService {

    @Autowired
    private DatosPacienteRepository datosPacienteRepository;

    // Verificar si un paciente ya existe por CI
    public boolean existePacientePorCi(String ci) {
        return datosPacienteRepository.existsById(ci);
    }

    // Guardar paciente (si no existe)
    public DatosPaciente guardarPaciente(DatosPaciente datosPaciente) {
        if (existePacientePorCi(datosPaciente.getCi())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "El paciente ya existe");
        }
        return datosPacienteRepository.save(datosPaciente);
    }

    // Obtener un paciente por CI
    public Optional<DatosPaciente> obtenerPacientePorCi(String ci) {
        return datosPacienteRepository.findById(ci);
    }

    // Obtener todos los pacientes
    public List<DatosPaciente> obtenerTodosPacientes() {
        return datosPacienteRepository.findAll();
    }
}

