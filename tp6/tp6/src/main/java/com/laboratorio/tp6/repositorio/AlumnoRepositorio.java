package com.laboratorio.tp6.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laboratorio.tp6.modelo.Alumno;

@Repository
public interface AlumnoRepositorio extends JpaRepository<Alumno, Integer> {
    int countByCarreraId(Integer carreraId);
}
