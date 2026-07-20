package com.medico.demo.model.dto;

public class HorarioRequest {
    private String colegiatura;
    private String especialidad;
    private String turno;

    // Getters y Setters
    public String getColegiatura() { return colegiatura; }
    public void setColegiatura(String colegiatura) { this.colegiatura = colegiatura; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }
}