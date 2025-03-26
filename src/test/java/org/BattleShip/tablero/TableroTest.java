package org.BattleShip.tablero;

import org.example.tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TableroTest {

    @Test
    public void testColocarYAtacar() {
        Tablero tablero = new Tablero(10, 10);
        tablero.colocarBarco(0, 0, 3, "H");

        assertTrue(tablero.recibirAtaque(0, 0)); // tocado
        assertFalse(tablero.recibirAtaque(5, 5)); // agua
    }
}
