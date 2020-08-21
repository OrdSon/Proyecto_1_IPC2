/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListasYPilas;

/**
 *
 * @author samuel
 * @param <T>
 */
public class Pila <T>{
    
    private Nodo<T> tope;
    private int tamaño;
    
    public Pila(){
        tope = null;
        this.tamaño = 0;
    }
    
    public boolean esVacia(){
        return tope == null;
    }
    
    public int getSize(){
        return this.tamaño;
    }
    
    public T pop(){
        if(esVacia()){
            return null;
        }else{
            T contenido = tope.getContenido();
            Nodo <T> temporal = tope.getSiguiente();
            tope = null;
            tope = temporal;
            this.tamaño--;
            return contenido;
        }
    }
    
    public void push(T elemento){
        Nodo<T> contenido = new Nodo<>(elemento, tope);
        tope =  contenido;
        this.tamaño++;
        
    }
    
    public T top(){
        if(esVacia()){
           return null;
        }else{
            return tope.getContenido();
        }
    }
    
    public String toString(){
        if (esVacia()) {
            return"Pila Vacia";
        }else{
            String resultado = "";
            Nodo<T> aux = tope;
            while(aux != null){
                resultado += aux.toString();
                aux = aux.getSiguiente();
            }
            return resultado;
        }
    }
}
