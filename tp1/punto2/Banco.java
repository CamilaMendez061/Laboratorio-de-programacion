package entrega.tp1.punto2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import entrega.tp1.ConexionDB;


public class Banco {

    //método para determinar la existencia de una cuenta.
    private static boolean existeCuenta(int cuenta) throws SQLException {
        String sql = "SELECT * FROM cuentas WHERE cuenta = ?";
        boolean existeCuenta = false;

        try (Connection conn = ConexionDB.getConexion();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, cuenta);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    existeCuenta = true;
                }
            } 
            }
        return existeCuenta;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int opcion = -1;
        double importe;

        System.out.println("Bienvenido al mejor sistema bancario del universo!");
        do {
            System.out.println("Ingrese el número de cuenta sobre el que desea operar, o ingrese '0' para salir.");
            int cuenta = scan.nextInt();
            
            if (cuenta == 0) {
                opcion = 0; //salir
                System.out.println("Adiós!");
            } else {
                try {
                    if (!existeCuenta(cuenta)) {
                        System.out.println("La cuenta ingresada no existe.");                        
                    } else {
                        do {
                            System.out.println("Ingrese la operación que desea realizar:\n1. Extracción\n2. Depósito\n0. Salir ");
                            opcion = scan.nextInt();
                            switch (opcion) {
                                case 1: //Extracción
                                    System.out.println("Ingrese la cantidad de dinero que desea extraer:");
                                    importe = scan.nextDouble();
                                    Transaccion.extraer(cuenta, importe);
                                    opcion = 0;
                                    break;
                                case 2: //Depósito
                                    System.out.println("Ingrese la cantidad de dinero que desea depositar:");
                                    importe = scan.nextDouble();
                                    Transaccion.depositar(cuenta, importe);
                                    opcion = 0;
                                    break;
                                case 0: //Salir
                                    break;
                                default:
                                    System.out.println("La opción ingresada no es válida.");
                                    break;
                            }
                        } while (opcion !=0);
                        System.out.println("Adiós!");

                    }
                } catch (SQLException e) {
                    System.out.println("Ha ocurrido un error al tratar de determinar la existencia de la cuenta.");
                    System.out.println(e.getMessage());
                }
            }
        } while (opcion != 0);

        scan.close();

    }
}
