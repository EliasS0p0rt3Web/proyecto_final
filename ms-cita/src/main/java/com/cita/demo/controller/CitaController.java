package com.cita.demo.controller;

import com.cita.demo.model.dto.CitaRequest;
import com.cita.demo.model.entity.CitaEntity;
import com.cita.demo.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/agendamiento")
@Tag(name = "API de Citas Médicas", description = "Procesamiento de reservas de consultas")
public class CitaController {

    private final CitaService service;

    public CitaController(CitaService service) {
        this.service = service;
    }

    @PostMapping("/confirmar")
    @Operation(description = "Reserva una cita médica validando el horario del doctor en tiempo real")
    public void confirmarCita(@RequestBody CitaRequest request) {
        service.programarCita(request);
    }

    @GetMapping("/historial")
    public Iterable<CitaEntity> historial() {
        return service.obtenerCitas();
    }
}