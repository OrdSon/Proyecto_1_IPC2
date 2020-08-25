/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Conexion.Conexion;
import formatos.Entidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author samuelson
 */
public class Lector {

    public Lector() {
    }
    
    public void leerTabla(int tipo){
        String query = "";
        switch (tipo) {
            case Entidad.TIENDA:
                query = "SELECT * FROM Tienda";
                break;
            case Entidad.CLIENTE:
                query = "SELECT * FROM Cliente";
                break;
            case Entidad.EMPLEADO:
                query = "SELECT * FROM Empleado";
                break;
            case Entidad.PRODUCTO:
                query = "SELECT * FROM Producto";
                break;
            case Entidad.PRODUCTO_TIENDA:
                query = "SELECT * FROM Tienda_tiene_Producto";
                break;
            case Entidad.INFO_COMPRA:
                break;
            case Entidad.TIEMPO:
                query = "SELECT * FROM Tiempo";
                break;
            case Entidad.INFO_COMPRA_PRODUCTO:
                break;
            case Entidad.VENTA:
                query = "SELECT Venta.*, info_compra.* FROM Venta, info_compra";
                break;
            case Entidad.PEDIDO:
                query = "SELECT Pedido.*, info_compra.* FROM Pedido, info_compra";
                break;
            default:
                break;
        }
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query);
                ResultSet resultado = estado.executeQuery()) {
            ResultSetMetaData meta = resultado.getMetaData();
            int columnas = meta.getColumnCount();
            int tuplas = 0;
            while (resultado.next()) {                
                tuplas++;
            }
            Object[][] info = new Object[tuplas][columnas]; 
            int contador = 0;
            while (resultado.next()) {                
                for (int i = 0; i < columnas; i++) {
                    info[contador][i] = resultado.getObject(i+1);
                }
                contador++;
            }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    
        
    }
    
}
