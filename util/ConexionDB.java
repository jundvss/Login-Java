package util;

import java.sql.*;

/**
 * Clase singleton para conectar con la base de datos MySQL.
 */
public class ConexionDB {
    private static Connection conn;

    public static Connection getConexion() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/loginapp", "root", "1234"
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}