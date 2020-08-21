/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListasYPilas;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel
 * @param <T>
 */
public class ListaEnlazada<T> implements Serializable {

    private Nodo<T> primerNodo;
    private Nodo<T> ultimoNodo;
    private int ultimoIndice;

    public void add(T contendio) {
        ultimoIndice++;
        Nodo<T> nuevo = new Nodo<>(contendio);
        if (esVacia()) {
            primerNodo = nuevo;
            ultimoNodo = nuevo;
        } else {
            ultimoNodo.setSiguiente(nuevo);
            ultimoNodo = nuevo;
        }
    }

    public void addFirst(T contenido) {
        Nodo<T> temporal;
        if (esVacia()) {
            temporal = new Nodo<>(contenido);
            primerNodo = temporal;
            ultimoNodo = temporal;
        } else {
            temporal = new Nodo<>(contenido, primerNodo);
            primerNodo = temporal;
        }
        ultimoIndice++;
    }

    public boolean esIndiceValido(int indice) {
        return !(indice < 0 || indice >= ultimoIndice);
    }

    public void insertar(T contenido, int indice) throws ExcepcionListaEnlazada {
        if (!esIndiceValido(indice)) {
            System.out.println("indiceChueco " + indice + "tamañoDeLista: " + ultimoIndice);
        }
        if (indice == 0) {
            addFirst(contenido);
        } else if (indice == (ultimoIndice - 1)) {
            add(contenido);
        } else {
            Nodo<T> anterior = obtenerPorIndice(indice - 1);
            Nodo<T> actual = obtenerPorIndice(indice);
            Nodo<T> temporal = new Nodo<>(contenido, actual);
            anterior.setSiguiente(temporal);
            ultimoIndice++;
        }
    }

    public void eliminarUltimo() throws ExcepcionListaEnlazada {
        if (esVacia()) {
            throw new ExcepcionListaEnlazada("ERROR. Lista Vacia");
        }
        if (primerNodo.equals(ultimoNodo)) {
            primerNodo = null;
            ultimoNodo = null;
        } else {
            Nodo<T> penultimo = obtenerPorIndice(ultimoIndice - 2);
            penultimo.setSiguiente(null);
            ultimoNodo = penultimo;
        }
        ultimoIndice--;
    }

    public void eliminarPrimero() throws ExcepcionListaEnlazada {
        if (esVacia()) {
            throw new ExcepcionListaEnlazada("ERROR. Lista Vacia");
        }
        if (primerNodo.equals(ultimoNodo)) {
            primerNodo = null;
            ultimoNodo = null;
        } else {
            primerNodo = primerNodo.getSiguiente();
        }
        ultimoIndice--;
    }

    public int getSize() {
        return ultimoIndice;
    }

    public boolean esVacia() {
        return primerNodo == null;
    }

    public Nodo<T> obtenerPorIndice(int indice) throws ExcepcionListaEnlazada {
        if (esIndiceValido(indice) == false) {
            throw new ExcepcionListaEnlazada("Indice invalido" + indice);
        }
        Nodo<T> buscado = primerNodo;
        for (int i = 0; i < indice; i++) {
            buscado = buscado.getSiguiente();
        }
        return buscado;
    }

    public void eliminar(int indice) throws ExcepcionListaEnlazada {
        if (esVacia() || esIndiceValido(indice) == false) {
        } else if (indice == 0) {
            eliminarPrimero();
        } else if (indice == (ultimoIndice - 1)) {
            eliminarUltimo();
        } else {
            Nodo<T> nodoAnterior = obtenerPorIndice(indice - 1);
            Nodo<T> nodoActual = obtenerPorIndice(indice);
            Nodo<T> nodoSiguiente = nodoActual.getSiguiente();
            nodoActual = null;
            nodoAnterior.setSiguiente(nodoSiguiente);
            ultimoIndice--;
        }
    }

    public T get(int indice) throws ExcepcionListaEnlazada {
        if (esIndiceValido(indice) == false) {
            throw new ExcepcionListaEnlazada("Indice invalido  " + ultimoIndice + "  " + indice);
        }
        Nodo<T> buscado = primerNodo;
        for (int i = 0; i < indice; i++) {
            buscado = buscado.getSiguiente();
        }
        return buscado.getContenido();
    }

    public void modifica(T contenido, int indice) {
        if (esVacia() || esIndiceValido(indice) == false) {
        } else {
            try {
                obtenerPorIndice(indice).setContenido(contenido);
            } catch (ExcepcionListaEnlazada ex) {
                Logger.getLogger(ListaEnlazada.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean existe(T contenido) {
        if (esVacia()) {
            return false;
        } else {
            Nodo<T> temporal = primerNodo;
            while (temporal != null) {
                if (contenido == temporal.getContenido()) {
                    return true;
                }
                temporal = temporal.getSiguiente();
            }

        }
        return false;
    }

    public int indexOf(T contenido) {
        if (esVacia()) {
            return -1;
        } else {
            Nodo<T> temporal = primerNodo;
            int contador = 0;
            while (temporal != null) {
                if (contenido.equals(temporal.getContenido())) {
                    return contador;
                }
                if (contenido == temporal.getContenido()) {
                    return contador;
                }
                contador++;
                temporal = temporal.getSiguiente();
            }

        }
        return -1;
    }

    public void asignaIndices() {
        Nodo<T> buscado = primerNodo;
        buscado.setIndiceNodo(0);
        for (int i = 0; i < ultimoIndice; i++) {
            buscado = buscado.getSiguiente();
            buscado.setIndiceNodo(i + 1);
        }
    }

    public Nodo<T> getPrimerNodo() {
        return primerNodo;
    }

    public T getFirst() {
        return primerNodo.getContenido();
    }

    public T getLast() {
        return ultimoNodo.getContenido();
    }

    public void setFirst(T content) {
        primerNodo.setContenido(content);
    }

    public void setLast(T content) {
        ultimoNodo.setContenido(content);
    }

    public void setPrimerNodo(Nodo<T> primerNodo) {
        this.primerNodo = primerNodo;
    }

    public Nodo<T> getUltimoNodo() {
        return ultimoNodo;
    }

    public void setUltimoNodo(Nodo<T> ultimoNodo) {
        this.ultimoNodo = ultimoNodo;
    }

}
