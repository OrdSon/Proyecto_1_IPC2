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
public class Nodo<T> implements Serializable{
    int indiceNodo;
    private T contenido;
    private Nodo<T> siguiente;

    public Nodo(T contenido) {
        this.contenido = contenido;
    }

    public Nodo(T contenido, Nodo<T> siguiente) {
        this.contenido = contenido;
        this.siguiente = siguiente;

    }

    public T getContenido() {
        return contenido;
    }

    public void setContenido(T contenido) {
        this.contenido = contenido;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

    public int getIndiceNodo() {
        return indiceNodo;
    }

    public void setIndiceNodo(int indiceNodo) {
        this.indiceNodo = indiceNodo;
    }
    
}
