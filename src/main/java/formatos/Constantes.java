/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formatos;

/**
 *
 * @author samuelson
 */
public class Constantes {
    //Constantes de paneles laterales
    public static final int CREAR_TIENDA = 11;
    public static final int CREAR_EMPLEADO = 12;
    public static final int CREAR_CLIENTE = 13;
    public static final int CREAR_PRODUCTO = 14;
    public static final int CREAR_TIEMPO = 15;
    
    public static final String [] Tienda = {"Nombre", "direccion", "codigo","telefono", "telefono 2", "email", "horario"};
    public static final String [] Cliente = {"NIT","Nombre","Telefono","Dirección","E-mail","DPI","Credito"};
    public static final String [] Empleado = {"Codigo","Nombre","Telefono","DPI","Direccion","E-mail","Nit"};
    public static final String [] Producto = {"Codigo","Nombre","Fabricante","Precio","Descripcion","Garantia"};
    public static final String [] Existencia = {"Nombre","Codigo","Fabricante","Precio","Descripcion","garantia","cantidad","tienda"};
    public static final String [] Tiempo = {"Codigo","Dias","Tienda emisora", "Tienda receptora"};
    public static final String [] Venta = {"Codigo", "Fecha", "Cantidad", "Anticipo", "Total", "NIT Cliente"};
    public static final String [] Pedido = {"Codigo", "Fecha", "Recibido", "Cantidad", "Anticipo", "Total", "ID Tiempo", "NIT Cliente"};
}
