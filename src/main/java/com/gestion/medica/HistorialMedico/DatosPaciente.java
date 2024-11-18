package com.gestion.medica.HistorialMedico;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "pacientes") // Especifica la colección en MongoDB
public class DatosPaciente {

    // Atributos
    @Id
    private String ci;            // CI del paciente (clave única)
    private String nombre;        // Nombre del paciente
    private String apellido;      // Apellido del paciente
    private Date fechaNacimiento; // Fecha de nacimiento del paciente
    private String sexo;          // Sexo del paciente

    // Constructor vacío
    public DatosPaciente() {}

    // Constructor con todos los atributos
    public DatosPaciente(String ci, String nombre, String apellido, Date fechaNacimiento, String sexo) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }

    // Getters y Setters
    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
