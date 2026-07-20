package com.medico.demo.service.impl;

import com.medico.demo.model.dto.HorarioRequest;
import com.medico.demo.model.dto.HorarioResponse;
import com.medico.demo.model.mapper.HorarioMapper;
import com.medico.demo.repository.HorarioRepository;
import com.medico.demo.service.HorarioService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class HorarioServiceImpl implements HorarioService {

    private final HorarioRepository repository;

    public HorarioServiceImpl(HorarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registrarHorario(HorarioRequest request) {
        var entity = HorarioMapper.MAPPER.toHorarioEntity(request);
        repository.save(entity);
    }

    @Override
    public Iterable<HorarioResponse> obtenerHorarios() {
        var horarios = repository.findAll();
        var lista = new ArrayList<HorarioResponse>();
        horarios.forEach(x -> lista.add(HorarioMapper.MAPPER.toHorarioResponse(x)));
        return lista;
    }

    @Override
    public boolean verificarDisponibilidad(String colegiatura, String turno) {
        return repository.findByColegiaturaAndTurno(colegiatura, turno).isPresent();
    }
}