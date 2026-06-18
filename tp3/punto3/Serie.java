package punto3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Serie {

    private JSONObject serie;
    private JSONArray temporadas;
    private JSONArray actoresPrincipales;
    private JSONArray generos;

    public Serie(long id, String titulo, String creador, long anioLanzamiento, long cantTemporadas) {
        this.serie = new JSONObject();
        this.serie.put("id", id);
        this.serie.put("titulo", titulo);
        this.serie.put("creador", creador);
        this.serie.put("anioLanzamiento", anioLanzamiento);
        this.serie.put("cantTemporadas", cantTemporadas);
        this.temporadas = new JSONArray();
        this.actoresPrincipales = new JSONArray();
        this.generos = new JSONArray();
    }

    public void agregarTemporada(long numero, long episodios) {
        JSONObject temporada = new JSONObject();
        temporada.put("numeroTemporada", numero);
        temporada.put("episodios", episodios);
        this.temporadas.add(temporada);
    }

    public void agregarActor(String nombre) {
        this.actoresPrincipales.add(nombre);
    }

    public void agregarGenero(String genero) {
        this.generos.add(genero);
    }

    public void agregarEstado(String estado) {
        this.serie.put("estado", estado);
    }

    public void agregarCalificacion(double calificacion) {
        this.serie.put("calificacion", calificacion);
    }

    public void cargarDatos() {
        this.serie.put("episodiosPorTemporada", temporadas);
        this.serie.put("actoresPrincipales", actoresPrincipales);
        this.serie.put("generos", generos);
    }

    public JSONObject obtenerSerie() {
        return this.serie;
    }

}
