package com.especialidad.demo.controller;

import com.especialidad.demo.model.dto.EspecialidadRequest;
import com.especialidad.demo.model.dto.EspecialidadResponse;
import com.especialidad.demo.service.EspecialidadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/especialidades")
@Tag(
        name = "API de Especialidades",
        description = "Gestión de especialidades médicas"
)
public class EspecialidadController {

    private final EspecialidadService service;

    public EspecialidadController(
            EspecialidadService service) {

        this.service = service;
    }

    @PostMapping
    @Operation(description = "Registra una nueva especialidad")
    public ResponseEntity<EspecialidadResponse> registrar(
            @RequestBody EspecialidadRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.registrar(request));
    }

    @GetMapping
    @Operation(description = "Lista las especialidades")
    public ResponseEntity<List<EspecialidadResponse>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(description = "Obtiene una especialidad por ID")
    public ResponseEntity<EspecialidadResponse> obtenerPorId(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                service.obtenerPorId(id)
        );
    }

    @PutMapping("/{id}")
    @Operation(description = "Actualiza una especialidad")
    public ResponseEntity<EspecialidadResponse> actualizar(
            @PathVariable Integer id,
            @RequestBody EspecialidadRequest request) {

        return ResponseEntity.ok(
                service.actualizar(id, request)
        );
    }
}