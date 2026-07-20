package com.medico.demo.controller;

import com.medico.demo.model.dto.HorarioRequest;
import com.medico.demo.model.dto.HorarioResponse;
import com.medico.demo.service.HorarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/horarios-medicos")
@Tag(name = "API de Horarios Médicos", description = "Gestión de agendas y turnos de especialistas")
public class HorarioController {

    private final HorarioService service;

    public HorarioController(HorarioService service) {
        this.service = service;
    }

    @PostMapping("/guardar")
    @Operation(description = "Registra el turno disponible de un médico especialista")
    public void registrar(@RequestBody HorarioRequest request) {
        service.registrarHorario(request);
    }

    @GetMapping("/validar-turno")
    public boolean validarTurno(@RequestParam("colegiatura") String colegiatura, @RequestParam("turno") String turno) {
        return service.verificarDisponibilidad(colegiatura, turno);
    }
}