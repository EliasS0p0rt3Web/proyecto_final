package com.especialidad.demo.service;

import com.especialidad.demo.model.dto.EspecialidadRequest;
import com.especialidad.demo.model.dto.EspecialidadResponse;

import java.util.List;

public interface EspecialidadService {

    EspecialidadResponse registrar(EspecialidadRequest request);

    List<EspecialidadResponse> listar();

    EspecialidadResponse obtenerPorId(Integer id);

    EspecialidadResponse actualizar(
            Integer id,
            EspecialidadRequest request
    );
}