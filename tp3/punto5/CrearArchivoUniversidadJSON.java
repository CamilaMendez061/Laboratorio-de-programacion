package punto5;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CrearArchivoUniversidadJSON {
    public static void main(String[] args) {

        JSONObject universidad = new JSONObject();

        // Datos alumnos
        Alumno alumno1 = new Alumno(1, "Bodoque Juan Carlos", 23021552, "juan_carlos@gmail.com", "15600217", 3);
        Alumno alumno2 = new Alumno(2, "Triviño Tulio", 20584789, "tulio.triviño@gmail.com", "15421977", 1);
        Alumno alumno3 = new Alumno(3, "Amarilla Flor", 12345678, "florcita_amarilla@gmail.com", "15000000", 2);
        Alumno alumno4 = new Alumno(4, "Matcha Juana", 33418900, "juanita_matcha@gmail.com", "15660322", 4);
        Alumno alumno5 = new Alumno(5, "Paredes Armando", 41002879, "armando_papapa@gmail.com", "15004466", 1);
        Alumno alumno6 = new Alumno(6, "Lopez Marcos", 26877444, "marquitos@gmail.com", "15000263", 1);
        Alumno alumno7 = new Alumno(7, "Rodriguez Teresa", 25897111, "tere.rodriguez@gmail.com", "15666333", 4);

        JSONArray listadoAlumnos = new JSONArray();
        listadoAlumnos.add(alumno1.getAlumno());
        listadoAlumnos.add(alumno2.getAlumno());
        listadoAlumnos.add(alumno3.getAlumno());
        listadoAlumnos.add(alumno4.getAlumno());
        listadoAlumnos.add(alumno5.getAlumno());
        listadoAlumnos.add(alumno6.getAlumno());
        listadoAlumnos.add(alumno7.getAlumno());

        universidad.put("alumnos", listadoAlumnos);

        // Datos carreras
        Carrera carrera1 = new Carrera(1, "Analista de Sistemas");
        Carrera carrera2 = new Carrera(2, "Licenciatura en Sistemas");
        Carrera carrera3 = new Carrera(3, "Licenciatura en Astronomía");
        Carrera carrera4 = new Carrera(4, "Ingeniería Química");

        JSONArray listadoCarreras = new JSONArray();
        listadoCarreras.add(carrera1.getCarrera());
        listadoCarreras.add(carrera2.getCarrera());
        listadoCarreras.add(carrera3.getCarrera());
        listadoCarreras.add(carrera4.getCarrera());

        universidad.put("carreras", listadoCarreras);

        // Guardar en archivo
        try (FileWriter fw = new FileWriter("punto5/universidad.json")) {
            fw.write(universidad.toJSONString());
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error.");
            System.out.println(e.getMessage());
        }

        // Mostrar archivo por consola
        try {
            ArchivoUniversidadJSON archivo = new ArchivoUniversidadJSON("punto5/universidad.json");
            archivo.mostrarInfoUniversidad();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error de E/S");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error.");
            System.out.println(e.getMessage());
        }
    }
}
