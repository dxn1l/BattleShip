package org.example.jugador;

public interface JugadorBuilder {
    void setNombre(String nombre);
    void setTableroPropio();
    void setTableroEnemigo();
    Jugador build();
}
