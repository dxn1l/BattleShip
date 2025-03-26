package org.example;

import org.example.grafo.GrafoPuertos;

import java.util.Scanner;

public class MainGrafoPuertos {
    public static void main(String[] args) {
        GrafoPuertos grafo = new GrafoPuertos();
        Scanner scanner = new Scanner(System.in);

        // Agregar algunos puertos
        grafo.agregarPuerto("Puerto Madero");
        grafo.agregarPuerto("Puerto de Rodas");
        grafo.agregarPuerto("Puerto Valencia");
        grafo.agregarPuerto("Puerto Lisboa");
        grafo.agregarPuerto("Puerto Génova");

        // Conectar puertos (grafo no dirigido con distancias)
        grafo.conectarPuertos("Puerto Madero", "Puerto Valencia", 10);
        grafo.conectarPuertos("Puerto Valencia", "Puerto Lisboa", 8);
        grafo.conectarPuertos("Puerto Lisboa", "Puerto de Rodas", 6);
        grafo.conectarPuertos("Puerto Madero", "Puerto Génova", 15);
        grafo.conectarPuertos("Puerto Génova", "Puerto de Rodas", 5);
        grafo.conectarPuertos("Puerto Valencia", "Puerto de Rodas", 12);

        int opcion;
        do {
            System.out.println("\n--- MENÚ GRAFO DE PUERTOS ---");
            System.out.println("1. Barrido en profundidad (DFS)");
            System.out.println("2. Camino más corto entre Madero y Rodas");
            System.out.println("3. Eliminar puerto con más conexiones");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    grafo.dfs("Puerto Madero");
                    break;
                case 2:
                    grafo.caminoMasCorto("Puerto Madero", "Puerto de Rodas");
                    break;
                case 3:
                    grafo.eliminarPuertoConMasAristas();
                    break;
                case 0:
                    System.out.println("Saliendo del grafo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}
