package org.example.barco;

public class BattleshipBuilder implements BarcoBuilder {
    private String nombre;
    private int numero;
    private int nivel;
    private int tamanio;

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public void setTamanio() {
        this.tamanio = 5;
    }

    @Override
    public Barco build() {
        return new Barco(nombre, numero, nivel, tamanio);
    }
}

