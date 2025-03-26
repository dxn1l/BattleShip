package org.example.grafo;

public class Puerto {
    private String nombre;

    public Puerto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Puerto otro) {
            return this.nombre.equals(otro.nombre);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
}

