package com.example.cardioapp.Paciente;

public class Paciente {

    private final String nombrePaciente;
    private final String apellidosPaciente;
    private final Integer edad;
    private final String historiaClinica;
    private long ID;

    public Paciente(String nombre, String apellidos, Integer edad, String historiaClinica) {
        this.nombrePaciente = nombre;
        this.apellidosPaciente = apellidos;
        this.edad = edad;
        this.historiaClinica = historiaClinica;
    }

    public String getNombrePaciente() {
        return this.nombrePaciente;
    }

    public String getApellidosPaciente() {
        return this.apellidosPaciente;
    }

    public long getID() {
        return ID;
    }

    public String getHistoriaClinica(){
        return this.historiaClinica;
    }

    public Integer getEdad() {
        return this.edad;
    }
}
