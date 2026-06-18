package punto5;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

public class GuardarArchivoJSON {

    public static void guardarCambios(String archivo, JSONObject jsonObject) {
        try (FileWriter fw = new FileWriter(archivo)) {
            fw.write(jsonObject.toJSONString());
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error.");
            System.out.println(e.getMessage());
        }
    }

}
