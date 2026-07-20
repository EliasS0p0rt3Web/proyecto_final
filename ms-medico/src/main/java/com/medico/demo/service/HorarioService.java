package com.medico.demo.service;

import com.medico.demo.model.dto.HorarioRequest;
import com.medico.demo.model.dto.HorarioResponse;

public interface HorarioService {
    void registrarHorario(HorarioRequest request);
    Iterable<HorarioResponse> obtenerHorarios();
    boolean verificarDisponibilidad(String colegiatura, String turno);
}