package punto4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MetodosInfoSeries {

    private FileReader archivo;
    private JSONArray listaSeries;

    public MetodosInfoSeries(String archivo) throws FileNotFoundException, IOException, Exception {
        JSONParser jsonParser = new JSONParser();

            this.archivo = new FileReader(archivo);
            Object obj = jsonParser.parse(this.archivo);
            this.listaSeries = (JSONArray) obj;
    }

    // Listar información. Mostrar: título, año y cantidad de temporadas de todas
    // las series.
    public void listarInfo() {
        for (Object serie : this.listaSeries) {
            JSONObject serieAux = (JSONObject) serie;
            System.out.println("Serie: " + serieAux.get("titulo"));
            System.out.println("Año de lanzamiento: " + serieAux.get("anioLanzamiento"));
            System.out.println("Cantidad de temporadas: " + serieAux.get("cantTemporadas"));
            System.out.println("---------------------");
        }
    }

    // Contar información. Mostrar cada serie junto con la cantidad de actores
    // principales.
    public void contarInfo() {
        for (Object serie : this.listaSeries) {
            JSONObject serieAux = (JSONObject) serie;
            System.out.println("Serie: " + serieAux.get("titulo"));

            System.out.println("Actores principales: ");
            JSONArray actores = (JSONArray) serieAux.get("actoresPrincipales");
            System.out.println("Cantidad: " + actores.size());
            for (int i = 0; i < actores.size(); i++) {
                String act = (String) actores.get(i);
                System.out.println(act);
            }
            System.out.println("---------------------");
        }
    }

    // Filtrar información. Mostrar las series cuya calificación sea mayor a 8.
    public void filtrarInfoCalifMayor8() {
        double aux = 8;

        filtrarInfoCalif(aux);
    }

    // Filtrar información. Mostrar series con calificación mayor al parámetro
    // ingresado.
    public void filtrarInfoCalif(double calificacion) {
        System.out.println("Series con calificación mayor que " + calificacion + " :");
        System.out.println();

        for (Object serie : this.listaSeries) {
            JSONObject serieAux = (JSONObject) serie;
            double calificacionAux = (double) serieAux.get("calificacion");
            if (calificacionAux > calificacion) {
                System.out.println("Serie: " + serieAux.get("titulo"));
                System.out.println("Calificación: " + serieAux.get("calificacion"));
                System.out.println("---------------------");
            }
        }
    }

    // Búsqueda avanzada. Mostrar las series donde participe un actor específico
    public void busquedaPorActor(String nombreActor) {
        boolean participa = false;
        String seriesDondeParticipa = "";

        for (Object serie : this.listaSeries) {
            JSONObject serieAux = (JSONObject) serie;
            JSONArray actores = (JSONArray) serieAux.get("actoresPrincipales");
            for (int i = 0; i < actores.size(); i++) {
                String act = (String) actores.get(i);
                if (act.equals(nombreActor)) {
                    participa = true;
                    String tituloSerie = (String) serieAux.get("titulo");
                    seriesDondeParticipa = seriesDondeParticipa + tituloSerie + " \n";
                }
            }
        }

        if (!participa) {
            System.out.println("El actor no participa en ninguna de las series almacenadas en el archivo.");
        } else {
            System.out.println("El actor participa en:\n " + seriesDondeParticipa);

        }
    }

}
