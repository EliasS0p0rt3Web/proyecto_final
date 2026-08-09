package com.cita.demo.client;

import com.cita.demo.model.dto.PacienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "PacienteClient",
        url = "${PACIENTE_URL:http://localhost:8085/api/v1/pacientes}"
)
public interface PacienteClient {

    @GetMapping("/{id}")
    PacienteResponse obtenerPorId(
            @PathVariable("id") Integer id
    );
}