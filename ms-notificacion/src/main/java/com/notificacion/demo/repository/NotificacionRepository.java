package com.notificacion.demo.repository;

import com.notificacion.demo.model.entity.NotificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionRepository
        extends JpaRepository<NotificacionEntity, Integer> {

    List<NotificacionEntity> findByPacienteId(Integer pacienteId);
}