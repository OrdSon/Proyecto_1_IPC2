/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Conexion.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.JOptionPane;

/**
 *
 * @author samuelson
 */
public class Editor {

    public Editor() {
    }
    
     /**
     *
     * @param info (nombre, direccion, telefono1, telefono2, email, horario, codigo);
     */
    public void crearTienda(String[] info) {
        String query = "UPDATE Tienda SET nombre = ?, direccion = ?, telefono1 = ?, telefono2 = ?, email = ?, horarop = ? WHERE codigo=?";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < info.length; i++) {
                if (info[i] == null || info[i].equals("") || info[i].equals("\\s+")) {
                    estado.setNull((i + 1), Types.VARCHAR);
                } else {
                    estado.setString((i + 1), info[i]);
                }
            }
            estado.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error: " + e.getMessage());
        }
    }

    /**
     *
     * @param info (NIT, nombre, telefono, direccion, email, DPI, CREDITO)
     */
    public void crearCliente(String[] info, String codigo) {
        String query = "UPDATE Cliente SET nombre = ?, telefono = ?, direccion = ?, email = ?, DPI = ?, CREDITO = ? WHERE NIT = ?";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < info.length; i++) {
                if (info[i] == null || info[i].equals("") || info[i].equals("\\s+")) {
                    estado.setNull((i + 1), Types.VARCHAR);
                } else if (i == (info.length - 1)) {
                    estado.setDouble((i + 1), Double.parseDouble(info[i]));
                } else {
                    estado.setString((i + 1), info[i]);
                }
            }
            estado.setString(7, codigo);
            
            estado.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void crearTiempo(String[] info) {
        String query = "UPDATE Tiempo SET tiempo = ? WHERE tienda_emisora = ? AND tienda_receptora = ?";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            estado.setInt(1, Integer.parseInt(info[0]));
            estado.setString(2, info[1]);
            estado.setString(3, info[2]);
            estado.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     *
     * @param info (codigo, nombre, telefono, DPI, direccion, email, NIT)
     */
    public void crearEmpleado(String[] info) {
        String query = "INSERT INTO Empleado (codigo, nombre, telefono, DPI, direccion, email, NIT) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < info.length; i++) {
                if (info[i] == null || info[i].equals("") || info[i].equals("\\s+")) {
                    estado.setNull((i + 1), Types.VARCHAR);
                } else {
                    estado.setString((i + 1), info[i]);
                }
            }estado.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     *
     * @param info
     * (codigo,nombre,fabricante,precio,descripcion,garantia,codigo_tienda,codigo_producto,cantidad);
     * Array tamaño 9
     */
    public void crearProducto(String[] info) {
        String queryExtra = "INSERT INTO Tienda_tiene_Producto(codigo_tienda, codigo_producto, cantidad) VALUES(?,?,?)";
        String query = "INSERT INTO Producto (codigo,nombre,fabricante,precio,descripcion,garantia) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < 6; i++) {

                if (info[i] == null || info[i].equals("") || info[i].equals("\\s+")) {
                    estado.setNull((i + 1), Types.VARCHAR);
                } else if (i == 3) {
                    if (info[i].equals("")) {
                        estado.setNull(i + 1, Types.DOUBLE);
                    } else {
                        estado.setDouble((i + 1), Double.parseDouble(info[i]));
                    }
                } else if (i == 5) {
                    if (info[i].equals("")) {
                        estado.setNull(i + 1, Types.INTEGER);
                    } else {
                        estado.setInt(i + 1, Integer.parseInt(info[i]));
                    }
                } else {
                    estado.setString((i + 1), info[i]);
                }
            }estado.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(queryExtra, Statement.CLOSE_CURRENT_RESULT)) {
            estado.setString(1, info[6]);
            estado.setString(2, info[7]);
            estado.setInt(2, Integer.parseInt(info[8]));
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    /**
     *
     * @param info
     * (anticipo, total, codigo_tiempo,NIT_cliente)
     */
    public void crearInfoDeCompra(String[] info) {
        String query = "INSERT INTO info_compra (anticipo, total, codigo_tiempo, NIT_cliente)";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            estado.setDouble(3, Double.parseDouble(info[0]));
            estado.setDouble(4, Double.parseDouble(info[1]));
            estado.setInt(5, Integer.parseInt(info[2]));
            estado.setString(6, info[3]);
            estado.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     *
     * @param info
     * (codigo, fecha, recibido, codigo_compra);
     */
    public void crearPedido(String [] info){
        String query = "INSERT INTO Pedido (codigo, fecha, recibido, codigo_compra) VALUES (?,?,?,?)";  
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            Date date = Date.valueOf(info[0]);
            estado.setDate(2, date);
            if (info[1] == null) {
                estado.setNull(3, Types.TINYINT);
            }else{
                estado.setInt(3, Integer.parseInt(info[1]));
            }
            estado.setInt(4, Integer.parseInt(info[2]));
            estado.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public void crearListaProductos(String [] info){
        String query = "INSERT INTO info_compra_producto (codigo_producto, cantidad, codigo_compra) VALUES (?,?,?)";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            estado.setString(1, info[0]);
            estado.setString(2, info[1]);
            estado.setInt(3, Integer.parseInt(info[2]));
            estado.executeUpdate();
        } catch (SQLException e) {
        }
    }
    public void crearSesion(String cliente, String tienda){
        String query = "INSERT INTO Sesion (codigo_tienda, codigo_cliente) VALUES (?,?)";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            estado.setString(1, cliente);
            estado.setString(2, tienda);
            estado.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public void crearVenta(String fecha, String codigoVenta){
        String query = "INSERT INTO Venta (fecha, codigo_compra) VALUES (?,?)";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            Date date = Date.valueOf(fecha);
            estado.setDate(1, date);
            estado.setInt(2, Integer.parseInt(codigoVenta));
            estado.executeUpdate();
        } catch (SQLException e) {
        }
    }
    }
    
}
