package com.gestion.medica.HistorialMedico;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List;

@Document(collection = "historiales_medicos") // Especifica la colección en MongoDB
public class HistorialMedico {

    @Id
    private String id;                          // Identificador único para el historial médico
    @DBRef
    private DatosPaciente paciente;             // Relación con la clase DatosPaciente
    @DBRef
    private List<RegistroMedico> registros;     // Relación con la lista de registros médicos

    // Constructor vacío
    public HistorialMedico() {}

    // Constructor con todos los atributos
    public HistorialMedico(DatosPaciente paciente, List<RegistroMedico> registros) {
        this.paciente = paciente;
        this.registros = registros;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DatosPaciente getPaciente() {
        return paciente;
    }

    public void setPaciente(DatosPaciente paciente) {
        this.paciente = paciente;
    }

    public List<RegistroMedico> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RegistroMedico> registros) {
        this.registros = registros;
    }
}
