package punto3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MetodosSeriesJSON {
    
    public static void leerJSON(String archivo) {

        JSONParser jsonParser = new JSONParser();

        try {

            FileReader fr = new FileReader(archivo);
            Object obj = jsonParser.parse(fr);
            JSONArray listaSeries = (JSONArray) obj;

            System.out.println("Contenido del archivo:");

            for (Object serie: listaSeries) {
                mostrarInfoSerie((JSONObject) serie);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error de E/S");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al tratar de leer el archivo.");
            System.out.println(e.getMessage());            
        }
        
    }

    private static void mostrarInfoSerie(JSONObject serie) {
        System.out.println("Serie: " + serie.get("id"));
        System.out.println("Título: " + serie.get("titulo"));
        System.out.println("Creador: " + serie.get("creador"));
        System.out.println("Año de lanzamiento: " + serie.get("anioLanzamiento"));
        System.out.println("Cantidad de temporadas: " + serie.get("cantTemporadas"));
        System.out.println();

        System.out.println("Episodios por temporada:");
        JSONArray temporadas = (JSONArray) serie.get("episodiosPorTemporada");
        for (int i=0; i < temporadas.size(); i++) {
            JSONObject temp = (JSONObject) temporadas.get(i);
            System.out.println("Temporada n°: " + temp.get("numeroTemporada"));
            System.out.println("Cantidad de episodios: " + temp.get("episodios"));
        }
        System.out.println();
        
        System.out.println("Actores Principales:");
        JSONArray actores = (JSONArray) serie.get("actoresPrincipales");
        for (int i = 0; i < actores.size(); i++) {
            String act = (String) actores.get(i);
            System.out.println(act);
        }
        System.out.println();

        System.out.println("Géneros:");
        JSONArray generos = (JSONArray) serie.get("generos");
        for (int i = 0; i < generos.size(); i++) { 
            String gen = (String) generos.get(i);
            System.out.println(gen);
        }
        System.out.println();

        System.out.println("Estado: " + serie.get("estado"));
        System.out.println("Calificación: " + serie.get("calificacion"));

        System.out.println("--------------------------------------");
    }

}
