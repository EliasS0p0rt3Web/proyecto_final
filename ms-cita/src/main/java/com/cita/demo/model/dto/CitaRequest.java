package com.cita.demo.model.dto;

public class CitaRequest {
    private String paciente;
    private String colegiaturaMedico;
    private String turnoCita;
    private double costoConsulta;

    // Getters y Setters
    public String getPaciente() { return paciente; }
    public void setPaciente(String paciente) { this.paciente = paciente; }
    public String getColegiaturaMedico() { return colegiaturaMedico; }
    public void setColegiaturaMedico(String colegiaturaMedico) { this.colegiaturaMedico = colegiaturaMedico; }
    public String getTurnoCita() { return turnoCita; }
    public void setTurnoCita(String turnoCita) { this.turnoCita = turnoCita; }
    public double getCostoConsulta() { return costoConsulta; }
    public void setCostoConsulta(double costoConsulta) { this.costoConsulta = costoConsulta; }
}