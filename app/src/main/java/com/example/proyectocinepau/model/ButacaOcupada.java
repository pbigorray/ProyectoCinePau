package com.example.proyectocinepau.model;

import java.io.Serializable;

public class ButacaOcupada implements Serializable {
    private int x,y,idSesion;

    public ButacaOcupada(int x, int y, int idSesion) {
        this.x = x;
        this.y = y;
        this.idSesion = idSesion;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIdSesion() {
        return idSesion;
    }
}
