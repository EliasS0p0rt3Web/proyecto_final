package com.paciente.demo.controller;

import com.paciente.demo.model.dto.PacienteRequest;
import com.paciente.demo.model.dto.PacienteResponse;
import com.paciente.demo.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
@Tag(
        name = "API de Pacientes",
        description = "Gestión de pacientes del sistema de citas médicas"
)
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(description = "Registra un nuevo paciente")
    public ResponseEntity<PacienteResponse> registrar(
            @RequestBody PacienteRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.registrar(request));
    }

    @GetMapping
    @Operation(description = "Lista todos los pacientes")
    public ResponseEntity<List<PacienteResponse>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(description = "Obtiene un paciente por su identificador")
    public ResponseEntity<PacienteResponse> obtenerPorId(
            @PathVariable Integer id) {

        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(description = "Actualiza los datos de un paciente")
    public ResponseEntity<PacienteResponse> actualizar(
            @PathVariable Integer id,
            @RequestBody PacienteRequest request) {

        return ResponseEntity.ok(
                service.actualizar(id, request)
        );
    }
}