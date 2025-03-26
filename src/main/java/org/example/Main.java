package org.example;

import org.example.barco.*;
import org.example.hash.HashTipoBarco;
import org.example.jugador.GameDirector;
import org.example.jugador.Jugador;
import org.example.jugador.JugadorBuilderImpl;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashTipoBarco gestorHash = new HashTipoBarco();

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
                gestorHash.insertarCompleto(tipo, barco);
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

        System.out.println("\n--- CONSULTA DE BARCOS (Tablas Hash) ---");
        int opcion;
        do {
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Buscar barcos por tipo");
            System.out.println("2. Buscar barco por número");
            System.out.println("3. Buscar barco por nombre");
            System.out.println("0. Continuar al juego");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Introduce tipo de barco (Battleship / Frigate / Canoe): ");
                    String tipoBuscar = scanner.nextLine().trim();
                    List<Barco> barcosTipo = gestorHash.buscarPorTipo(tipoBuscar);
                    if (barcosTipo.isEmpty()) {
                        System.out.println("No se encontraron barcos de tipo " + tipoBuscar);
                    } else {
                        System.out.println("Barcos encontrados:");
                        for (Barco b : barcosTipo) {
                            System.out.println("- " + b.getNombre() + " (#" + b.getNumero() + ")");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Introduce número del barco: ");
                    int numBuscar = Integer.parseInt(scanner.nextLine());
                    Barco barcoNum = gestorHash.buscarPorNumero(numBuscar);
                    if (barcoNum == null) {
                        System.out.println("No se encontró barco con número " + numBuscar);
                    } else {
                        System.out.println("Barco encontrado: " + barcoNum.getNombre() + " de tamaño " + barcoNum.getTamanio());
                    }
                    break;

                case 3:
                    System.out.print("Introduce nombre del barco: ");
                    String nombreBuscar = scanner.nextLine();
                    Barco barcoNombre = gestorHash.buscarPorNombre(nombreBuscar);
                    if (barcoNombre == null) {
                        System.out.println("No se encontró barco con nombre " + nombreBuscar);
                    } else {
                        System.out.println("Barco encontrado: #" + barcoNombre.getNumero() + " de tipo tamaño " + barcoNombre.getTamanio());
                    }
                    break;

                case 0:
                    System.out.println("Comenzando el juego...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);


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

