package com.notificacion.demo.model.mapper;

import com.notificacion.demo.model.dto.NotificacionRequest;
import com.notificacion.demo.model.dto.NotificacionResponse;
import com.notificacion.demo.model.entity.NotificacionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificacionMapper {

    NotificacionEntity toEntity(NotificacionRequest request);

    NotificacionResponse toResponse(NotificacionEntity entity);
}