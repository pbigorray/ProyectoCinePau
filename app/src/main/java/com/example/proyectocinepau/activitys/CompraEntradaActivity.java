package com.example.proyectocinepau.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectocinepau.R;
import com.example.proyectocinepau.db.DataBase;
import com.example.proyectocinepau.db.controller.PeliculaController;
import com.example.proyectocinepau.db.controller.SesionController;
import com.example.proyectocinepau.db.controller.UserController;
import com.example.proyectocinepau.db.controller.VentaController;
import com.example.proyectocinepau.model.ButacaOcupada;
import com.example.proyectocinepau.model.ButacasCompradas;
import com.example.proyectocinepau.model.Entrada;
import com.example.proyectocinepau.model.Pelicula;
import com.example.proyectocinepau.model.Sala;
import com.example.proyectocinepau.model.Sesion;
import com.example.proyectocinepau.model.User;
import com.example.proyectocinepau.model.Venta;
import com.example.proyectocinepau.model.recycler.EntradasAdapter;
import com.example.proyectocinepau.model.tipos.TipoSala;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class CompraEntradaActivity extends AppCompatActivity {
    private Sesion sesion;
    private ButacasCompradas but;
    private String dni;
    private int total;
    private SesionController sesionController;
    private UserController userController;
    private VentaController ventaController;
    private RecyclerView recyclerView;
    private Button volver,comprar;
    private TextView precioTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_entrada);
        recyclerView=findViewById(R.id.recylerEntrada);
        volver=findViewById(R.id.volverEntrada);
        comprar=findViewById(R.id.comprarEntrada);
        precioTotal=findViewById(R.id.totalPrecio);


        Bundle data = getIntent().getExtras();

        but=(ButacasCompradas) data.get("butacas");
        dni= data.getString("dni");
        PeliculaController peliculaController = new PeliculaController(this);

        Pelicula pelicula=peliculaController.getPelicula(data.getString("pelicula"));
        sesionController= new SesionController(this,pelicula);
//        Pelicula pelicula= (Pelicula)data.get("pelicula");
        sesion=sesionController.getSesionById(data.getInt("sesion"));
//        sesion=sesionController.getSesion(data.getString("hora"));
        userController=new UserController(this);
        ventaController= new VentaController(this);
        User user = userController.getUser(dni);

        Sala sala= sesionController.getSala(sesion.getNumSala());
        total= 0;

        EntradasAdapter entradasAdapter = new EntradasAdapter(this, but.getButacas(),sesionController,sala,sesion);

        recyclerView.setAdapter(entradasAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        for(ButacaOcupada a : but.getButacas()){
            if(Objects.equals(sala.getTipo(), TipoSala.NORMAL.getSala())){
                total += 7;
            }else if(Objects.equals(sala.getTipo(), TipoSala.S_3D.getSala())){
                total += 9;
            }else if(Objects.equals(sala.getTipo(), TipoSala.S_4DX.getSala())){
                total += 11;
            }
        }

        precioTotal.setText("Usuario: " + user.getUsuario() + " TOTAL COMPRA: " + total+"€");

        comprar.setOnClickListener(view -> {
                Venta venta = new Venta();

                Date c = Calendar.getInstance().getTime();

                venta.setHora(c.toString());
                venta.setUsuario(user.getUsuario());
                venta.setPrecio(total);
                venta.setIdVenta(ventaController.getID());
                venta.setNumSala(sesion.getNumSala());

                for(ButacaOcupada butaca : but.getButacas()){
                    Entrada entrada = new Entrada();
                    entrada.setFila(butaca.getX());
                    entrada.setColumna(butaca.getY());
                    entrada.setIdVenta(venta.getIdVenta());
                    entrada.setIdSesion(sesion.getIdSesion());
                    entrada.setId(ventaController.getIdEntrada());

                    ventaController.addEntrada(entrada);
                }
                ventaController.addVenta(venta);
                Toast.makeText(this, "Compra realizada con exito", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, CarteleraActivity.class);
                intent.putExtra("dni", user.getDNI());
                intent.putExtra("onCartelera",data.getBoolean("onCartelera"));
                startActivity(intent);
        });

        volver.setOnClickListener(view -> {
                Intent intent = new Intent(this, SesionActivity.class);
                intent.putExtra("dni", user.getDNI());
                intent.putExtra("sesion", sesion.getIdSesion());
                intent.putExtra("hora", sesion.getHora());
                intent.putExtra("pelicula", sesion.getNombrePelicula());
                intent.putExtra("onCartelera",data.getBoolean("onCartelera"));
                startActivity(intent);
                finish();
            }
        );


    }
}