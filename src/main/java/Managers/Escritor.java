/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author samuelson
 */
public class Escritor {

    public Escritor() {
    }
    
    /**
     *
     * @param info
     * 
     * 
     */
    public void crearTienda(String[] info)  {
        String query = "INSERT INTO TIENDA (nombre, direccion, codigo, telefono)VALUES (?,?,?,?)";
        String nombre = info[1];
        String direccion = info[2];
        String codigo = info[3];
        String telefono = info[4];

        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            estado.setString(1, nombre);
            estado.setString(2, direccion);
            estado.setString(3, codigo);
            estado.setString(4, telefono);

            estado.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
