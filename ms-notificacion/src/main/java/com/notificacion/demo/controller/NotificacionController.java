package com.notificacion.demo.controller;

import com.notificacion.demo.model.dto.NotificacionRequest;
import com.notificacion.demo.model.dto.NotificacionResponse;
import com.notificacion.demo.service.NotificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
@Tag(
        name = "API de Notificaciones",
        description = "Gestión de notificaciones de citas médicas"
)
public class NotificacionController {

    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(description = "Registra una notificación")
    public ResponseEntity<NotificacionResponse> registrar(
            @RequestBody NotificacionRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.registrar(request));
    }

    @GetMapping
    @Operation(description = "Lista todas las notificaciones")
    public ResponseEntity<List<NotificacionResponse>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/paciente/{pacienteId}")
    @Operation(description = "Lista las notificaciones de un paciente")
    public ResponseEntity<List<NotificacionResponse>> listarPorPaciente(
            @PathVariable Integer pacienteId) {

        return ResponseEntity.ok(
                service.listarPorPaciente(pacienteId)
        );
    }
}