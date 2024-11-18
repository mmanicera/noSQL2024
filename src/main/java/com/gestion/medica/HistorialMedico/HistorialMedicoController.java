package com.gestion.medica.HistorialMedico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HistorialMedicoController {

    // Inyecta el servicio para interactuar con MongoDB
    @Autowired
    private DatosPacienteService datosPacienteService;

    @Autowired
    private RegistroMedicoService registroMedicoService;

    // Endpoint para crear un nuevo paciente (POST)
    @PostMapping("/pacientes")
    @ResponseStatus(HttpStatus.CREATED)
    public DatosPaciente crearPaciente(@RequestBody DatosPaciente datosPaciente) {
        if (datosPacienteService.existePacientePorCi(datosPaciente.getCi())) {
            throw new PacienteYaExisteException("El paciente ya existe");
        }
        return datosPacienteService.guardarPaciente(datosPaciente);
    }

    // Endpoint para obtener todos los pacientes (GET)
    @GetMapping("/pacientes")
    public List<DatosPaciente> obtenerPacientes() {
        return datosPacienteService.obtenerTodosPacientes();
    }

    // Endpoint para obtener un paciente por su CI (GET)
    @GetMapping("/pacientes/{ci}")
    public Optional<DatosPaciente> obtenerPacientePorCi(@PathVariable String ci) {
        return datosPacienteService.obtenerPacientePorCi(ci);
    }

    // Endpoint para obtener todos los registros médicos (GET)
    @GetMapping("/registros")
    public List<RegistroMedico> obtenerRegistrosMedicos() {
        return registroMedicoService.obtenerTodosRegistrosMedicos();
    }

    // Endpoint para obtener un registro médico por ID (GET)
    @GetMapping("/registros/{id}")
    public Optional<RegistroMedico> obtenerRegistroMedicoPorId(@PathVariable String id) {
        return registroMedicoService.obtenerRegistroMedicoPorId(id);
    }

 // **Nuevo Endpoint para consultar historial médico con paginación**
    @GetMapping("/historiales")
    public Page<RegistroMedico> consultarHistorialMedico(
            @RequestParam String ci,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        // Verificar si el paciente existe
        Optional<DatosPaciente> paciente = datosPacienteService.obtenerPacientePorCi(ci);
        if (!paciente.isPresent()) {
            throw new PacienteNoEncontradoException("No existe un paciente con la cédula aportada como parámetro");
        }

        // Llamar al servicio para obtener los registros médicos paginados
        return registroMedicoService.obtenerRegistrosMedicosPorPaciente(ci, page, pageSize);
    }
    
    @PostMapping("/registros")
    @ResponseStatus(HttpStatus.CREATED)
    public RegistroMedico crearRegistroMedico(@RequestParam String ci, @RequestBody RegistroMedico registroMedico) {
        Optional<DatosPaciente> paciente = datosPacienteService.obtenerPacientePorCi(ci);
        if (!paciente.isPresent()) {
            throw new PacienteNoEncontradoException("No existe un paciente con la cédula aportada como parámetro");
        }
        return registroMedicoService.guardarRegistroMedico(ci, registroMedico);
    }
    
    @GetMapping("/registros/criterio")
    public ResponseEntity<Page<RegistroMedico>> obtenerRegistrosPorCriterio(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String diagnostico,
            @RequestParam(required = false) String medico,
            @RequestParam(required = false) String institucion,
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int size) {
        
        // Llama al servicio para obtener los registros filtrados por los criterios proporcionados
        Page<RegistroMedico> registros = registroMedicoService.obtenerRegistrosPorCriterio(tipo, diagnostico, medico, institucion, page, size);

        if (registros.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(registros);
        }
    }
}
