package com.example.proyectocinepau.db.controller;

import android.content.Context;

import com.example.proyectocinepau.db.DataBase;
import com.example.proyectocinepau.model.User;
import com.example.proyectocinepau.model.tipos.Rol;

import io.realm.Realm;

public class UserController {

    private Context context;
    private Realm con;

    public UserController(Context context) {
        this.context = context;
        this.con = DataBase.getInstance().connection(context);

        long users= con.where(User.class).count();
        if (users==0){
            addAdminUserDefault();
        }
    }

    public User getUser(String dni){
        return con.where(User.class).equalTo("DNI",dni).findFirst();
    }

    public void addUser(User user){
        con.beginTransaction();
        con.copyToRealmOrUpdate(user);
        con.commitTransaction();
    }
    public User findUser(String user){
        return con.where(User.class).equalTo("usuario",user).findFirst();
    }
    public void addAdminUserDefault(){
        User user= new User();
        user.setDNI("admin");
        user.setNombre("admin");
        user.setUsuario("admin");
        user.setPass("admin");
        user.setRol(Rol.ADMIN.getNum());
        addUser(user);
    }public void addAdminUser(){
        User user= new User();
        user.setDNI("admin");
        user.setNombre("admin");
        user.setUsuario("admin");
        user.setPass("admin");
        user.setRol(Rol.ADMIN.getNum());
        addUser(user);
    }

}
