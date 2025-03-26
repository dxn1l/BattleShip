package org.example.barco;

public interface BarcoBuilder {
    void setNombre(String nombre);
    void setNumero(int numero);
    void setNivel(int nivel);
    void setTamanio();
    Barco build();
}

