package entrega.tp1.punto1;

import java.sql.Connection;
import java.sql.SQLException;
import entrega.tp1.ConexionDB;

public class Menu {
    public static void main(String[] args) {

        try (Connection conn = ConexionDB.getConexion()) {
            
            
            
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error.");
            System.out.println(e.getMessage());
        }
    }
}
