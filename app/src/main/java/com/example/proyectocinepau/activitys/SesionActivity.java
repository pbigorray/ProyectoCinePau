package com.example.proyectocinepau.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectocinepau.R;
import com.example.proyectocinepau.db.controller.PeliculaController;
import com.example.proyectocinepau.db.controller.SesionController;
import com.example.proyectocinepau.model.ButacaOcupada;
import com.example.proyectocinepau.model.ButacasCompradas;
import com.example.proyectocinepau.model.Sesion;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class SesionActivity extends AppCompatActivity implements Serializable {
    private TextView horaView,peliculaView;
    private SesionController sesionController;
    private PeliculaController peliculaController;
    private Sesion sesion;
    private Button volver,comprar;
    private List<ButacaOcupada> butacaOcupadas,seatOcupados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);
        horaView =findViewById(R.id.horaSesion);
        peliculaView=findViewById(R.id.pelicula);
        volver=findViewById(R.id.volverSesion);
        comprar=findViewById(R.id.comprarEntradas);
        butacaOcupadas= new LinkedList<>();

        Bundle data = getIntent().getExtras();
        horaView.setText(""+data.getString("hora"));
        peliculaView.setText(""+data.getString("pelicula"));
        peliculaController=new PeliculaController(getApplicationContext());

        sesionController=new SesionController(getApplicationContext(),peliculaController.getPelicula(data.getString("pelicula")));

        sesion =sesionController.getSesion(data.getString("hora"),data.getString("pelicula"));

        seatOcupados =sesionController.getAllButacasOcupadasDeSala(sesion);
        TableLayout tableLayout=findViewById(R.id.table);
        tableLayout.setGravity(Gravity.CENTER);
        int count=0;
        for (int i =0; i<sesionController.getFilas(sesion.getNumSala());i++){
            TableRow tableRow= new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            tableRow.setGravity(Gravity.CENTER);
            for (int j = 0; j < sesionController.getColumnas(sesion.getNumSala()); j++) {
                CheckBox boton = new CheckBox(this);
                boton.setButtonDrawable(R.drawable.butaca_selector);
                boton.setGravity(Gravity.CENTER);
                boton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                boton.setId(++count);
                for(ButacaOcupada ao : seatOcupados){
                    if(ao.getX() == j+1 && ao.getY() == i+1){
                        boton.setChecked(false);
                        boton.setEnabled(false);
                        boton.setButtonDrawable(R.drawable.butaca_vendida_24);
                    }
                }
                boton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        int col = 1;
                        int fil = compoundButton.getId();

                        for (int k = 2, g = sesionController.getColumnas(sesion.getNumSala()); k <= sesionController.getFilas(sesion.getNumSala()); k++, g+=sesionController.getColumnas(sesion.getNumSala())) {
                            if (fil >= 1+g && fil <= sesionController.getColumnas(sesion.getNumSala())+g){
                                fil -= g;
                                col = k;
                            }
                        }

                        ButacaOcupada butaca = new ButacaOcupada(fil, col, sesion.getNumSala());
                        if(b){
                            butacaOcupadas.add(butaca);
                        }else{
                            butacaOcupadas.remove(butaca);
                        }
                    }
                });
                tableRow.addView(boton);
            }
            tableLayout.addView(tableRow);
        }
        volver.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PeliculaActivity.class);
            intent.putExtra("dni",data.getString("dni"));
            intent.putExtra("pelicula",data.getString("pelicula"));
            intent.putExtra("onCartelera",data.getBoolean("onCartelera"));
            startActivity(intent);
            finish();
        });
        comprar.setOnClickListener(view -> {
                if(butacaOcupadas.size() != 0){
                    Intent intent = new Intent(this, CompraEntradaActivity.class);
                    intent.putExtra("dni", data.getString("dni"));
                    ButacasCompradas but=new ButacasCompradas(butacaOcupadas);
                    intent.putExtra("butacas", but);
                    intent.putExtra("pelicula", sesionController.getPelicula());
                    intent.putExtra("onCartelera",data.getBoolean("onCartelera"));
                    intent.putExtra("sesion", sesion.getIdSesion());
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(this, "Elige alguna butaca", Toast.LENGTH_SHORT).show();
                }

        });
    }
}