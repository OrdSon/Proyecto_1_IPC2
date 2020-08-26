/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Conexion.Conexion;
import ListasYPilas.ExcepcionListaEnlazada;
import ListasYPilas.ListaEnlazada;
import formatos.Constantes;
import formatos.Entidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author samuelson
 */
public class Lector {

    public Lector() {
    }

    public DefaultTableModel leerTabla(int tipo) {
        String query = "";
        DefaultTableModel modelo = new DefaultTableModel();
        switch (tipo) {
            case Entidad.TIENDA:
                query = "SELECT * FROM Tienda";
                for (int i = 0; i < Constantes.Tienda.length; i++) {
                    modelo.addColumn(Constantes.Tienda[i]);
                }
                break;
            case Entidad.CLIENTE:
                query = "SELECT * FROM Cliente";
                for (int i = 0; i < Constantes.Cliente.length; i++) {
                    modelo.addColumn(Constantes.Cliente[i]);
                }
                break;
            case Entidad.EMPLEADO:
                query = "SELECT * FROM Empleado";
                for (int i = 0; i < Constantes.Empleado.length; i++) {
                    modelo.addColumn(Constantes.Empleado[i]);
                }
                break;
            case Entidad.PRODUCTO:
                query = "SELECT * FROM Producto";
                for (int i = 0; i < Constantes.Producto.length; i++) {
                    modelo.addColumn(Constantes.Producto[i]);
                }
                break;
            case Entidad.PRODUCTO_TIENDA:
                query = "select Tienda.nombre as Tienda, Producto.*, Tienda_tiene_Producto.cantidad from Producto, Tienda inner join Tienda_tiene_Producto on Tienda.codigo = Tienda_tiene_Producto.codigo_tienda";
                for (int i = 0; i < Constantes.Existencia.length; i++) {
                    modelo.addColumn(Constantes.Existencia[i]);
                }
                break;
            case Entidad.TIEMPO:
                query = "SELECT * FROM Tiempo";
                for (int i = 0; i < Constantes.Tiempo.length; i++) {
                    modelo.addColumn(Constantes.Tiempo[i]);
                }
                break;
            case Entidad.INFO_COMPRA_PRODUCTO:
                break;
            case Entidad.VENTA:
                query = "SELECT Venta.*, info_compra.* FROM Venta, info_compra";
                for (int i = 0; i < Constantes.Venta.length; i++) {
                    modelo.addColumn(Constantes.Venta[i]);
                }
                break;
            case Entidad.PEDIDO:
                query = "SELECT Pedido.*, info_compra.* FROM Pedido, info_compra";
                for (int i = 0; i < Constantes.Pedido.length; i++) {
                    modelo.addColumn(Constantes.Pedido[i]);
                }
                break;
            default:
                break;
        }

        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query);
                ResultSet resultado = estado.executeQuery()) {
            ResultSetMetaData meta = resultado.getMetaData();
            int columnas = meta.getColumnCount();
            int contador = 0;
            while (resultado.next()) {
                Object[] filas = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    filas[i] = (resultado.getObject(i + 1));
                }
                modelo.addRow(filas);
                contador++;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        return modelo;
    }
}
