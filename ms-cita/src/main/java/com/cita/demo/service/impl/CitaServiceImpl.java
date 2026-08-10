package com.cita.demo.service.impl;

import com.cita.demo.client.MedicoClient;
import com.cita.demo.client.NotificacionClient;
import com.cita.demo.client.PacienteClient;
import com.cita.demo.model.dto.CitaRequest;
import com.cita.demo.model.dto.NotificacionRequest;
import com.cita.demo.model.entity.CitaEntity;
import com.cita.demo.repository.CitaRepository;
import com.cita.demo.service.CitaService;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository repository;
    private final MedicoClient medicoClient;
    private final PacienteClient pacienteClient;
    private final NotificacionClient notificacionClient;

    public CitaServiceImpl(
            CitaRepository repository,
            MedicoClient medicoClient,
            PacienteClient pacienteClient,
            NotificacionClient notificacionClient) {

        this.repository = repository;
        this.medicoClient = medicoClient;
        this.pacienteClient = pacienteClient;
        this.notificacionClient = notificacionClient;
    }

    @Override
    public CitaEntity programarCita(CitaRequest dto) {

        validarDatos(dto);
        try {

            pacienteClient.obtenerPorId(dto.getPacienteId());

        } catch (FeignException.NotFound e) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "El paciente indicado no existe"
            );
        }

        boolean medicoDisponible =
                medicoClient.validarTurno(
                        dto.getColegiaturaMedico(),
                        dto.getTurnoCita()
                );

        if (!medicoDisponible) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El médico no cuenta con disponibilidad para el turno solicitado"
            );
        }

        CitaEntity entity = new CitaEntity();

        entity.setPacienteId(dto.getPacienteId());
        entity.setColegiaturaMedico(dto.getColegiaturaMedico());
        entity.setTurnoCita(dto.getTurnoCita());
        entity.setCostoConsulta(dto.getCostoConsulta());

        CitaEntity citaGuardada =
                repository.save(entity);

        NotificacionRequest notificacion =
                new NotificacionRequest();

        notificacion.setPacienteId(dto.getPacienteId());
        notificacion.setCitaId(citaGuardada.getId());
        notificacion.setTipo("CONFIRMACION");
        notificacion.setMensaje(
                "Su cita médica fue confirmada para el turno "
                        + dto.getTurnoCita()
        );

        notificacionClient.registrar(notificacion);

        return citaGuardada;
    }

    @Override
    public Iterable<CitaEntity> obtenerCitas() {
        return repository.findAll();
    }

    private void validarDatos(CitaRequest dto) {

        if (dto.getPacienteId() == null
                || dto.getColegiaturaMedico() == null
                || dto.getColegiaturaMedico().isBlank()
                || dto.getTurnoCita() == null
                || dto.getTurnoCita().isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Paciente, médico y turno son obligatorios"
            );
        }

        if (dto.getCostoConsulta() <= 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El costo de la consulta debe ser mayor a cero"
            );
        }
    }
}