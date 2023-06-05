package com.example.cardioapp.Paciente;

public class Paciente {

    private String nombrePaciente;
    private String apellidosPaciente;
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
