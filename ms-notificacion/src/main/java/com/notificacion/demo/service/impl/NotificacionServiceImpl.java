package com.notificacion.demo.service.impl;

import com.notificacion.demo.model.dto.NotificacionRequest;
import com.notificacion.demo.model.dto.NotificacionResponse;
import com.notificacion.demo.model.entity.NotificacionEntity;
import com.notificacion.demo.model.mapper.NotificacionMapper;
import com.notificacion.demo.repository.NotificacionRepository;
import com.notificacion.demo.service.NotificacionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    private final NotificacionRepository repository;
    private final NotificacionMapper mapper;

    public NotificacionServiceImpl(
            NotificacionRepository repository,
            NotificacionMapper mapper) {

        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public NotificacionResponse registrar(
            NotificacionRequest request) {

        validarDatos(request);

        NotificacionEntity notificacion =
                mapper.toEntity(request);

        notificacion.setFecha(LocalDateTime.now());

        NotificacionEntity guardada =
                repository.save(notificacion);

        return mapper.toResponse(guardada);
    }

    @Override
    public List<NotificacionResponse> listar() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<NotificacionResponse> listarPorPaciente(
            Integer pacienteId) {

        return repository.findByPacienteId(pacienteId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    private void validarDatos(NotificacionRequest request) {

        if (request.getPacienteId() == null
                || request.getCitaId() == null
                || request.getMensaje() == null
                || request.getMensaje().isBlank()
                || request.getTipo() == null
                || request.getTipo().isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Paciente, cita, mensaje y tipo son obligatorios"
            );
        }
    }
}