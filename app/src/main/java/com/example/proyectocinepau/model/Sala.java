package com.example.proyectocinepau.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Sala extends RealmObject {
    @PrimaryKey
    private int numSala;

    private int fila,columnas,tipo;

    public int getNumSala() {
        return numSala;
    }

    public void setNumSala(int numSala) {
        this.numSala = numSala;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
