package com.laboratorio.tp6.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laboratorio.tp6.modelo.Alumno;
import com.laboratorio.tp6.repositorio.AlumnoRepositorio;

@Service
public class AlumnoServicio implements AlumnoServicioInterface {

    @Autowired
    private AlumnoRepositorio repositorio;

    @Override
    public List<Alumno> listar() {
        return repositorio.findAll();
    }

    @Override
    public Alumno guardarAlumno(Alumno alumno) {
        return repositorio.save(alumno);
    }

    @Override
    public Alumno obtenerAlumnoPorId(int id) {
        return repositorio.findById(id).get();
    }

    @Override
    public Alumno actualizarAlumno(Alumno alumno) {
        return repositorio.save(alumno);
    }

    @Override
    public void eliminarAlumno(int id) {
        repositorio.deleteById(id);
    }


}
