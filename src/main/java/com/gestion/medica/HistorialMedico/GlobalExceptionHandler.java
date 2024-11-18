package com.gestion.medica.HistorialMedico;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PacienteYaExisteException.class)
    public ResponseEntity<String> manejarPacienteYaExisteException(PacienteYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El paciente ya existe");
    }

    @ExceptionHandler(PacienteNoEncontradoException.class)
    public ResponseEntity<String> manejarPacienteNoEncontradoException(PacienteNoEncontradoException ex) {
        return ResponseEntity.status(402).body("No existe un paciente con la cédula aportada como parámetro");
    }
    
    // Otros manejadores de excepciones según sea necesario
}