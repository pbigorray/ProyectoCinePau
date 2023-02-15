package com.example.proyectocinepau.model.tipos;

public enum TipoSala {
    NORMAL("Normal"),S_3D("3D"),S_4DX("4DX");

    private String sala;

    TipoSala(String sala) {
        this.sala = sala;
    }

    public String getSala() {
        return sala;
    }
}
