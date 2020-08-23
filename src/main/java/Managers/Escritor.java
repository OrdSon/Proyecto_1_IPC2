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
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static void main(String[] args) {
        String [] info = {"prueba", "ciudad", "980", "55544488","45645612"};
        crear(info, "Tienda");
        
    }
    public static void crear(String[] info,String tipo)  {
        String query = "";
        if (tipo.equals(Entidad.TIENDA)) {
            query = "INSERT INTO Tienda (nombre, direccion, codigo, telefono1, telefono2)VALUES (?,?,?,?,?)";
        }else if (tipo.equals(Entidad.TIEMPO)) {
            query = "INSERT INTO Tiempo (tienda_emisora, tienda_receptora, tiempo) VALUES (?,?,?)";
        }else if (tipo.equals(Entidad.PRODUCTO)) {
            query = "INSERT INTO Producto (nombre, fabricante, codigo, precio) VALUES (?,?,?,?,?)";
        }else if (tipo.equals(Entidad.PRODUCTO_TIENDA)) {
            query = "INSERT INTO Tienda_tiene_Producto(codigo_tienda, codigo_producto, cantidad) VALUES(?,?,?)";
        }else if (tipo.equals(Entidad.EMPLEADO)) {
            query = "INSERT INTO Empleado (nombre, codigo, NIT, DPI) VALUES (?,?,?,?)";
        }else if (tipo.equals(Entidad.CLIENTE)) {
            query = "INSERT INTO Cliente (nombre, NIT, DPI, credito) VALUES (?,?,?,?)";            
        }else if (tipo.equals(Entidad.PEDIDO_ANTIGUO)) {
            query = "INSERT INTO Pedido_antiguo (codigo, fecha) VALUES (?,?)";            
        }else if(tipo.equals())
        
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < info.length; i++) {
                if (tipo.equals("Tiempo") && i == (info.length-1)) {
                    estado.setInt(i+1, Integer.parseInt(info[i]));
                }else if (tipo.equals("Producto") && i == 3) {
                    estado.setDouble(i+1, Double.parseDouble(info[i]));
                }else if(tipo.equals("Tienda_tiene_Producto") && i == 2){
                    estado.setInt(i+1, Integer.parseInt(info[2]));
                }else if (tipo.equals(Entidad.CLIENTE) && i == 3) {
                    estado.setInt(i+1, Integer.parseInt(info[i]));
                }else if (tipo.equals(Entidad.PEDIDO_ANTIGUO)) {
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MMM-yyyy");  
                    estado.setInt(1, Integer.parseInt(info[0]));
                    java.sql.Date fecha = new java.sql.Date(formatoFecha.parse(info[1]).getTime());
                    estado.setDate(i, fecha);
                }
                estado.setString(i+1, info[i]);
            }
            if (tipo.equals(Entidad.PRODUCTO)) {
                //codigo de producto, cantidad, tienda;
                String [] datos = {info[2], info[4], info[5]};
                crear(datos, Entidad.PRODUCTO_TIENDA);
            }else if (tipo.equals(Entidad.PEDIDO_ANTIGUO)) {
                //tienda1, tienda2, nit,anticipo
                String [] datos = {info[2], info[3], info[4], info[5]};
                String [] producto = {info[6], info[7]};
                crear(datos, Entidad.INFO_COMPRA);
                crear(producto, Entidad.INFO_COMPRA_PRODUCTO);
            }
            estado.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
