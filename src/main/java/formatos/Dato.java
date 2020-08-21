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
public class Dato {

    private String info;
    private String tipo;
    private int linea;

    public Dato() {
    }

    public Dato(String info, String tipo, int linea) {
        this.info = info;
        this.tipo = tipo;
        this.linea = linea;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

}
