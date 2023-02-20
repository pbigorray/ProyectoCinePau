package com.example.proyectocinepau.db.controller;

import android.content.Context;

import com.example.proyectocinepau.db.DataBase;
import com.example.proyectocinepau.model.Sala;
import com.example.proyectocinepau.model.tipos.TipoSala;

import java.util.List;

import io.realm.Realm;

public class SalaController {
    private Context context;
    private Realm con;

    public SalaController(Context context) {
        this.context = context;
        con= DataBase.getInstance().connection(context);
        long salas = con.where(Sala.class).count();
        if (salas == 0) {
            addDefault();
        }
    }



    public List<Sala> getSalas(){
        return con.where(Sala.class).findAll();
    }
    public int getSize(){
        return (int) con.where(Sala.class).count();
    }
    public int getSizeTipo(){
        return (int) con.where(Sala.class).equalTo("tipo", "").count();
    }
    public Sala getSala(int i){
        return con.where(Sala.class).equalTo("numSala",i).findFirst();
    }
    public void addSala(int sala, int filas, int columnas,String tipo) {
        Sala s = new Sala();

        s.setNumSala(sala);
        s.setFilas(filas);
        s.setColumnas(columnas);
        s.setTipo(tipo);

        con.beginTransaction();
        con.copyToRealmOrUpdate(s);
        con.commitTransaction();
    }

    public void addDefault() {
        addSala(1,9,9, TipoSala.NORMAL.getSala());
        addSala(2,9,9, TipoSala.S_3D.getSala());
        addSala(3,12,12, TipoSala.S_4DX.getSala());
        addSala(4,12,12, TipoSala.S_3D.getSala());
        addSala(5,15,15, TipoSala.NORMAL.getSala());
        addSala(6,15,15, TipoSala.S_4DX.getSala());
    }
}
