package com.laboratorio.tp6.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laboratorio.tp6.modelo.Alumno;
import com.laboratorio.tp6.servicio.AlumnoServicio;


@Controller
@RequestMapping("/alumnos")
public class AlumnoControlador {

    @Autowired
    private AlumnoServicio servicio;

    @GetMapping
    public String listarAlumnos(Model modelo) {
        List<Alumno> alumnos = servicio.listar();
        modelo.addAttribute("alumnos", alumnos);
        return "index";
    }
    

}
