package com.example.proyectocinepau.activitys.add;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectocinepau.R;
import com.example.proyectocinepau.activitys.CarteleraActivity;
import com.example.proyectocinepau.db.controller.PeliculaController;
import com.example.proyectocinepau.model.tipos.Edad;
import com.example.proyectocinepau.model.tipos.Genero;

public class AddPeliculaActivity extends AppCompatActivity {
    private EditText addTitulo,addDesc,addImagen,addDuracion;
    private Spinner edadSpinner,generoSpinner;
    private CheckBox addCartelera;
    private Button addPelicula,volver;
    private PeliculaController peliculaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pelicula);
        addTitulo=findViewById(R.id.addTitulo);
        addDesc=findViewById(R.id.addDescripcion);
        addImagen=findViewById(R.id.addImagen);
        addDuracion=findViewById(R.id.addDuracion);
        edadSpinner=findViewById(R.id.edadSpinner);
        generoSpinner=findViewById(R.id.generoSpinner);
        addCartelera=findViewById(R.id.addCartelera);
        addPelicula=findViewById(R.id.addPelicula);
        volver=findViewById(R.id.volverAdd);

        peliculaController=new PeliculaController(getApplicationContext());

        Bundle data=getIntent().getExtras();

        ArrayAdapter<Genero> generos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Genero.values());
        ArrayAdapter<Edad> edades = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Edad.values());

        edadSpinner.setAdapter(edades);
        generoSpinner.setAdapter(generos);

        addPelicula.setOnClickListener(view -> {
            String titulo,desc,imagen,duracion;
            Genero genero;
            Edad edad;
            boolean cartelera;


            titulo=addTitulo.getText().toString();
            desc=addDesc.getText().toString();
            imagen=addImagen.getText().toString();
            duracion=addDuracion.getText().toString();
            edad=(Edad) edadSpinner.getSelectedItem();
            genero=(Genero) generoSpinner.getSelectedItem();
            cartelera=addCartelera.isChecked();

            if (!addTitulo.getText().toString().equals("")) {
                Toast.makeText(this, "El titulo no puede estar vacio", Toast.LENGTH_SHORT).show();
            }else{
                titulo=addTitulo.getText().toString();
                peliculaController.addPeliculas(titulo,duracion,desc,genero.getGenero(),edad.getEdad(),cartelera,imagen);
            }

            Intent intent = new Intent(getApplicationContext(), CarteleraActivity.class);
            intent.putExtra("dni",data.getString("dni"));
            startActivity(intent);
            finish();
        });

        volver.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CarteleraActivity.class);
            intent.putExtra("dni",data.getString("dni"));
            startActivity(intent);
            finish();
        });

    }
}