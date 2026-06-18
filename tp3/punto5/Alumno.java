package punto5;

import org.json.simple.JSONObject;

public class Alumno {
    private JSONObject alumno;

    public Alumno(long id, String nombreApellido, long dni, String email, String telefono, long idCarrera) {
        this.alumno = new JSONObject();
        this.alumno.put("id", id);
        this.alumno.put("nombreApellido", nombreApellido);
        this.alumno.put("dni", dni);
        this.alumno.put("email", email);
        this.alumno.put("telefono", telefono);
        this.alumno.put("idCarrera", idCarrera);
    }

    public JSONObject getAlumno() {
        return this.alumno;
    }
}
