package com.example.proyectocinepau.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Sala extends RealmObject {
    @PrimaryKey
    private int numSala;

    private int filas,columnas;
    private String tipo;

    public int getNumSala() {
        return numSala;
    }

    public void setNumSala(int numSala) {
        this.numSala = numSala;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
