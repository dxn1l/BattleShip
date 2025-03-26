package org.example.jugador;

public class GameDirector {
    private JugadorBuilder builder;

    public void setBuilder(JugadorBuilder builder) {
        this.builder = builder;
    }

    public Jugador construct(String nombre) {
        builder.setNombre(nombre);
        builder.setTableroPropio();
        builder.setTableroEnemigo();
        return builder.build();
    }
}
