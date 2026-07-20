package com.medico.demo.repository;

import com.medico.demo.model.entity.HorarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface HorarioRepository extends CrudRepository<HorarioEntity, Integer> {
    Optional<HorarioEntity> findByColegiaturaAndTurno(String colegiatura, String turno);
}