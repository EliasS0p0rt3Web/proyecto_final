package com.notificacion.demo.service;

import com.notificacion.demo.model.dto.NotificacionRequest;
import com.notificacion.demo.model.dto.NotificacionResponse;

import java.util.List;

public interface NotificacionService {

    NotificacionResponse registrar(NotificacionRequest request);

    List<NotificacionResponse> listar();

    List<NotificacionResponse> listarPorPaciente(Integer pacienteId);
}