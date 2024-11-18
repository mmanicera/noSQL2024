package com.gestion.medica.HistorialMedico;

public class PacienteNoEncontradoException extends RuntimeException {
    public PacienteNoEncontradoException(String message) {
        super(message);
    }
}