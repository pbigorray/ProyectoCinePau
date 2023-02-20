package com.example.proyectocinepau.db.controller;

import android.content.Context;

import com.example.proyectocinepau.db.DataBase;
import com.example.proyectocinepau.model.Entrada;
import com.example.proyectocinepau.model.Venta;

import io.realm.Realm;

public class VentaController {
    private Context context;
    private Realm con;

    public VentaController(Context context) {
        this.context = context;
        this.con = DataBase.getInstance().connection(context);
    }
    public int getID(){
        return (int)con.where(Venta.class).count();
    }
    public int getIdEntrada(){
        return (int) con.where(Entrada.class).count();
    }
    public void addEntrada(Entrada e){
        con.beginTransaction();
        con.copyToRealmOrUpdate(e);
        con.commitTransaction();
    }
    public void addVenta(Venta v){
        con.beginTransaction();
        con.copyToRealmOrUpdate(v);
        con.commitTransaction();
    }
}
