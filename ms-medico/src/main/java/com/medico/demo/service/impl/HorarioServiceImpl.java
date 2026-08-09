package com.medico.demo.service.impl;

import com.medico.demo.client.EspecialidadClient;
import com.medico.demo.model.dto.HorarioRequest;
import com.medico.demo.model.dto.HorarioResponse;
import com.medico.demo.model.mapper.HorarioMapper;
import com.medico.demo.repository.HorarioRepository;
import com.medico.demo.service.HorarioService;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
public class HorarioServiceImpl implements HorarioService {

    private final HorarioRepository repository;
    private final EspecialidadClient especialidadClient;

    public HorarioServiceImpl(
            HorarioRepository repository,
            EspecialidadClient especialidadClient) {

        this.repository = repository;
        this.especialidadClient = especialidadClient;
    }

    @Override
    public void registrarHorario(HorarioRequest request) {

        if (request.getEspecialidadId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La especialidad es obligatoria"
            );
        }

        try {

            especialidadClient.obtenerPorId(
                    request.getEspecialidadId()
            );

        } catch (FeignException.NotFound e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La especialidad indicada no existe"
            );
        }

        var entity =
                HorarioMapper.MAPPER.toHorarioEntity(request);

        repository.save(entity);
    }

    @Override
    public Iterable<HorarioResponse> obtenerHorarios() {

        var horarios = repository.findAll();

        var lista = new ArrayList<HorarioResponse>();

        horarios.forEach(
                x -> lista.add(
                        HorarioMapper.MAPPER.toHorarioResponse(x)
                )
        );

        return lista;
    }

    @Override
    public boolean verificarDisponibilidad(
            String colegiatura,
            String turno) {

        return repository
                .findByColegiaturaAndTurno(
                        colegiatura,
                        turno
                )
                .isPresent();
    }
}