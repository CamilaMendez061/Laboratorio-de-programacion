package punto5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ArchivoUniversidadJSON {

    private FileReader archivo;
    private JSONObject universidad;
    private JSONArray listaAlumnos;
    private JSONArray listaCarreras;

    public ArchivoUniversidadJSON(String archivo) throws FileNotFoundException, IOException, Exception {
        JSONParser jsonParser = new JSONParser();

        this.archivo = new FileReader(archivo);
        Object obj = jsonParser.parse(this.archivo);
        this.universidad = (JSONObject) obj;
        this.listaAlumnos = (JSONArray) this.universidad.get("alumnos");
        this.listaCarreras = (JSONArray) this.universidad.get("carreras");

    }

    public JSONObject getUniversidad() {
        return this.universidad;
    }

    public JSONArray getListaAlumnos() {
        return this.listaAlumnos;
    }

    public JSONArray getListaCarreras() {
        return this.listaCarreras;
    }

    public void mostrarInfoUniversidad() {
        System.out.println("--- Listado de alumnos ---");
        listarAlumnos();
        System.out.println("--- Listado de carreras ---");
        listarCarreras();
    }

    public void listarAlumnos() {
        for (Object alumno : this.listaAlumnos) {
            mostrarAlumno((JSONObject) alumno);
            System.out.println();
        }
    }

    private void mostrarAlumno(JSONObject alumno) {
        System.out.println("id: " + alumno.get("id"));
        System.out.println("Nombre y apellido: " + alumno.get("nombreApellido"));
        System.out.println("DNI: " + alumno.get("dni"));
        System.out.println("Email: " + alumno.get("email"));
        System.out.println("Teléfono: " + alumno.get("telefono"));
    }

    public void listarCarreras() {
        for (Object carrera : this.listaCarreras) {
            mostrarCarrera((JSONObject) carrera);
            System.out.println();
        }
    }

    private void mostrarCarrera(JSONObject carrera) {
        System.out.println("id: " + carrera.get("id"));
        System.out.println("Nombre: " + carrera.get("nombre"));
    }

    public boolean mostrarCarreraId(long id) {
        int aux = buscarCarrera(id);
        return mostrarCarrera(aux);
    }

    private boolean mostrarCarrera(int aux) {
        if (aux == -1) {
            System.out.println("No se han encontrado coincidencias.");
            return false;
        } else {
            System.out.println("Se ha encontrado la carrera.");
            JSONObject carreAux = (JSONObject) this.listaCarreras.get(aux);
            mostrarCarrera(carreAux);
            return true;
        }
    }

    public boolean mostrarAlumnoId(long id) {
        int aux = buscarAlumnoId(id);
        return mostrarAlumno(aux);
    }

    public boolean mostrarAlumnoDNI(long dni) {
        int aux = buscarAlumnoDNI(dni);
        return mostrarAlumno(aux);
    }

    private boolean mostrarAlumno(int aux) {
        if (aux == -1) {
            System.out.println("No se han encontrado coincidencias.");
            return false;
        } else {
            System.out.println("Se ha encontrado al alumno.");
            JSONObject alumAux = (JSONObject) this.listaAlumnos.get(aux);
            mostrarAlumno(alumAux);
            return true;
        }
    }

    // Retorna índice del alumno en el array listaAlumnos si lo encuentra por DNI. Sino, retorna -1
    public int buscarAlumnoDNI(long dni) {
        int i = -1;
        for (Object alumno : this.listaAlumnos) {
            JSONObject alumAux = (JSONObject) alumno;
            long dniAux = (long) alumAux.get("dni");
            i++;
            if (dniAux == dni) {
                return i;
            }
        }
        return -1;
    }

    // Retorna índice del alumno en el array listaAlumnos si lo encuentra por id. Sino, retorna -1
    public int buscarAlumnoId(long id) {
        int i = -1;
        for (Object alumno : this.listaAlumnos) {
            JSONObject alumAux = (JSONObject) alumno;
            long idAux = (long) alumAux.get("id");
            i++;
            if (idAux == id) {
                return i;
            }
        }
        return -1;
    }

    // Retorna índice de carrera en el array listaCarreras si lo encuentra. Sino, retorna -1
    public int buscarCarrera(long id) {
        int i = -1;
        for (Object carrera : listaCarreras) {
            JSONObject carreAux = (JSONObject) carrera;
            long idAux = (long) carreAux.get("id");
            i++;
            if(idAux == id) {
                return i;
            }
        }
        return -1;
    }

    public void eliminarAlumnoDNI(long dni) {
        int aux = buscarAlumnoDNI(dni);
        eliminarAlumno(aux);
    }

    public void eliminarAlumnoId(long id) {
        int aux = buscarAlumnoId(id);
        eliminarAlumno(aux);
    }

    private void eliminarAlumno(int aux) {
        if (aux == -1) {
            System.out.println("No se han encontrado coincidencias.");
        } else {
            listaAlumnos.remove(aux);
            System.out.println("Se ha eliminado el alumno.");
        }
    }

    public long getIdAlumno(long dni) {
        int aux = buscarAlumnoDNI(dni);
        JSONObject alumAux = (JSONObject) this.listaAlumnos.get(aux);
        return (long) alumAux.get("id");
    }

    public void eliminarCarreraId(long id) {
        int aux = buscarCarrera(id);
        eliminarCarrera(aux);
    }

    private void eliminarCarrera(int aux) {
        if (aux == -1) {
            System.out.println("No se han encontrado coincidencias.");
        } else {
            listaCarreras.remove(aux);
            System.out.println("Se ha eliminado la carrera.");
        }
    }

    public int cantidadAlumnos() {
        int cantidad = listaAlumnos.size();
        return cantidad;
    }

    public int cantidadCarreras() {
        int cantidad = listaCarreras.size();
        return cantidad;
    }

    public void cantidadAlumnosPorCarrera() {
        ArrayList<Long> idCarreras = new ArrayList<>();
        ArrayList<String> nombreCarreras = new ArrayList<>();
        ArrayList<Integer> cantAlumnos = new ArrayList<>();

        // carga del array con los id de todas las carreras, y del array con los nombres de las carreras
        for (Object carrera : listaCarreras) {
            JSONObject carreAux = (JSONObject) carrera;
            long id = (long) carreAux.get("id");
            idCarreras.add(id);
            String nomCarrera = (String) carreAux.get("nombre");
            nombreCarreras.add(nomCarrera);
            cantAlumnos.add(0);
        }

        // compara el idCarrera de los alumnos con los id de las carreras guardados en el array idCarreras, y suma 1 a la cantidad de alumnos en esa carrera cada vez que coinciden. Almacena la cantidad en el array cantAlumnos
        for (int i = 0; i < idCarreras.size(); i++) {
            long idAux = idCarreras.get(i);
            int cantAux = 0;
            for (Object alumno : listaAlumnos) {
                JSONObject alumAux = (JSONObject) alumno;
                long idCarrera = (long) alumAux.get("idCarrera");
                if (idCarrera == idAux) {
                    cantAux++;
                    cantAlumnos.add(i, cantAux);
                }
            }
        }

        // muestra los resultados
        for (int i = 0; i < idCarreras.size(); i++) {
            System.out.println("La carrera " + nombreCarreras.get(i) + " tiene " + cantAlumnos.get(i) + " alumnos inscriptos.");
        }
        System.out.println();
    }

}
