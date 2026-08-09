package com.paciente.demo.repository;

import com.paciente.demo.model.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository
        extends JpaRepository<PacienteEntity, Integer> {

    boolean existsByDni(String dni);

    Optional<PacienteEntity> findByDni(String dni);
}