package com.laboratorio.tp6.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laboratorio.tp6.modelo.Carrera;

@Repository
public interface CarreraRepositorio extends JpaRepository<Carrera, Integer>{

    
}
