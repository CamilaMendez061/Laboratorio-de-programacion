package com.laboratorio.tp6.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "apellido_nombre", nullable = false, length = 30)
    private String apellidoNombre;

    @Column(name = "dni", nullable = false)
    private int dni;

    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @Column(name = "telefono", length = 15, nullable = false)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "carrera_id", nullable = false)
    private Carrera carrera;

    public Alumno() {
        
    }

    public Alumno(int id, String apellidoNombre, int dni, String email, String telefono, Carrera carrera) {
        this.id = id;
        this.apellidoNombre = apellidoNombre;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.carrera = carrera;
    }

    public int getId() {
        return id;
    }

    public String getApellidoNombre() {
        return apellidoNombre;
    }

    public int getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public Carrera getCarrera() {
        return carrera;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setApellidoNombre(String apellidoNombre) {
        this.apellidoNombre = apellidoNombre;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

}
