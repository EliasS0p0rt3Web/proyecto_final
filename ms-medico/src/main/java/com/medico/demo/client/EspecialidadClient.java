package com.medico.demo.client;

import com.medico.demo.model.dto.EspecialidadResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "EspecialidadClient",
        url = "${ESPECIALIDAD_URL:http://localhost:8086/api/v1/especialidades}"
)
public interface EspecialidadClient {

    @GetMapping("/{id}")
    EspecialidadResponse obtenerPorId(
            @PathVariable("id") Integer id
    );
}