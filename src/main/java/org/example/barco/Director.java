package org.example.barco;

public class Director {
    private BarcoBuilder builder;

    public void setBuilder(BarcoBuilder builder) {
        this.builder = builder;
    }

    public Barco construct(String nombre, int numero, int nivel) {
        builder.setNombre(nombre);
        builder.setNumero(numero);
        builder.setNivel(nivel);
        builder.setTamanio();
        return builder.build();
    }
}

