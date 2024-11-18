package com.gestion.medica.HistorialMedico;

public class PacienteYaExisteException extends RuntimeException {
    public PacienteYaExisteException(String message) {
        super(message);
    }
}