package com.example.organisapp;

import java.util.ArrayList;

public class Usuario {
    private int ID;
    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasena;
    private ArrayList<Task> diarias;
    private ArrayList<Task> semanales;
    private ArrayList<Task> mensuales;

    public Usuario(int ID, String nombre, String apellidos, String correo, String contrasena) {
        this.ID = ID;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasena = contrasena;
        this.diarias = new ArrayList<Task>();
        this.semanales = new ArrayList<Task>();
        this.mensuales = new ArrayList<Task>();
    }

    public int getID() {
        return this.ID;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public String getCorreo() {
        return this.correo;
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public ArrayList<Task> getDiarias() {
        return this.diarias;
    }

    public ArrayList<Task> getSemanales() {
        return this.semanales;
    }

    public ArrayList<Task> getMensuales() {
        return this.mensuales;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void addTaskDiaria(Task task){diarias.add(task);}

    public void addTaskMensual(Task task){mensuales.add(task);}

    public void addTaskSemanal(Task task){semanales.add(task);}
}
