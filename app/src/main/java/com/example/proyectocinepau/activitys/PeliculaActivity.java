package com.example.proyectocinepau.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectocinepau.R;
import com.example.proyectocinepau.activitys.add.AddPeliculaActivity;
import com.example.proyectocinepau.db.DataBase;
import com.example.proyectocinepau.db.controller.PeliculaController;
import com.example.proyectocinepau.db.controller.SalaController;
import com.example.proyectocinepau.db.controller.SesionController;
import com.example.proyectocinepau.model.Pelicula;
import com.example.proyectocinepau.model.Sala;
import com.example.proyectocinepau.model.Sesion;
import com.example.proyectocinepau.model.User;
import com.example.proyectocinepau.model.tipos.Rol;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

public class PeliculaActivity extends AppCompatActivity {

    private TextView titulo, descripcion, genero, duracion, edad;
    private ImageView imagen;
    private PeliculaController peliculaController;
    private SesionController sesionController;
    private LinearLayout sesionContainer;
    private Realm con;
    private SalaController salaController;
    private CheckBox verCartelera;
    private Button atrasPelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula);
        titulo = findViewById(R.id.tituloDef);
        descripcion = findViewById(R.id.descripcionDef);
        genero = findViewById(R.id.generoDef);
        duracion = findViewById(R.id.duracionDef);
        edad = findViewById(R.id.edadDef);
        imagen = findViewById(R.id.imagenDef);
        sesionContainer = findViewById(R.id.sesionContainer);
        verCartelera = findViewById(R.id.verCartelera);
        con = DataBase.getInstance().connection(this);
        atrasPelicula = findViewById(R.id.atrasPelicula);

        peliculaController = new PeliculaController(getApplicationContext());
        Bundle data = getIntent().getExtras();
        Pelicula pelicula;

        if(data.getString("pelicula")!=null){
            pelicula= peliculaController.getPelicula(data.getString("pelicula"));
        }else if(data.getBoolean("onCartelera",true)){
            pelicula = peliculaController.getOnPelicula(data.getInt("position"));
        }else {
            pelicula = peliculaController.getPelicula(data.getInt("position"));
        }

        User userLog = con.where(User.class).equalTo("DNI", data.getString("dni")).findFirst();
        if (userLog != null) {
            if (Rol.ADMIN.getNum() == userLog.getRol()) {
                verCartelera.setVisibility(View.VISIBLE);
                verCartelera.setChecked(pelicula.isCartelera());
            }
        }
        verCartelera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Pelicula p = new Pelicula();

                p.setCartelera(b);
                p.setDescripcion(pelicula.getDescripcion());
                p.setEdad(pelicula.getEdad());
                p.setGenero(pelicula.getGenero());
                p.setDuracion(pelicula.getDuracion());
                p.setUrlImagen(pelicula.getUrlImagen());
                p.setTitulo(pelicula.getTitulo());
                peliculaController.updatePelicula(p);
                Toast.makeText(PeliculaActivity.this, "Se ha actualizado", Toast.LENGTH_SHORT).show();
            }
        });
        try {
            Picasso.get().load(pelicula.getUrlImagen()).into(imagen);
        } catch (Exception e) {
            Picasso.get().load("https://farm5.staticflickr.com/4363/36346283311_74018f6e7d_o.png").into(imagen);
        }
            titulo.setText("Titulo:" + pelicula.getTitulo());
            descripcion.setText("Descripcion:\n" + pelicula.getDescripcion());
            genero.setText("Genero: " + pelicula.getGenero());
            duracion.setText("Durecion: " + pelicula.getDuracion() + " minutos");
            edad.setText("Edad recomendad: " + pelicula.getEdad());

            salaController = new SalaController(getApplicationContext());
            sesionController = new SesionController(getApplicationContext(), pelicula);
            sesionController.getSesiones();
            sesionContainer.setGravity(Gravity.CENTER);

        for (int i = 0; i < sesionController.getSizeSesiones(); i++) {
            Button boton = new Button(this);
            boton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            boton.setText(""+sesionController.getSesion(i).getHora());
            boton.setId(i);
            Intent intent = new Intent(getApplicationContext(), SesionActivity.class);
            intent.putExtra("hora", sesionController.getSesion(i).getHora());
            intent.putExtra("dni", data.getString("dni"));
            intent.putExtra("pelicula", sesionController.getSesion(i).getNombrePelicula());
            intent.putExtra("onCartelera",data.getBoolean("onCartelera"));
            boton.setOnClickListener(view -> {
                startActivity(intent);
            });
            sesionContainer.addView(boton);
        }

        atrasPelicula.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CarteleraActivity.class);
            intent.putExtra("dni", data.getString("dni"));
            intent.putExtra("onCartelera",data.getBoolean("onCartelera"));
            startActivity(intent);
        });
    }
}