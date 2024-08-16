package com.Final.Final.modelo;

import java.time.LocalDate;

public class Paciente {

    private Integer id;
    private String nombre;
    private String apellido;
    private String domicilio;
    private String dni;
    private LocalDate fechaAlta;

    public Paciente(String nombre, String apellido, String domicilio, String dni, LocalDate fechaAlta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
    }

    public Paciente(Integer id, String nombre, String apellido, String domicilio, String dni, LocalDate fechaAlta) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
    }

    public Paciente() {

    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString(){
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaAlta=" + fechaAlta +
                '}';
    }
}
