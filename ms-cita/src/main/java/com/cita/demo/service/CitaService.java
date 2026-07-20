package com.cita.demo.service;

import com.cita.demo.model.dto.CitaRequest;
import com.cita.demo.model.entity.CitaEntity;

public interface CitaService {
    void programarCita(CitaRequest request);
    Iterable<CitaEntity> obtenerCitas();
}