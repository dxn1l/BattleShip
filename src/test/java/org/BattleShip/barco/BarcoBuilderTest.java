package org.BattleShip.barco;

import org.example.barco.Barco;
import org.example.barco.BattleshipBuilder;
import org.example.barco.CanoeBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BarcoBuilderTest {

    @Test
    public void testCanoeBuilder() {
        CanoeBuilder builder = new CanoeBuilder();
        builder.setNombre("Mini");
        builder.setNumero(1);
        builder.setNivel(1);
        builder.setTamanio();

        Barco barco = builder.build();

        assertEquals("Mini", barco.getNombre());
        assertEquals(1, barco.getNumero());
        assertEquals(1, barco.getNivel());
        assertEquals(1, barco.getTamanio());
    }

    @Test
    public void testBattleshipBuilder() {
        BattleshipBuilder builder = new BattleshipBuilder();
        builder.setNombre("Titanic");
        builder.setNumero(100);
        builder.setNivel(5);
        builder.setTamanio();

        Barco barco = builder.build();

        assertEquals(5, barco.getTamanio());
        assertFalse(barco.estaHundido());
    }
}
