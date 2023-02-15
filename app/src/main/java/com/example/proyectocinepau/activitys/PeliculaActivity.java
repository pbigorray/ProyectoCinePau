package com.example.proyectocinepau.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.proyectocinepau.R;
import com.example.proyectocinepau.db.DataBase;
import com.example.proyectocinepau.db.controller.PeliculaController;
import com.example.proyectocinepau.db.controller.SalaController;
import com.example.proyectocinepau.db.controller.SesionController;
import com.example.proyectocinepau.model.Pelicula;
import com.example.proyectocinepau.model.Sala;
import com.example.proyectocinepau.model.Sesion;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

public class PeliculaActivity extends AppCompatActivity {

    private TextView titulo,descripcion,genero,duracion,edad;
    private ImageView imagen;
    private PeliculaController peliculaController;
    private SesionController sesionController;
    private LinearLayout sesionContainer;
    private Realm con;
    private SalaController salaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula);
        titulo=findViewById(R.id.tituloDef);
        descripcion=findViewById(R.id.descripcionDef);
        genero=findViewById(R.id.generoDef);
        duracion=findViewById(R.id.duracionDef);
        edad=findViewById(R.id.edadDef);
        imagen=findViewById(R.id.imagenDef);
        sesionContainer=findViewById(R.id.sesionContainer);

        peliculaController=new PeliculaController(getApplicationContext());
        Bundle data=getIntent().getExtras();

        Pelicula p =peliculaController.getPelicula(data.getInt("position"));

        Picasso.get().load(p.getUrlImagen()).into(imagen);
        titulo.setText(p.getTitulo());
        descripcion.setText(p.getDescripcion());
        genero.setText(p.getGenero());
        duracion.setText(p.getDuracion());
        edad.setText(p.getEdad());

        salaController= new SalaController(getApplicationContext());
        sesionController=new SesionController(getApplicationContext(),p);

        sesionController.getSesiones();

        for (int i = 0; i < sesionController.getSizeSesiones(); i++) {
            Button boton = new Button(this);
            boton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            boton.setText(""+sesionController.getSesion(i).getHora());
            boton.setId(i);
            Intent intent = new Intent(getApplicationContext(), SesionActivity.class);
            intent.putExtra("hora", sesionController.getSesion(i).getHora());
            intent.putExtra("pelicula", sesionController.getSesion(i).getNombrePelicula());
            boton.setOnClickListener(view -> {
                startActivity(intent);
                finish();
            });
            sesionContainer.addView(boton);
        }
    }
}