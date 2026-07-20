package com.cita.demo.repository;

import com.cita.demo.model.entity.CitaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends CrudRepository<CitaEntity, Integer> {
}