package com.example.proyectocinepau.db;

import android.content.Context;

import com.example.proyectocinepau.model.Pelicula;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DataBase {

    private static DataBase instance=new DataBase();
    public static DataBase getInstance(){
        return instance;
    }


    private Realm con;
    public Realm connection(Context context){
        if(con==null){
            Realm.init(context);
            String nombre="cineDB";
            RealmConfiguration configuration= new RealmConfiguration
                    .Builder()
                    .schemaVersion(1)
                    .allowWritesOnUiThread(true)
                    .deleteRealmIfMigrationNeeded()
                    .name(nombre)
                    .build();
            con= Realm.getInstance(configuration);

        }
        return con;
    }
}
