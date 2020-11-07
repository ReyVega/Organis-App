package com.example.organisapp;

public class Task {
    private String nombreActividad;
    private String descripcion;
    private String prioridad;
    private String fecha;
    private String hora;

    public Task(String nombreActividad, String descripcion, String prioridad, String fecha, String hora) {
        this.nombreActividad = nombreActividad;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getNombreActividad() {
        return this.nombreActividad;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getPrioridad() {
        return this.prioridad;
    }

    public String getFecha() {
        return this.fecha;
    }

    public String getHora() {
        return this.hora;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return this.nombreActividad;
    }
}
