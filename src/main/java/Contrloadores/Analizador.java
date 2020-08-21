/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contrloadores;

import ListasYPilas.ExcepcionListaEnlazada;
import ListasYPilas.ListaEnlazada;
import formatos.Dato;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author samuel
 */
public class Analizador {

    /**
     *
     *
     * @return String [] con los datos aprobados listos para ser ingresados a la
     * base de datos
     *
     */
//    public ListaEnlazada<Dato> exportarDatos() {
//
//    }

    public String[] leerArchivo() throws ExcepcionListaEnlazada {
        String linea;
        ListaEnlazada<String> lineas = new ListaEnlazada();
        try {
            BufferedReader lector = new BufferedReader(
                    new FileReader(selectorDeArchivo()));
            linea = lector.readLine();
            while (linea != null) {
                linea = lector.readLine();
                lineas.add(linea);
            }
        } catch (IOException e) {
        }
        String[] cadenas = new String[lineas.getSize()];
        for (int i = 0; i < lineas.getSize(); i++) {
            cadenas[i] = lineas.get(i);
        }
        return cadenas;
    }

    /**
     * usa FileChooser para obtener ubicacion de archivo.
     *
     * @return path de FileChooser.
     */
    public String selectorDeArchivo() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivos", "txt"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        int seleccion = fileChooser.showOpenDialog(null);//Cambiar null por algo mas!!!
        if (seleccion == APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "Path: " + fileChooser.getSelectedFile().getPath());
            return fileChooser.getSelectedFile().getPath();
        }
        return null;
    }
//    public String[] separaPorComas(String [] cadenas){
//        String[] subCadenas = new String[cadenas.length]
//    }

    public ListaEnlazada<Dato> comprobarFormato(String[] cadenas) {

        ListaEnlazada<Dato> tiendas = new ListaEnlazada();
        ListaEnlazada<Dato> tiempo = new ListaEnlazada();
        ListaEnlazada<Dato> productos = new ListaEnlazada();
        ListaEnlazada<Dato> cliente = new ListaEnlazada();
        ListaEnlazada<Dato> pedidos = new ListaEnlazada();
        ListaEnlazada<Dato> aprobados = new ListaEnlazada();
        ListaEnlazada<Integer> line = new ListaEnlazada();

        for (int i = 0; i < cadenas.length; i++) {
            String[] parametro = cadenas[i].split("\\s+");

        }
        return null;
    }


}
