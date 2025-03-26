package org.BattleShip.grafo;


import org.example.grafo.GrafoPuertos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class GrafoPuertosTest {

    @Test
    public void testConexionYCaminoCorto() {
        GrafoPuertos grafo = new GrafoPuertos();

        grafo.agregarPuerto("A");
        grafo.agregarPuerto("B");
        grafo.conectarPuertos("A", "B", 10);

        assertDoesNotThrow(() -> grafo.caminoMasCorto("A", "B"));
    }

    @Test
    public void testPuertoConMasConexionesEsEliminado() {
        GrafoPuertos grafo = new GrafoPuertos();

        grafo.conectarPuertos("A", "B", 1);
        grafo.conectarPuertos("A", "C", 1);
        grafo.conectarPuertos("A", "D", 1);
        grafo.conectarPuertos("B", "C", 1);

        grafo.eliminarPuertoConMasAristas();

        // Luego de eliminar "A", no debería haber conexión entre A y los demás
        assertDoesNotThrow(() -> grafo.caminoMasCorto("B", "C")); // aún debe ser posible
    }
}
