package com.example.cardioapp.Paciente;

public class Paciente {

    private final String nombrePaciente;
    private final String apellidosPaciente;
    private long ID;

    public Paciente(String nombre, String apellidos) {
        this.nombrePaciente = nombre;
        this.apellidosPaciente = apellidos;
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
}
