package org.example.barco;

public class Barco {
    protected String nombre;
    protected int numero;
    protected int nivel;
    protected int tamanio;

    public Barco(String nombre, int numero, int nivel, int tamanio) {
        this.nombre = nombre;
        this.numero = numero;
        this.nivel = nivel;
        this.tamanio = tamanio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumero() {
        return numero;
    }

    public int getNivel() {
        return nivel;
    }

    public int getTamanio() {
        return tamanio;
    }

    public boolean estaHundido() {
        return nivel <= 0;
    }
}
