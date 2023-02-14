package com.example.proyectocinepau.model.tipos;

public enum Rol {
    USER(0),EMPLEADO(1),ADMIN(2);

    private int num;

    Rol(int i) {
        this.num=i;
    }

    public int getNum() {
        return num;
    }
}
