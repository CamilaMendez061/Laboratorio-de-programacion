package punto5;

import org.json.simple.JSONObject;

public class Carrera {
    private JSONObject carrera;

    public Carrera(long id, String nombre){
        this.carrera = new JSONObject();
        this.carrera.put("id", id);
        this.carrera.put("nombre", nombre);
    }

    public JSONObject getCarrera() {
        return this.carrera;
    }
}
