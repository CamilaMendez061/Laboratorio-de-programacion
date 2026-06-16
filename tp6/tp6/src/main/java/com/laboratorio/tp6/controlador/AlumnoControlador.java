package com.laboratorio.tp6.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laboratorio.tp6.modelo.Alumno;
import com.laboratorio.tp6.servicio.AlumnoServicio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetMapping("/nuevo")
    public String formularioDeRegistrarAlumno(Model modelo) {
        Alumno alumno = new Alumno();
        modelo.addAttribute("alumno", alumno);
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
        return "editar";
    }

    @PostMapping("/{id}")
    public String actualizarAlumno(@PathVariable int id, @ModelAttribute("alumno") Alumno alumno, Model modelo) {
        System.out.println("ENTRÉ AL POST");
        Alumno alumnoExistente = servicio.obtenerAlumnoPorId(id);
        alumnoExistente.setId(id);
        alumnoExistente.setApellidoNombre(alumno.getApellidoNombre());
        alumnoExistente.setDni(alumno.getDni());
        alumnoExistente.setEmail(alumno.getEmail());
        alumnoExistente.setTelefono(alumno.getTelefono());
        servicio.actualizarAlumno(alumnoExistente);
        return "redirect:/alumnos";
    }

    @GetMapping("/{id}")
    public String eliminarAlumno(@PathVariable int id) {
        servicio.eliminarAlumno(id);
        return "redirect:/alumnos";
    }

}
