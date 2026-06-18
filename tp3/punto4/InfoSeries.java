package punto4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class InfoSeries {
    public static void main(String[] args) {

        MetodosInfoSeries series;
        String nombreArchivo = "punto4/series.json";

        try {
            series = new MetodosInfoSeries(nombreArchivo);
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
            System.out.println(e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error de E/S");
            System.out.println(e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error.");
            System.out.println(e.getMessage());
            return;
        }

        
        System.out.println("--- Listar información ---");
        series.listarInfo();
        System.out.println();
        
        System.out.println("--- Contar información ---");
        series.contarInfo();
        System.out.println();
        
        System.out.println("--- Filtrar información ---");
        series.filtrarInfoCalifMayor8();
        System.out.println();

        Scanner scan = new Scanner(System.in);
        scan.useLocale(Locale.US);

        System.out.println("Ingrese una calificación para ver las series que superen esa calificación:");
        double calificacion = scan.nextDouble();
        series.filtrarInfoCalif(calificacion);
        scan.nextLine();

        System.out.println("Ingrese un actor para verificar si ha participado en alguna serie almacenada en el archivo:");
        String actor = scan.nextLine();
        series.busquedaPorActor(actor);

    }
}
