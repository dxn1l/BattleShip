package org.example.tablero;

public class Tablero {
    private final String[][] casillas;

    public Tablero(int filas, int columnas) {
        casillas = new String[filas][columnas];
        inicializarCasillas();
    }

    private void inicializarCasillas() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[0].length; j++) {
                casillas[i][j] = "~"; // agua
            }
        }
    }

    public void colocarBarco(int x, int y, int tamanio, String direccion) {
        // Simplificación: solo horizontal o vertical
        for (int i = 0; i < tamanio; i++) {
            if (direccion.equals("H") && y + i < casillas[0].length) {
                casillas[x][y + i] = "B";
            } else if (direccion.equals("V") && x + i < casillas.length) {
                casillas[x + i][y] = "B";
            }
        }
    }

    public boolean recibirAtaque(int x, int y) {
        if (casillas[x][y].equals("B")) {
            casillas[x][y] = "X"; // tocado
            return true;
        } else {
            casillas[x][y] = "O"; // agua
            return false;
        }
    }

    public void mostrarTablero() {
        for (String[] fila : casillas) {
            for (String celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }
}

