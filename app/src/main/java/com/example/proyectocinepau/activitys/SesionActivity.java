package com.example.proyectocinepau.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectocinepau.R;
import com.example.proyectocinepau.db.controller.PeliculaController;
import com.example.proyectocinepau.db.controller.SesionController;
import com.example.proyectocinepau.model.Sesion;

public class SesionActivity extends AppCompatActivity {
    private TextView horaView,peliculaView;
    private SesionController sesionController;
    private PeliculaController peliculaController;
    private Sesion sesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);
        horaView =findViewById(R.id.horaSesion);
        peliculaView=findViewById(R.id.pelicula);


        Bundle data = getIntent().getExtras();
        Toast.makeText(this, data.getString("hora"), Toast.LENGTH_SHORT).show();
        horaView.setText(""+data.getString("hora"));
        peliculaView.setText(""+data.getString("pelicula"));
        peliculaController=new PeliculaController(getApplicationContext());

        sesionController=new SesionController(getApplicationContext(),peliculaController.getPelicula(data.getString("pelicula")));

        sesion =sesionController.getSesion(data.getString("hora"),data.getString("pelicula"));

        TableLayout tableLayout=findViewById(R.id.table);
        tableLayout.setGravity(Gravity.CENTER);
        int count=0;
        for (int i =0; i<sesionController.getFilas(sesion.getNumSala());i++){
            TableRow tableRow= new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            tableRow.setGravity(Gravity.CENTER);
            for (int j = 0; j < sesionController.getColumnas(sesion.getNumSala()); j++) {
//                @SuppressLint("RestrictedApi") CheckableImageButton boton = new CheckableImageButton(this);
                CheckBox boton = new CheckBox(this);
                boton.setButtonDrawable(R.drawable.butaca_selector);
                boton.setGravity(Gravity.CENTER);
                boton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                boton.setId(++count);
                tableRow.addView(boton);
            }
            Toast.makeText(this, "Count: "+count, Toast.LENGTH_SHORT).show();
            tableLayout.addView(tableRow);
        }
    }
}