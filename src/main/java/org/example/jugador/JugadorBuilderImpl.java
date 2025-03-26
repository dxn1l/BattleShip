package org.example.jugador;

import org.example.tablero.Tablero;

public class JugadorBuilderImpl implements JugadorBuilder {
    private String nombre;
    private Tablero tableroPropio;
    private Tablero tableroEnemigo;

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void setTableroPropio() {
        this.tableroPropio = new Tablero(10, 10);
    }

    @Override
    public void setTableroEnemigo() {
        this.tableroEnemigo = new Tablero(10, 10);
    }

    @Override
    public Jugador build() {
        return new Jugador(nombre, tableroPropio, tableroEnemigo);
    }
}
