package com.paciente.demo.service;

import com.paciente.demo.model.dto.PacienteRequest;
import com.paciente.demo.model.dto.PacienteResponse;

import java.util.List;

public interface PacienteService {

    PacienteResponse registrar(PacienteRequest request);

    List<PacienteResponse> listar();

    PacienteResponse obtenerPorId(Integer id);

    PacienteResponse actualizar(Integer id, PacienteRequest request);
}