/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListasYPilas;

import java.io.Serializable;

/**
 *
 * @author Samuel
 */
public class ExcepcionListaEnlazada extends java.lang.Exception implements Serializable{
    public ExcepcionListaEnlazada(String mensaje){
        super(mensaje);
    }
}
