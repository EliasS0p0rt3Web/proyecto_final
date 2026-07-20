package com.medico.demo.model.dto;

public class HorarioResponse {
    private String colegiatura;
    private String especialidad;
    private String turno;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters y Setters
    public String getColegiatura() { return colegiatura; }
    public void setColegiatura(String colegiatura) { this.colegiatura = colegiatura; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }
}