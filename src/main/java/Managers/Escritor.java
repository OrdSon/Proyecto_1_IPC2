/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Conexion.Conexion;
import ListasYPilas.ExcepcionListaEnlazada;
import ListasYPilas.ListaEnlazada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author samuelson
 */
public class Escritor {

    public Escritor() {
    }
    
    /**
     *
     * @param connection
     * @param columna
     * @param info
     * 
     * Tienda(nombre, telefono1, telefono2, direccion, email, horario);
     * Empleado(nombre, telefono,DPI,);
     */
    public static void crear(String columna, ListaEnlazada<String> info)  {
//        String query = "INSERT INTO CLIENTE VALUES (?,?,?,?)";
        String consulta = "INSERT INTO " + columna + " VALUES(?";
        for (int i = 0; i < info.getSize()-1; i++) {
             consulta = consulta + ",?";
        }
        consulta = consulta + ")";
        
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(consulta)) {

            for (int i = 0; i < info.getSize(); i++) {
                if (info.get(i).equalsIgnoreCase("null") || info.get(i).equals("")) {
                    estado.setNull((i+2),Types.VARCHAR);
                }else{
                    estado.setString((i+2), info.get(i));
                }
            }
            estado.executeUpdate();
            JOptionPane.showMessageDialog(null, "pues si xD");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ExcepcionListaEnlazada ex) {
            JOptionPane.showMessageDialog(null, "pues no :V");
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
