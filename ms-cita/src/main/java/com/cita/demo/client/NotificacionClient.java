package com.cita.demo.client;

import com.cita.demo.model.dto.NotificacionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "NotificacionClient",
        url = "${NOTIFICACION_URL:http://localhost:8087/api/v1/notificaciones}"
)
public interface NotificacionClient {

    @PostMapping
    void registrar(
            @RequestBody NotificacionRequest request
    );
}