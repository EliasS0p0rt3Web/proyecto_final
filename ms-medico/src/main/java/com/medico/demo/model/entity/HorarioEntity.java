package com.medico.demo.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_horario_medico")
public class HorarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String colegiatura;
    private String especialidad;
    private String turno;

    public HorarioEntity() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getColegiatura() { return colegiatura; }
    public void setColegiatura(String colegiatura) { this.colegiatura = colegiatura; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }
}