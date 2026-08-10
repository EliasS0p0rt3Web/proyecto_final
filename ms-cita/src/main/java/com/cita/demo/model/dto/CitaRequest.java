package com.cita.demo.model.dto;

public class CitaRequest {

    private Integer pacienteId;
    private String colegiaturaMedico;
    private String turnoCita;
    private double costoConsulta;

    public Integer getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getColegiaturaMedico() {
        return colegiaturaMedico;
    }

    public void setColegiaturaMedico(String colegiaturaMedico) {
        this.colegiaturaMedico = colegiaturaMedico;
    }

    public String getTurnoCita() {
        return turnoCita;
    }

    public void setTurnoCita(String turnoCita) {
        this.turnoCita = turnoCita;
    }

    public double getCostoConsulta() {
        return costoConsulta;
    }

    public void setCostoConsulta(double costoConsulta) {
        this.costoConsulta = costoConsulta;
    }
}