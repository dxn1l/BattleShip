package org.example.jugador;

import org.example.tablero.Tablero;

public class Jugador {
    private String nombre;
    private Tablero tableroPropio;
    private Tablero tableroEnemigo;

    public Jugador(String nombre, Tablero tableroPropio, Tablero tableroEnemigo) {
        this.nombre = nombre;
        this.tableroPropio = tableroPropio;
        this.tableroEnemigo = tableroEnemigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Tablero getTableroPropio() {
        return tableroPropio;
    }

    public Tablero getTableroEnemigo() {
        return tableroEnemigo;
    }
}
