package entrega.tp1.punto2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entrega.tp1.ConexionDB;

public class Transaccion {

    //método para depositar en cuenta
    public static void depositar(int cuenta, double importe) {
        String sqlDepositar = "UPDATE cuentas SET saldo = saldo + ? WHERE cuenta = ?";
        updateTablas(cuenta, "D", importe, sqlDepositar);
    }

    //método para extraer de cuenta
    public static void extraer(int cuenta, double importe) {
        String sqlExtraer = "UPDATE cuentas SET saldo = saldo - ? WHERE cuenta = ?";
        updateTablas(cuenta, "E", importe, sqlExtraer);
    }

    //método privado para actualizar tablas con nuevos datos
    private static void updateTablas(int cuenta, String movimiento, double importe, String sql1) {
        String sql2 = "INSERT INTO movimientos (cuenta, movimiento, importe) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConexion();
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
            
            //desactivar auto-commmit
            conn.setAutoCommit(false);

            try {
                //modificar la tabla 'cuentas' con el nuevo valor de 'saldo'
                pstmt1.setDouble(1, importe);
                pstmt1.setInt(2, cuenta);
                pstmt1.executeUpdate();

                //insertar nuevos valores a tabla 'movimientos'
                pstmt2.setInt(1, cuenta);
                pstmt2.setString(2, movimiento);
                pstmt2.setDouble(3, importe);
                pstmt2.executeUpdate();

                conn.commit();
                System.out.println("Se han actualizado los datos de la cuenta.");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Ha ocurrido un error. Se hizo rollback.");
                
                //error 3819 violación de check constraint (saldo_positivo)
                if (e.getErrorCode() == 3819) {
                    System.out.println("Saldo insuficiente para realizar la extracción.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
