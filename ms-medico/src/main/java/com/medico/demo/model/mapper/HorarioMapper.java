package com.medico.demo.model.mapper;

import com.medico.demo.model.dto.HorarioRequest;
import com.medico.demo.model.dto.HorarioResponse;
import com.medico.demo.model.entity.HorarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HorarioMapper {
    HorarioMapper MAPPER = Mappers.getMapper(HorarioMapper.class);
    HorarioResponse toHorarioResponse(HorarioEntity entity);
    HorarioEntity toHorarioEntity(HorarioRequest request);
}