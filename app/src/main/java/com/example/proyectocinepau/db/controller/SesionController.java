package com.example.proyectocinepau.db.controller;

import android.content.Context;

import com.example.proyectocinepau.db.DataBase;
import com.example.proyectocinepau.model.Pelicula;
import com.example.proyectocinepau.model.Sala;
import com.example.proyectocinepau.model.Sesion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;

public class SesionController {
    private Context context;
    private Realm con;
    private String titulo;
    private Pelicula pelicula;
    private PeliculaController peliculaController;
    private static int idSesion;


    public SesionController(Context context, Pelicula pelicula) {
        this.context = context;
        this.con = DataBase.getInstance().connection(context);
        this.titulo =pelicula.getTitulo();
        this.pelicula=pelicula;
        this.peliculaController=new PeliculaController(context);
        long sesiones = con.where(Sesion.class).count();
        if (sesiones == 0) {
            addAll();
        }
    }
    public void addAll(){
        List<Pelicula> pelis= peliculaController.getCartelera();
        for (int i=0;i<pelis.size();i++){
            addDefault(pelis.get(i));
        }

    }
    public void addDefault(Pelicula pelicula) {
        Date sesion;
        String horaIni="12:00";
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            sesion=format.parse(horaIni);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        addSesion(1, pelicula.getTitulo(),""+format.format(sesion));
        sesion=addHora(sesion,Integer.parseInt(pelicula.getDuracion())/60);
        addSesion(2, pelicula.getTitulo(),""+format.format(sesion));
        sesion=addHora(sesion,Integer.parseInt(pelicula.getDuracion())/60);
        addSesion(3, pelicula.getTitulo(),""+format.format(sesion));
        sesion=addHora(sesion,Integer.parseInt(pelicula.getDuracion())/60);
        addSesion(4, pelicula.getTitulo(),""+format.format(sesion));
        sesion=addHora(sesion,Integer.parseInt(pelicula.getDuracion())/60);
        addSesion(5, pelicula.getTitulo(),""+format.format(sesion));
        sesion=addHora(sesion,Integer.parseInt(pelicula.getDuracion())/60);
        addSesion(6, pelicula.getTitulo(),""+format.format(sesion));

    }

    private Date addHora(Date sesion, int tiempoSesion) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sesion);
        calendar.add(Calendar.HOUR, tiempoSesion);

        return calendar.getTime();
    }

    public List<Sesion> getSesiones(){
        return con.where(Sesion.class).equalTo("nombrePelicula", titulo).findAll();
    }
    public int getSize(){
        return (int) con.where(Sesion.class).count();
    }
    public int getSizeSesiones(){
        return (int) con.where(Sesion.class).equalTo("nombrePelicula", titulo).count();
    }
    public Sesion getSesion(int i){
        return getSesiones().get(i);
    }
    public Sesion getSesion(String hora,String titulo){
        return con.where(Sesion.class).equalTo("hora",hora).equalTo("nombrePelicula",titulo).findFirst();
    }
    public void addSesion(int sala, String pelicula, String hora) {
        Sesion s = new Sesion();

        s.setIdSesion(++idSesion);
        s.setNumSala(sala);
        s.setNombrePelicula(pelicula);
        s.setHora(hora);

        con.beginTransaction();
        con.copyToRealmOrUpdate(s);
        con.commitTransaction();
    }
    public int getColumnas(int numSala){
        return con.where(Sala.class).equalTo("numSala",numSala).findFirst().getColumnas();
    }
    public int getFilas(int numSala){
        return con.where(Sala.class).equalTo("numSala",numSala).findFirst().getFilas();
    }
}
