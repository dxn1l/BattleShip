package org.BattleShip.jugador;

import org.example.jugador.Jugador;
import org.example.jugador.JugadorBuilderImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JugadorBuilderTest {

    @Test
    public void testJugadorBuilder() {
        JugadorBuilderImpl builder = new JugadorBuilderImpl();
        builder.setNombre("Alice");
        builder.setTableroPropio();
        builder.setTableroEnemigo();

        Jugador jugador = builder.build();

        assertEquals("Alice", jugador.getNombre());
        assertNotNull(jugador.getTableroPropio());
        assertNotNull(jugador.getTableroEnemigo());
    }
}
