package com.cita.demo.controller;

import com.cita.demo.model.dto.CitaRequest;
import com.cita.demo.model.entity.CitaEntity;
import com.cita.demo.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/agendamiento")
@Tag(
        name = "API de Citas Médicas",
        description = "Procesamiento de reservas de consultas"
)
public class CitaController {

    private final CitaService service;

    public CitaController(CitaService service) {
        this.service = service;
    }

    @PostMapping("/confirmar")
    @Operation(
            description = "Reserva una cita validando paciente y disponibilidad médica"
    )
    public ResponseEntity<CitaEntity> confirmarCita(
            @RequestBody CitaRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.programarCita(request));
    }

    @GetMapping("/historial")
    @Operation(description = "Lista las citas registradas")
    public ResponseEntity<Iterable<CitaEntity>> historial() {

        return ResponseEntity.ok(
                service.obtenerCitas()
        );
    }
}