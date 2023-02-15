package com.example.proyectocinepau.model.tipos;

public enum Genero {
    DRAMA("Drama"),COMEDIA("Comedia"),TRILLER("Triller"),AVENTURAS("Aventuras");

    private String genero;
    Genero(String genero) {
        this.genero=genero;
    }

    public String getGenero() {
        return genero;
    }
}
