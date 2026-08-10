package com.especialidad.demo.repository;

import com.especialidad.demo.model.entity.EspecialidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EspecialidadRepository
        extends JpaRepository<EspecialidadEntity, Integer> {

    boolean existsByNombre(String nombre);

    Optional<EspecialidadEntity> findByNombre(String nombre);
}