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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
     *
     *
     * @param args
     */
    public static void main(String[] args) {
//        String[] info = {"prueba", "ciudad", "980", "55544488", "45645612"};
        Escritor escritor = new Escritor();
        //(codigo,nombre,fabricante,precio,descripcion,garantia,codigo_tienda,codigo_producto,cantidad);
        String[] Producto = {"c2", "Barbaro", "fabrica", "50", "es una barbaro", "12", "2", "2", "500"};
        escritor.crearProducto(Producto);
    }

    /**
     *
     * @param info (nombre, direccion, codigo, telefono1, telefono2, email,
     * horario);
     */
    public void crearTienda(String[] info) {
        String query = "INSERT INTO Tienda (nombre, direccion, codigo, telefono1, telefono2, email, horario)VALUES (?,?,?,?,?,?,?)";
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
    public void crearCliente(String[] info) {
        String query = "INSERT INTO Cliente (NIT, nombre, telefono, direccion, email, DPI, CREDITO) VALUES (?,?,?,?,?,?,?)";
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
            estado.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void crearTiempo(String[] info) {
        String query = "INSERT INTO Tiempo (tienda_emisora, tienda_receptora, tiempo) VALUES (?,?,?)";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            estado.setString(1, info[0]);
            estado.setString(2, info[1]);
            estado.setInt(3, Integer.parseInt(info[2]));
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
            }
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
            }
            estado.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(queryExtra, Statement.CLOSE_CURRENT_RESULT)) {
            estado.setString(1, info[6]);
            estado.setString(2, info[0]);
            estado.setInt(3, Integer.parseInt(info[7]));
            estado.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     *
     * @param info (anticipo, total, codigo_tiempo,NIT_cliente)
     */
    public void crearInfoDeCompra(String[] info) {
        String query = "INSERT INTO info_compra (anticipo, total, codigo_tiempo, NIT_cliente) VALUES (?,?,?,?)";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            estado.setDouble(1, Double.parseDouble(info[0]));
            estado.setDouble(2, Double.parseDouble(info[1]));
            estado.setInt(3, Integer.parseInt(info[2]));
            estado.setString(4, info[3]);
            estado.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     *
     * @param info (codigo, fecha, recibido, codigo_compra);
     */
    public void crearPedidoAntiguo(String[] info) {
        String query = "INSERT INTO Pedido (codigo, fecha, recibido, codigo_compra) VALUES (?,?,?,?)";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            estado.setInt(1, Integer.parseInt(info[0]));
            Date date = Date.valueOf(info[1]);
            estado.setDate(2, date);
            if (info[1] == null) {
                estado.setNull(3, Types.TINYINT);
            } else {
                estado.setInt(3, Integer.parseInt(info[1]));
            }
            estado.setInt(4, Integer.parseInt(info[2]));
            estado.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void crearPedido(String[] info) {
        String query = "INSERT INTO Pedido (fecha, recibido, codigo_compra) VALUES (?,?,?)";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            Date date = Date.valueOf(info[0]);
            estado.setDate(2, date);
            if (info[1] == null) {
                estado.setNull(3, Types.TINYINT);
            } else {
                estado.setInt(3, Integer.parseInt(info[1]));
            }
            estado.setInt(4, Integer.parseInt(info[2]));
            estado.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void crearListaProductos(String[] info) {
        String query = "INSERT INTO info_compra_producto (codigo_producto, cantidad, codigo_compra) VALUES (?,?,?)";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            estado.setString(1, info[0]);
            estado.setString(2, info[1]);
            estado.setInt(3, Integer.parseInt(info[2]));
            estado.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void crearSesion(String cliente, String tienda) {
        String query = "INSERT INTO Sesion (codigo_tienda, codigo_cliente) VALUES (?,?)";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            estado.setString(1, cliente);
            estado.setString(2, tienda);
            estado.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void crearVenta(String fecha, String codigoVenta) {
        String query = "INSERT INTO Venta (fecha, codigo_compra) VALUES (?,?)";
        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            Date date = Date.valueOf(fecha);
            estado.setDate(1, date);
            estado.setInt(2, Integer.parseInt(codigoVenta));
            estado.executeUpdate();
        } catch (SQLException e) {
        }
    }
}

//    public static void crear(String[] info,String tipo)  {
//        String query = "";
//        if (tipo.equals(Entidad.TIENDA)) {
//            query = "INSERT INTO Tienda (nombre, direccion, codigo, telefono1, telefono2)VALUES (?,?,?,?,?)";
//        }else if (tipo.equals(Entidad.TIEMPO)) {
//            query = "INSERT INTO Tiempo (tienda_emisora, tienda_receptora, tiempo) VALUES (?,?,?)";
//        }else if (tipo.equals(Entidad.PRODUCTO)) {
//            query = "INSERT INTO Producto (nombre, fabricante, codigo, precio) VALUES (?,?,?,?,?)";
//        }else if (tipo.equals(Entidad.PRODUCTO_TIENDA)) {
//            query = "INSERT INTO Tienda_tiene_Producto(codigo_tienda, codigo_producto, cantidad) VALUES(?,?,?)";
//        }else if (tipo.equals(Entidad.EMPLEADO)) {
//            query = "INSERT INTO Empleado (nombre, codigo, NIT, DPI) VALUES (?,?,?,?)";
//        }else if (tipo.equals(Entidad.CLIENTE)) {
//            query = "INSERT INTO Cliente (nombre, NIT, DPI, credito) VALUES (?,?,?,?)";            
//        }else if (tipo.equals(Entidad.PEDIDO_ANTIGUO)) {
//            query = "INSERT INTO Pedido_antiguo (codigo, fecha) VALUES (?,?)";            
//        }else if(tipo.equals())
//        
//        try (PreparedStatement estado = Conexion.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
//            for (int i = 0; i < info.length; i++) {
//                if (info[i].equals("")||info[i].eq) {
//                    
//                }
//                if (tipo.equals("Tiempo") && i == (info.length-1)) {
//                    estado.setInt(i+1, Integer.parseInt(info[i]));
//                }else if (tipo.equals("Producto") && i == 3) {
//                    estado.setDouble(i+1, Double.parseDouble(info[i]));
//                }else if(tipo.equals("Tienda_tiene_Producto") && i == 2){
//                    estado.setInt(i+1, Integer.parseInt(info[2]));
//                }else if (tipo.equals(Entidad.CLIENTE) && i == 3) {
//                    estado.setInt(i+1, Integer.parseInt(info[i]));
//                }else if (tipo.equals(Entidad.PEDIDO_ANTIGUO)) {
//                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MMM-yyyy");  
//                    estado.setInt(1, Integer.parseInt(info[0]));
//                    java.sql.Date fecha = new java.sql.Date(formatoFecha.parse(info[1]).getTime());
//                    estado.setDate(i, fecha);
//                }
//                estado.setString(i+1, info[i]);
//            }
//            if (tipo.equals(Entidad.PRODUCTO)) {
//                //codigo de producto, cantidad, tienda;
//                String [] datos = {info[2], info[4], info[5]};
//                crear(datos, Entidad.PRODUCTO_TIENDA);
//            }else if (tipo.equals(Entidad.PEDIDO_ANTIGUO)) {
//                //tienda1, tienda2, nit,anticipo
//                String [] datos = {info[2], info[3], info[4], info[5]};
//                String [] producto = {info[6], info[7]};
//                crear(datos, Entidad.INFO_COMPRA);
//                crear(producto, Entidad.INFO_COMPRA_PRODUCTO);
//            }
//            estado.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("Error: " + e.getMessage());
//        } catch (ParseException ex) {
//            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

