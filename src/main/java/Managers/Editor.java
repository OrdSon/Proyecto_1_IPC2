/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Conexion.Conexion;
import formatos.Entidad;
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
     * @param info (nombre, direccion, telefono1, telefono2, email, horario,
     * codigo);
     */
    public void crearTienda(String[] info) {
        String query = "UPDATE Tienda SET nombre = ?, direccion = ?, telefono1 = ?, telefono2 = ?, email = ?, horario = ? WHERE codigo=?";
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
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
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
     * @param codigo
     */
    public void crearEmpleado(String[] info, String codigo) {
        String query = "UPDATE Empleado SET nombre = ?, telefono = ?, DPI = ?, direccion = ?, email = ?, NIT = ? WHERE codigo = ?";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < info.length; i++) {
                if (info[i] == null || info[i].equals("") || info[i].equals("\\s+")) {
                    estado.setNull((i + 1), Types.VARCHAR);
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

    /**
     *
     * @param info
     * (codigo,nombre,fabricante,precio,descripcion,garantia,codigo_tienda,codigo_producto,cantidad);
     * Array tamaño 9
     */
    public void crearProducto(String[] info) {
        String queryExtra = "UPDATE Tienda_tiene_Producto SET cantidad = ? WHERE codigo_tienda = ? AND codigo_producto = ?";
        String query = "UPDATE Prdocuto SET nombre = ?, fabricante = ?, precio = ?, descripcion = ?, garantia = ? WHERE codigo = ?";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            for (int i = 1; i < 6; i++) {

                if (info[i] == null || info[i].equals("") || info[i].equals("\\s+")) {
                    estado.setNull((i + 1), Types.VARCHAR);
                } else if (i == 2) {
                    if (info[i].equals("")) {
                        estado.setNull(i + 1, Types.DOUBLE);
                    } else {
                        estado.setDouble((i + 1), Double.parseDouble(info[i]));
                    }
                } else if (i == 4) {
                    if (info[i].equals("")) {
                        estado.setNull(i + 1, Types.INTEGER);
                    } else {
                        estado.setInt(i + 1, Integer.parseInt(info[i]));
                    }
                } else {
                    estado.setString((i + 1), info[i]);
                }
            }
            estado.setString(1, info[0]);
            estado.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(queryExtra, Statement.CLOSE_CURRENT_RESULT)) {
            estado.setInt(1, Integer.parseInt(info[8]));
            estado.setString(2, info[6]);
            estado.setString(3, info[0]);
            estado.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    /**
     *
     * @param recibido
     * @param tipo
     * @param codigo
     */
    public void recibirPedido(boolean recibido, int tipo, int codigo) {
        String query = "";
        if (tipo == Entidad.PEDIDO_ANTIGUO) {
            query = "UPDATE Pedido SET recibido = ? WHERE codigo = ?";
        }else if (tipo == Entidad.PEDIDO) {
            query = "UPDATE Pedido_antiguo SET recibido = ? WHERE codigo = ?";
        }
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            if (recibido == true) {
                estado.setInt(1, 1);
            }else{
                estado.setInt(1, 0);
            }
            estado.setInt(2, codigo);
        } catch (SQLException e) {
        }
    }

}
