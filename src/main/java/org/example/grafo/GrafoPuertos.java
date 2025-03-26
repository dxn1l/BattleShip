package org.example.grafo;

import java.util.*;

public class GrafoPuertos {
    private Map<Puerto, Map<Puerto, Integer>> adyacencia;

    public GrafoPuertos() {
        adyacencia = new HashMap<>();
    }

    public void agregarPuerto(String nombre) {
        adyacencia.putIfAbsent(new Puerto(nombre), new HashMap<>());
    }

    public void conectarPuertos(String nombre1, String nombre2, int distancia) {
        Puerto p1 = new Puerto(nombre1);
        Puerto p2 = new Puerto(nombre2);
        adyacencia.putIfAbsent(p1, new HashMap<>());
        adyacencia.putIfAbsent(p2, new HashMap<>());

        adyacencia.get(p1).put(p2, distancia);
        adyacencia.get(p2).put(p1, distancia);
    }

    public void dfs(String nombreInicio) {
        Puerto inicio = new Puerto(nombreInicio);
        Set<Puerto> visitados = new HashSet<>();
        System.out.println("Barrido en profundidad desde " + nombreInicio + ":");
        dfsRecursivo(inicio, visitados);
    }

    private void dfsRecursivo(Puerto actual, Set<Puerto> visitados) {
        if (!adyacencia.containsKey(actual) || visitados.contains(actual)) return;
        visitados.add(actual);
        System.out.println("- " + actual.getNombre());
        for (Puerto vecino : adyacencia.get(actual).keySet()) {
            dfsRecursivo(vecino, visitados);
        }
    }

    public void caminoMasCorto(String origen, String destino) {
        Map<Puerto, Integer> distancias = new HashMap<>();
        Map<Puerto, Puerto> anterior = new HashMap<>();
        Set<Puerto> visitados = new HashSet<>();
        PriorityQueue<Puerto> cola = new PriorityQueue<>(Comparator.comparingInt(distancias::get));

        Puerto inicio = new Puerto(origen);
        Puerto fin = new Puerto(destino);

        for (Puerto p : adyacencia.keySet()) {
            distancias.put(p, Integer.MAX_VALUE);
        }

        distancias.put(inicio, 0);
        cola.add(inicio);

        while (!cola.isEmpty()) {
            Puerto actual = cola.poll();
            if (!visitados.add(actual)) continue;

            for (Map.Entry<Puerto, Integer> vecino : adyacencia.get(actual).entrySet()) {
                int nuevaDist = distancias.get(actual) + vecino.getValue();
                if (nuevaDist < distancias.get(vecino.getKey())) {
                    distancias.put(vecino.getKey(), nuevaDist);
                    anterior.put(vecino.getKey(), actual);
                    cola.add(vecino.getKey());
                }
            }
        }

        if (!distancias.containsKey(fin) || distancias.get(fin) == Integer.MAX_VALUE) {
            System.out.println("No existe camino entre los puertos.");
            return;
        }

        // Reconstruir camino
        List<Puerto> camino = new ArrayList<>();
        for (Puerto at = fin; at != null; at = anterior.get(at)) {
            camino.add(at);
        }
        Collections.reverse(camino);

        System.out.println("Camino más corto de " + origen + " a " + destino + ":");
        for (Puerto p : camino) {
            System.out.print(p.getNombre() + " -> ");
        }
        System.out.println("FIN (Distancia total: " + distancias.get(fin) + ")");
    }

    public void eliminarPuertoConMasAristas() {
        Puerto maxPuerto = null;
        int maxConexiones = -1;

        for (Map.Entry<Puerto, Map<Puerto, Integer>> entry : adyacencia.entrySet()) {
            if (entry.getValue().size() > maxConexiones) {
                maxConexiones = entry.getValue().size();
                maxPuerto = entry.getKey();
            }
        }

        if (maxPuerto != null) {
            adyacencia.remove(maxPuerto);
            for (Map<Puerto, Integer> vecinos : adyacencia.values()) {
                vecinos.remove(maxPuerto);
            }
            System.out.println("Puerto eliminado: " + maxPuerto.getNombre() + " (con " + maxConexiones + " conexiones)");
        }
    }
}
