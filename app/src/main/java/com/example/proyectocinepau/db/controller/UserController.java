package com.example.proyectocinepau.db.controller;

import android.content.Context;

import com.example.proyectocinepau.db.DataBase;
import com.example.proyectocinepau.model.Pelicula;
import com.example.proyectocinepau.model.User;

import io.realm.Realm;

public class UserController {

    private Context context;
    private Realm con;

    public UserController(Context context) {
        this.context = context;
        this.con = DataBase.getInstance().connection(context);
    }

    public User getUser(String dni){
        return con.where(User.class).equalTo("DNI",dni).findFirst();
    }
}
