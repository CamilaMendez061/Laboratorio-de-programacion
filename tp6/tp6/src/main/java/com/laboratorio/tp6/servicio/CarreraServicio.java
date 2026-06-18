package com.laboratorio.tp6.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laboratorio.tp6.modelo.Carrera;
import com.laboratorio.tp6.repositorio.CarreraRepositorio;

@Service
public class CarreraServicio implements CarreraServicioInterface {

    @Autowired
    private CarreraRepositorio repositorio;

    @Override
    public List<Carrera> listar() {
        return repositorio.findAll();
    }

}
