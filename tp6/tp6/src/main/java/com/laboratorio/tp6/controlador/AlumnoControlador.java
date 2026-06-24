package com.laboratorio.tp6.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laboratorio.tp6.modelo.Alumno;
import com.laboratorio.tp6.modelo.Carrera;
import com.laboratorio.tp6.repositorio.AlumnoRepositorio;
import com.laboratorio.tp6.repositorio.CarreraRepositorio;
import com.laboratorio.tp6.servicio.AlumnoServicio;
import com.laboratorio.tp6.servicio.CarreraServicio;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/alumnos")
public class AlumnoControlador {

    @Autowired
    private AlumnoServicio servicio;

    @Autowired
    private CarreraServicio carrera;

    @Autowired
    private CarreraRepositorio carreraRepositorio;

    @Autowired
    private AlumnoRepositorio alumnoRepositorio;

    @GetMapping
    public String listarAlumnos(Model modelo) {
        List<Alumno> alumnos = servicio.listar();
        modelo.addAttribute("alumnos", alumnos);
        return "index";
    }

    @GetMapping("/nuevo")
    public String formularioDeRegistrarAlumno(Model modelo) {
        Alumno alumno = new Alumno();
        modelo.addAttribute("alumno", alumno);
        List<Carrera> listaCarreras = carrera.listar();
        modelo.addAttribute("listaCarreras", listaCarreras);
        return "crear";
    }

    @PostMapping
    public String guardarAlumno(@ModelAttribute("alumno") Alumno alumno) {
        servicio.guardarAlumno(alumno);
        return "redirect:/alumnos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable int id, Model modelo) {
        modelo.addAttribute("alumno", servicio.obtenerAlumnoPorId(id));
        List<Carrera> listaCarreras = carrera.listar();
        modelo.addAttribute("listaCarreras", listaCarreras);
        return "editar";
    }

    @PostMapping("/{id}")
    public String actualizarAlumno(@PathVariable int id, @ModelAttribute("alumno") Alumno alumno, Model modelo) {
        Alumno alumnoExistente = servicio.obtenerAlumnoPorId(id);
        alumnoExistente.setId(id);
        alumnoExistente.setApellidoNombre(alumno.getApellidoNombre());
        alumnoExistente.setDni(alumno.getDni());
        alumnoExistente.setEmail(alumno.getEmail());
        alumnoExistente.setTelefono(alumno.getTelefono());
        alumnoExistente.setCarrera(alumno.getCarrera());
        servicio.actualizarAlumno(alumnoExistente);
        return "redirect:/alumnos";
    }

    @GetMapping("/{id}")
    public String eliminarAlumno(@PathVariable int id) {
        servicio.eliminarAlumno(id);
        return "redirect:/alumnos";
    }

    @GetMapping("/carreras")
    public String mostrarCantAlumnos(Model modelo) {
        List<Carrera> carreras = carreraRepositorio.findAll();

        Map<Integer, Integer> cantAlumnosPorCarrera = new HashMap<>();

        for (Carrera c : carreras) {
            int cantAlumnos = alumnoRepositorio.countByCarreraId(c.getId());
            cantAlumnosPorCarrera.put(c.getId(), cantAlumnos);
        }

        modelo.addAttribute("carreras", carreras);
        modelo.addAttribute("cantAlumnosPorCarrera", cantAlumnosPorCarrera);

        return "carreras";
    }
}
