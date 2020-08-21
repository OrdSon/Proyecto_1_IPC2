/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author samuelson
 */
public class Conexion {

    static Connection conexion;

    public static Connection getConexion() {
        String url = "jdbc:mysql://localhost:3306/Intelaf?useSSL=false";
        String usuario = "empleado";
        String contraseña = "amorAl7R4B4J0.";
        if (conexion != null) {
            System.out.println("conexion guardada");
            return conexion;
        }
        try{
            Connection connection = DriverManager.getConnection(url, usuario, contraseña);
            conexion = connection;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.println("conexion nueva");
        return conexion;
    }
}
