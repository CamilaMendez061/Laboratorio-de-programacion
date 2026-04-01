package entrega.tp1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB {
    private static final String url = "jdbc:mysql://localhost:3306/banco";
    private static final String user = "root";
    private static final String password = "1234";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
