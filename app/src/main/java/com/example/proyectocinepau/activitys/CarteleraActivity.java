package com.example.proyectocinepau.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.proyectocinepau.R;
import com.example.proyectocinepau.activitys.add.AddPeliculaActivity;
import com.example.proyectocinepau.db.DataBase;
import com.example.proyectocinepau.db.controller.PeliculaController;
import com.example.proyectocinepau.model.Pelicula;
import com.example.proyectocinepau.model.User;
import com.example.proyectocinepau.model.recycler.MyRecyclerViewAdapter;
import com.example.proyectocinepau.model.tipos.Rol;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.realm.Realm;

public class CarteleraActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private PeliculaController peliculaController;
    private FloatingActionButton addPeliculas,addSala,addSesion,menuAdd;
    private Realm con;
    private boolean isFABOpen;
    private List<Pelicula> listaPelicula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartelera);
        recycler=findViewById(R.id.recycle);
        addPeliculas =findViewById(R.id.addPeliculas);
        addSala=findViewById(R.id.addSala);
        addSesion=findViewById(R.id.addSesion);
        menuAdd= findViewById(R.id.menuAdd);
        con=DataBase.getInstance().connection(this);
        peliculaController=new PeliculaController(this);
        Bundle data = getIntent().getExtras();

        boolean onCartelera= data.getBoolean("onCartelera",true);
        if (onCartelera){
            listaPelicula=peliculaController.getOnCartelera();
        }else {
            listaPelicula=peliculaController.getCartelera();
        }

        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(this,listaPelicula);
        myRecyclerViewAdapter.setListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PeliculaActivity.class);
            int position = recycler.getChildAdapterPosition(view);
            intent.putExtra("position", position);
            intent.putExtra("dni", data.getString("dni"));
            intent.putExtra("onCartelera",data.getBoolean("onCartelera"));
            startActivity(intent);
            finish();
        });
        recycler.setAdapter(myRecyclerViewAdapter);

        recycler.setLayoutManager(new GridLayoutManager(this,2));

        String dni= data.getString("dni");

        User userLog=con.where(User.class).equalTo("DNI",dni).findFirst();
        if(userLog!=null){
            if (Rol.ADMIN.getNum()== userLog.getRol()){
                addPeliculas.setVisibility(View.VISIBLE);
                addSala.setVisibility(View.VISIBLE);
                addSesion.setVisibility(View.VISIBLE);
                menuAdd.setVisibility(View.VISIBLE);


            }else {
                Toast.makeText(this, dni, Toast.LENGTH_SHORT).show();
            }
        }
        addPeliculas.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddPeliculaActivity.class);
            intent.putExtra("dni",dni);
            intent.putExtra("onCartelera",data.getBoolean("onCartelera"));
            startActivity(intent);
            finish();
        });
        menuAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });


    }
    private void showFABMenu(){
        isFABOpen=true;
        addPeliculas.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        addSala.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        addSesion.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        addPeliculas.animate().translationY(0);
        addSala.animate().translationY(0);
        addSesion.animate().translationY(0);
    }
}