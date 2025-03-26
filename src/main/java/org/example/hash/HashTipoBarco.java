package org.example.hash;

import org.example.barco.Barco;

import java.util.*;

public class HashTipoBarco {
    private final Map<String, List<Barco>> tablaTipo;
    private final Barco[] tablaNumero;
    private final Barco[] tablaNombre;
    private final int SIZE = 15;

    public HashTipoBarco() {
        tablaTipo = new HashMap<>();
        tablaNumero = new Barco[SIZE];
        tablaNombre = new Barco[SIZE];
    }

    // Tabla por TIPO
    public void insertarPorTipo(String tipo, Barco barco) {
        tablaTipo.putIfAbsent(tipo, new ArrayList<>());
        tablaTipo.get(tipo).add(barco);
    }

    public List<Barco> buscarPorTipo(String tipo) {
        return tablaTipo.getOrDefault(tipo, new ArrayList<>());
    }

    // Tabla por NÚMERO
    public void insertarPorNumero(Barco barco) {
        int index = barco.getNumero() % SIZE;
        int original = index;
        while (tablaNumero[index] != null) {
            index = (index + 1) % SIZE;
            if (index == original) break; // Tabla llena
        }
        tablaNumero[index] = barco;
    }

    public Barco buscarPorNumero(int numero) {
        int index = numero % SIZE;
        int original = index;
        while (tablaNumero[index] != null) {
            if (tablaNumero[index].getNumero() == numero)
                return tablaNumero[index];
            index = (index + 1) % SIZE;
            if (index == original) break;
        }
        return null;
    }

    // Tabla por NOMBRE
    public void insertarPorNombre(Barco barco) {
        int index = Math.abs(barco.getNombre().hashCode()) % SIZE;
        int original = index;
        while (tablaNombre[index] != null) {
            index = (index + 1) % SIZE;
            if (index == original) break;
        }
        tablaNombre[index] = barco;
    }

    public Barco buscarPorNombre(String nombre) {
        int index = Math.abs(nombre.hashCode()) % SIZE;
        int original = index;
        while (tablaNombre[index] != null) {
            if (tablaNombre[index].getNombre().equals(nombre))
                return tablaNombre[index];
            index = (index + 1) % SIZE;
            if (index == original) break;
        }
        return null;
    }

    // Método para insertar en todas las tablas a la vez
    public void insertarCompleto(String tipo, Barco barco) {
        insertarPorTipo(tipo, barco);
        insertarPorNumero(barco);
        insertarPorNombre(barco);
    }
}

