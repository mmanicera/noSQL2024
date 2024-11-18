package com.gestion.medica.HistorialMedico;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "registros_medicos") // Especifica la colección en MongoDB
public class RegistroMedico {

    // Atributos
    @Id
    private String id;             // Identificador único para cada registro (opcional, pero recomendado)
    private Date fecha;            // Fecha del registro médico
    private String tipo;           // Tipo de registro (Consulta, Examen, Internación)
    private String diagnostico;    // Diagnóstico realizado
    private String medico;         // Nombre del médico que atendió al paciente
    private String institucion;    // Institución donde se realizó la atención
    private String descripcion;    // Descripción adicional (opcional)
    private String medicacion;     // Medicación administrada (opcional)

    @DBRef
    private DatosPaciente paciente; // Se agrega la referencia al paciente
    
    // Adicionalmente, se puede agregar el CI del paciente, para facilitar las consultas
    private String pacienteCi;     // CI del paciente asociado al registro

    // Constructor vacío
    public RegistroMedico() {}

    // Constructor con todos los atributos
    public RegistroMedico(Date fecha, String tipo, String diagnostico, String medico, String institucion, 
                           String descripcion, String medicacion, DatosPaciente paciente) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.diagnostico = diagnostico;
        this.medico = medico;
        this.institucion = institucion;
        this.descripcion = descripcion;
        this.medicacion = medicacion;
        this.paciente = paciente;
        this.pacienteCi = paciente != null ? paciente.getCi() : null; // Inicializa el pacienteCi con la cédula
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMedicacion() {
        return medicacion;
    }

    public void setMedicacion(String medicacion) {
        this.medicacion = medicacion;
    }

    public DatosPaciente getPaciente() {
        return paciente;
    }

    public void setPaciente(DatosPaciente paciente) {
        this.paciente = paciente;
        this.pacienteCi = paciente != null ? paciente.getCi() : null; // Mantiene el pacienteCi sincronizado
    }

    public String getPacienteCi() {
        return pacienteCi;
    }

    public void setPacienteCi(String pacienteCi) {
        this.pacienteCi = pacienteCi;
    }
}
