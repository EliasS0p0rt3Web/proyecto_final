package com.cita.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MedicoClient", url = "http://localhost:8083/api/v1/horarios-medicos")
public interface MedicoClient {

    @GetMapping("/validar-turno")
    boolean validarTurno(@RequestParam("colegiatura") String colegiatura, @RequestParam("turno") String turno);
}