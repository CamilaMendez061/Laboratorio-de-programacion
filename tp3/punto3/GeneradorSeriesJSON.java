package punto3;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;

/**
 * @author Camila A. Mendez
 * @version 1.0
 *          13/05/2026
 *          La clase Punto3 genera un archivo .json con datos de distintas
 *          series, y luego muestra los datos almacenados en el archivo por
 *          pantalla.
 * 
 */

public class GeneradorSeriesJSON {
    public static void main(String[] args) {

        // datos serie 1
        Serie serie1 = new Serie(1, "Beef", "Lee Sung Jin", 2023, 2);
        serie1.agregarTemporada(1, 10);
        serie1.agregarTemporada(2, 8);
        serie1.agregarActor("Steven Yeun");
        serie1.agregarActor("Ali Wong");
        serie1.agregarActor("Oscar Isaac");
        serie1.agregarActor("Carey Mulligan");
        serie1.agregarGenero("Comedia dramática");
        serie1.agregarGenero("Drama");
        serie1.agregarGenero("Thriller");
        serie1.agregarGenero("Tragicomedia");
        serie1.agregarEstado("Finalizada");
        serie1.agregarCalificacion(8.4);
        serie1.cargarDatos();

        // datos serie 2
        Serie serie2 = new Serie(2, "American Horror Story", "Ryan Murphy y Brad Falchuk", 2011, 12);
        serie2.agregarTemporada(1, 12);
        serie2.agregarTemporada(2, 13);
        serie2.agregarTemporada(3, 13);
        serie2.agregarTemporada(4, 13);
        serie2.agregarTemporada(5, 12);
        serie2.agregarTemporada(6, 10);
        serie2.agregarTemporada(7, 11);
        serie2.agregarTemporada(8, 10);
        serie2.agregarTemporada(9, 9);
        serie2.agregarTemporada(10, 10);
        serie2.agregarTemporada(11, 11);
        serie2.agregarTemporada(12, 9);
        serie2.agregarActor("Evan Peters");
        serie2.agregarActor("Sarah Paulson");
        serie2.agregarActor("Lily Rabe");
        serie2.agregarActor("Frances Conroy");
        serie2.agregarActor("Denis O'Hare");
        serie2.agregarActor("Kathy Bates");
        serie2.agregarGenero("Horror");
        serie2.agregarGenero("Antología");
        serie2.agregarGenero("Sobrenatural");
        serie2.agregarEstado("En emisión");
        serie2.agregarCalificacion(7.9);
        serie2.cargarDatos();

        // datos serie 3
        Serie serie3 = new Serie(3, "Kingdom", "Kim Eun-hee", 2019, 2);
        serie3.agregarTemporada(1, 6);
        serie3.agregarTemporada(2, 6);
        serie3.agregarActor("Ju Ji-hoon");
        serie3.agregarActor("Ryu Seung-ryong");
        serie3.agregarActor("Bae Doona");
        serie3.agregarActor("Kim Sung-kyu");
        serie3.agregarGenero("Histórico");
        serie3.agregarGenero("Político");
        serie3.agregarGenero("Horror");
        serie3.agregarGenero("Thriller");
        serie3.agregarEstado("En emisión");
        serie3.agregarCalificacion(8.3);
        serie3.cargarDatos();

        // datos serie 4
        Serie serie4 = new Serie(4, "Soulmates", "William Bridges y Brett Goldstein", 2020, 1);
        serie4.agregarTemporada(1, 6);
        serie4.agregarActor("Sarah Snook");
        serie4.agregarActor("David Costabile");
        serie4.agregarActor("Laia Costa");
        serie4.agregarGenero("Romance");
        serie4.agregarGenero("Distópico");
        serie4.agregarGenero("Antología");
        serie4.agregarEstado("Finalizada");
        serie4.agregarCalificacion(6.4);
        serie4.cargarDatos();

        // datos serie 5
        Serie serie5 = new Serie(5, "Entrevista con el vampiro", "Rolin Jones", 2022, 2);
        serie5.agregarTemporada(1, 7);
        serie5.agregarTemporada(2, 8);
        serie5.agregarActor("Jacob Anderson");
        serie5.agregarActor("Sam Reid");
        serie5.agregarActor("Eric Bogosian");
        serie5.agregarActor("Assad Zaman");
        serie5.agregarGenero("Terror gótico");
        serie5.agregarGenero("Romance gótico");
        serie5.agregarGenero("Gotico sureño");
        serie5.agregarGenero("Drama");
        serie5.agregarGenero("Fantasía oscura");
        serie5.agregarEstado("En emisión");
        serie5.agregarCalificacion(7.6);
        serie5.cargarDatos();

        // datos serie 6
        Serie serie6 = new Serie(6, "W: Dos Mundos", "Song Jae Jung", 2016, 1);
        serie6.agregarTemporada(1, 16);
        serie6.agregarActor("Lee Jong-suk");
        serie6.agregarActor("Han Hyo-joo");
        serie6.agregarActor("Kim Eui-sung");
        serie6.agregarGenero("Fantasía");
        serie6.agregarGenero("Romance");
        serie6.agregarGenero("Comedia");
        serie6.agregarGenero("Thriller");
        serie6.agregarGenero("Acción");
        serie6.agregarEstado("Finalizada");
        serie6.agregarCalificacion(9.5);
        serie6.cargarDatos();

        // creación de array que almacena los objetos serie
        JSONArray listadoSeries = new JSONArray();
        listadoSeries.add(serie1.obtenerSerie());
        listadoSeries.add(serie2.obtenerSerie());
        listadoSeries.add(serie3.obtenerSerie());
        listadoSeries.add(serie4.obtenerSerie());
        listadoSeries.add(serie5.obtenerSerie());
        listadoSeries.add(serie6.obtenerSerie());

        // guardar en archivo
        try (FileWriter fw = new FileWriter("punto3/series.json")) {
            fw.write(listadoSeries.toJSONString());

        } catch (IOException e) {
            System.out.println("Ha ocurrido un error.");
            System.out.println(e.getMessage());
        }

        MetodosSeriesJSON.leerJSON("punto3/series.json");

    }
}
