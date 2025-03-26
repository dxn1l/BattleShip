package org.example;

import org.example.barco.*;
import org.example.jugador.GameDirector;
import org.example.jugador.Jugador;
import org.example.jugador.JugadorBuilderImpl;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GameDirector jugadorDirector = new GameDirector();
        JugadorBuilderImpl jugadorBuilder = new JugadorBuilderImpl();
        jugadorDirector.setBuilder(jugadorBuilder);

        System.out.print("Introduce el nombre del Jugador 1: ");
        Jugador jugador1 = jugadorDirector.construct(scanner.nextLine());

        System.out.print("Introduce el nombre del Jugador 2: ");
        Jugador jugador2 = jugadorDirector.construct(scanner.nextLine());

        System.out.println("\n--- Configuración de barcos ---");

        Map<Jugador, List<Barco>> barcosPorJugador = new HashMap<>();
        barcosPorJugador.put(jugador1, new ArrayList<>());
        barcosPorJugador.put(jugador2, new ArrayList<>());

        for (Jugador jugador : Arrays.asList(jugador1, jugador2)) {
            System.out.println("\nConfigurando barcos para " + jugador.getNombre());

            for (int i = 0; i < 3; i++) {
                System.out.print("Introduce tipo de barco (Battleship / Frigate / Canoe): ");
                String tipo = scanner.nextLine().trim();

                System.out.print("Nombre del barco: ");
                String nombre = scanner.nextLine();

                System.out.print("Número del barco: ");
                int numero = Integer.parseInt(scanner.nextLine());

                System.out.print("Nivel del barco: ");
                int nivel = Integer.parseInt(scanner.nextLine());

                Director barcoDirector = new Director();
                BarcoBuilder builder;

                switch (tipo.toLowerCase()) {
                    case "battleship":
                        builder = new BattleshipBuilder();
                        break;
                    case "frigate":
                        builder = new FrigateBuilder();
                        break;
                    case "canoe":
                        builder = new CanoeBuilder();
                        break;
                    default:
                        System.out.println("Tipo no válido. Se usará Canoe por defecto.");
                        builder = new CanoeBuilder();
                }

                barcoDirector.setBuilder(builder);
                Barco barco = barcoDirector.construct(nombre, numero, nivel);
                barcosPorJugador.get(jugador).add(barco);

                // Colocación simple: coordenadas y dirección
                System.out.print("Coordenada X (0-9): ");
                int x = Integer.parseInt(scanner.nextLine());

                System.out.print("Coordenada Y (0-9): ");
                int y = Integer.parseInt(scanner.nextLine());

                System.out.print("Dirección (H/V): ");
                String direccion = scanner.nextLine().toUpperCase();

                jugador.getTableroPropio().colocarBarco(x, y, barco.getTamanio(), direccion);
            }
        }

        System.out.println("\n--- ¡Comienza el juego! ---");

        Random random = new Random();
        while (true) {
            // Turno Jugador 1
            int x1 = random.nextInt(10);
            int y1 = random.nextInt(10);
            boolean acierto1 = jugador2.getTableroPropio().recibirAtaque(x1, y1);
            System.out.println(jugador1.getNombre() + " ataca a (" + x1 + ", " + y1 + "): " + (acierto1 ? "Tocado!" : "Agua"));

            // Turno Jugador 2
            int x2 = random.nextInt(10);
            int y2 = random.nextInt(10);
            boolean acierto2 = jugador1.getTableroPropio().recibirAtaque(x2, y2);
            System.out.println(jugador2.getNombre() + " ataca a (" + x2 + ", " + y2 + "): " + (acierto2 ? "Tocado!" : "Agua"));

            // Verificación de fin de juego
            boolean jugador1Hundido = todosHundidos(barcosPorJugador.get(jugador1));
            boolean jugador2Hundido = todosHundidos(barcosPorJugador.get(jugador2));

            if (jugador1Hundido && jugador2Hundido) {
                System.out.println("¡Empate! Ambos jugadores han perdido sus barcos.");
                break;
            } else if (jugador1Hundido) {
                System.out.println("¡" + jugador2.getNombre() + " gana!");
                break;
            } else if (jugador2Hundido) {
                System.out.println("¡" + jugador1.getNombre() + " gana!");
                break;
            }
        }

        scanner.close();
    }

    private static boolean todosHundidos(List<Barco> barcos) {
        for (Barco b : barcos) {
            if (!b.estaHundido()) {
                return false;
            }
        }
        return true;
    }
}

