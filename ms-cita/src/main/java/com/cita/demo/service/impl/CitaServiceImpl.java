package com.cita.demo.service.impl;

import com.cita.demo.client.MedicoClient;
import com.cita.demo.model.dto.CitaRequest;
import com.cita.demo.model.entity.CitaEntity;
import com.cita.demo.repository.CitaRepository;
import com.cita.demo.service.CitaService;
import org.springframework.stereotype.Service;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository repository;
    private final MedicoClient medicoClient;

    public CitaServiceImpl(CitaRepository repository, MedicoClient medicoClient) {
        this.repository = repository;
        this.medicoClient = medicoClient;
    }

    @Override
    public void programarCita(CitaRequest dto) {
        // Comunicación cruzada real vía Feign usando los nuevos métodos
        boolean medicoDisponible = medicoClient.validarTurno(dto.getColegiaturaMedico(), dto.getTurnoCita());

        if (!medicoDisponible) {
            throw new RuntimeException("Error: El especialista no cuenta con agenda programada para el turno solicitado.");
        }

        CitaEntity entity = new CitaEntity();
        entity.setPaciente(dto.getPaciente());
        entity.setColegiaturaMedico(dto.getColegiaturaMedico());
        entity.setTurnoCita(dto.getTurnoCita());
        entity.setCostoConsulta(dto.getCostoConsulta());

        repository.save(entity);
    }

    @Override
    public Iterable<CitaEntity> obtenerCitas() {
        return repository.findAll();
    }
}