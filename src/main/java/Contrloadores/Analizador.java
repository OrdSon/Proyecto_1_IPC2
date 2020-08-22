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
     * @throws ListasYPilas.ExcepcionListaEnlazada
     *
     */
//    public ListaEnlazada<Dato> exportarDatos() {
//
//    }
    String [] tipos = {"TIENDA","TIEMPO","PRODUCTO","CLIENTE","PEDIDO"};
    ListaEnlazada<String> errores;
    
    public String[] leerArchivo() throws ExcepcionListaEnlazada {
        String linea;
        ListaEnlazada<String> lineas = new ListaEnlazada();
        try {
            BufferedReader lector = new BufferedReader(//copio todas las lineas una a una a una
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
    public void distribuirStrings(String [] cadenas){
        int contador = 0;
        
        for (int i = 0; i < cadenas.length; i++) {
            String [] sinComas = cadenas[i].split(",");
            for (int j = 0; j < tipos.length; j++) {
                String parametroInicial = sinComas[0];
                if (parametroInicial.equalsIgnoreCase(tipos[j])) {
                    contador ++;
                }
            }
            if (contador == 0) {
                errores.add(cadenas[i]);
            }else{
                String parametroInicial = sinComas[i];
                if (parametroInicial.equalsIgnoreCase("TIENDA")) {
                    if (sinComas.length!=5) {
                        errores.add(cadenas[i]);
                    }else{
                        
                    }
                }else if (parametroInicial.equalsIgnoreCase("TIEMPO")) {
                    if (sinComas.length!=4) {
                        errores.add(cadenas[i]);
                    }else{
                        
                    }
                }else if (parametroInicial.equalsIgnoreCase("PRODUCTO")) {
                    if (sinComas.length!=7) {
                        errores.add(cadenas[i]);
                    }else{
                        
                    }
                }else if (parametroInicial.equalsIgnoreCase("EMPLEADO")) {
                    if (sinComas.length!=5) {
                        errores.add(cadenas[i]);
                    }else{
                        
                    }
                }else if (parametroInicial.equalsIgnoreCase("CLIENTE")) {
                    if (sinComas.length!=5) {
                        errores.add(cadenas[i]);
                    }else{
                        
                    }
                }else if (parametroInicial.equalsIgnoreCase("PEDIDO")) {
                    if (sinComas.length!=10) {
                        errores.add(cadenas[i]);
                    }else{
                        
                    }
                }
            }
        }
    }

    public String [] verificarTienda(String [] sinComas){
        if (sinComas.length != 5) {
            return null;
        }
//        String [] nombre = sinComas[1].split("\\s+");
//        String [] direccion = sinComas[2].split("\\s+");
//        String [] codigo = sinComas[3].split("\\s+");
//        String [] telefono = sinComas[4].split("\\s+");
//        String [] aciertos = new String[4];
//        if (nombre[0].equalsIgnoreCase("nombre") && nombre.length == 2) {
//            aciertos[0]=nombre[1];
//        }else{
//            return null;
//        }
        
//        for (int i = 0; i < sinComas.length; i++) {
//            
//            String [] sinEspacios = sinComas[i].split("\\s+");
//            if (sinEspacios[0].equalsIgnoreCase("nombre") && sinEspacios.length ==2) {
//                info[0] = sinEspacios[1];
//            }else if (sinEspacios[0].equalsIgnoreCase("direccion") && sinEspacios.length ==2) {
//                info[1] = sinEspacios[1];
//            }else if (sinEspacios.length == 1) {
//                
//            }
//        }
    }
    
    


}
