package com.especialidad.demo.model.mapper;

import com.especialidad.demo.model.dto.EspecialidadRequest;
import com.especialidad.demo.model.dto.EspecialidadResponse;
import com.especialidad.demo.model.entity.EspecialidadEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EspecialidadMapper {

    EspecialidadEntity toEntity(EspecialidadRequest request);

    EspecialidadResponse toResponse(EspecialidadEntity entity);
}