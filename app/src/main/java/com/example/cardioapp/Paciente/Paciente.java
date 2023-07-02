package com.example.cardioapp.Paciente;

import java.io.Serializable;

public class Paciente implements Serializable { //implementando serializable, podemos pasar el objeto de la actividad ListaPacientes a VistaPaciente

    private final String nombrePaciente;
    private final String apellidosPaciente;
    private final Integer edad;
    private final String historiaClinica;
    //Se inicializa aqui para poder crear un Paciente sin estos campos
    private String historiaClinica2 = "";
    private String historiaClinica3 = "";
    private long ID;

    public Paciente(String nombre, String apellidos, Integer edad, String historiaClinica, String historiaClinica2, String historiaClinica3) {
        this.nombrePaciente = nombre;
        this.apellidosPaciente = apellidos;
        this.edad = edad;
        this.historiaClinica = historiaClinica;
        this.historiaClinica2 = historiaClinica2;
        this.historiaClinica3 = historiaClinica3;
    }

    public Paciente(String nombre, String apellidos, Integer edad, String historiaClinica, String historiaClinica2) {
        this.nombrePaciente = nombre;
        this.apellidosPaciente = apellidos;
        this.edad = edad;
        this.historiaClinica = historiaClinica;
        this.historiaClinica2 = historiaClinica2;
    }
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
    public String getHistoriaClinica2() {
        return historiaClinica2;
    }

    public String getHistoriaClinica3() {
        return historiaClinica3;
    }
}
