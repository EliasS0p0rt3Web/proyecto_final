package com.paciente.demo.model.mapper;

import com.paciente.demo.model.dto.PacienteRequest;
import com.paciente.demo.model.dto.PacienteResponse;
import com.paciente.demo.model.entity.PacienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    PacienteEntity toEntity(PacienteRequest request);

    PacienteResponse toResponse(PacienteEntity entity);
}