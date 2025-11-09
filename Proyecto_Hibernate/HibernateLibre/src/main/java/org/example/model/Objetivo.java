package org.example.model;

public enum Objetivo {
    PERDER_PESO("Perder peso"),
    GANAR_MASA("Ganar masa muscular"),
    MANTENER("Mantener condición");

    private final String descripcion;

    Objetivo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
