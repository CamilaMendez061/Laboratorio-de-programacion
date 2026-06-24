package com.laboratorio.tp6.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laboratorio.tp6.modelo.Carrera;
import com.laboratorio.tp6.repositorio.CarreraRepositorio;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/carreras")
public class CarreraControlador {
    
    @Autowired
    private CarreraRepositorio carreraRepositorio;

    @GetMapping
    public String listarCarreras(Model modelo) {
        List<Carrera> carreras = carreraRepositorio.findAll();
        modelo.addAttribute("carreras", carreras);
        return "carreras";
    }
    
}
