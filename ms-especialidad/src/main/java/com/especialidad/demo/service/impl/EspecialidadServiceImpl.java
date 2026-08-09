package com.especialidad.demo.service.impl;

import com.especialidad.demo.model.dto.EspecialidadRequest;
import com.especialidad.demo.model.dto.EspecialidadResponse;
import com.especialidad.demo.model.entity.EspecialidadEntity;
import com.especialidad.demo.model.mapper.EspecialidadMapper;
import com.especialidad.demo.repository.EspecialidadRepository;
import com.especialidad.demo.service.EspecialidadService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    private final EspecialidadRepository repository;
    private final EspecialidadMapper mapper;

    public EspecialidadServiceImpl(
            EspecialidadRepository repository,
            EspecialidadMapper mapper) {

        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EspecialidadResponse registrar(
            EspecialidadRequest request) {

        validarDatos(request);

        if (repository.existsByNombre(request.getNombre())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "La especialidad ya se encuentra registrada"
            );
        }

        EspecialidadEntity especialidad =
                mapper.toEntity(request);

        EspecialidadEntity guardada =
                repository.save(especialidad);

        return mapper.toResponse(guardada);
    }

    @Override
    public List<EspecialidadResponse> listar() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public EspecialidadResponse obtenerPorId(Integer id) {

        EspecialidadEntity especialidad =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Especialidad no encontrada"
                                ));

        return mapper.toResponse(especialidad);
    }

    @Override
    public EspecialidadResponse actualizar(
            Integer id,
            EspecialidadRequest request) {

        validarDatos(request);

        EspecialidadEntity especialidad =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Especialidad no encontrada"
                                ));

        repository.findByNombre(request.getNombre())
                .filter(e -> !e.getId().equals(id))
                .ifPresent(e -> {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "La especialidad ya se encuentra registrada"
                    );
                });

        especialidad.setNombre(request.getNombre());
        especialidad.setDescripcion(request.getDescripcion());

        return mapper.toResponse(
                repository.save(especialidad)
        );
    }

    private void validarDatos(EspecialidadRequest request) {

        if (request.getNombre() == null
                || request.getNombre().isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre de la especialidad es obligatorio"
            );
        }
    }
}