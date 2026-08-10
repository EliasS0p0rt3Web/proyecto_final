package com.paciente.demo.service.impl;

import com.paciente.demo.model.dto.PacienteRequest;
import com.paciente.demo.model.dto.PacienteResponse;
import com.paciente.demo.model.entity.PacienteEntity;
import com.paciente.demo.model.mapper.PacienteMapper;
import com.paciente.demo.repository.PacienteRepository;
import com.paciente.demo.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository repository;
    private final PacienteMapper mapper;

    public PacienteServiceImpl(PacienteRepository repository,
                               PacienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PacienteResponse registrar(PacienteRequest request) {

        validarDatos(request);

        if (repository.existsByDni(request.getDni())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un paciente con el DNI ingresado"
            );
        }

        PacienteEntity paciente = mapper.toEntity(request);
        PacienteEntity guardado = repository.save(paciente);

        return mapper.toResponse(guardado);
    }

    @Override
    public List<PacienteResponse> listar() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public PacienteResponse obtenerPorId(Integer id) {

        PacienteEntity paciente = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Paciente no encontrado"
                ));

        return mapper.toResponse(paciente);
    }

    @Override
    public PacienteResponse actualizar(Integer id,
                                       PacienteRequest request) {

        validarDatos(request);

        PacienteEntity paciente = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Paciente no encontrado"
                ));

        repository.findByDni(request.getDni())
                .filter(existente -> !existente.getId().equals(id))
                .ifPresent(existente -> {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "El DNI pertenece a otro paciente"
                    );
                });

        paciente.setDni(request.getDni());
        paciente.setNombres(request.getNombres());
        paciente.setApellidos(request.getApellidos());
        paciente.setTelefono(request.getTelefono());
        paciente.setEmail(request.getEmail());

        PacienteEntity actualizado = repository.save(paciente);

        return mapper.toResponse(actualizado);
    }

    private void validarDatos(PacienteRequest request) {

        if (request.getDni() == null || request.getDni().isBlank()
                || request.getNombres() == null || request.getNombres().isBlank()
                || request.getApellidos() == null || request.getApellidos().isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "DNI, nombres y apellidos son obligatorios"
            );
        }
    }
}