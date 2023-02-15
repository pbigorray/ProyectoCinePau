package com.example.proyectocinepau.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.proyectocinepau.R;
import com.example.proyectocinepau.activitys.add.AddPeliculaActivity;
import com.example.proyectocinepau.db.DataBase;
import com.example.proyectocinepau.db.controller.PeliculaController;
import com.example.proyectocinepau.model.Pelicula;
import com.example.proyectocinepau.model.User;
import com.example.proyectocinepau.model.recycler.MyRecyclerViewAdapter;
import com.example.proyectocinepau.model.tipos.Genero;
import com.example.proyectocinepau.model.tipos.Rol;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;

public class CarteleraActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private PeliculaController pc;
    private FloatingActionButton addButton;
    private Realm con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartelera);
        recycler=findViewById(R.id.recycle);
        addButton=findViewById(R.id.addPeliculas);

        Bundle data = getIntent().getExtras();
        String dni= data.getString("dni");

        User userLog=con.where(User.class).equalTo("dni",dni).findFirst();
        if (userLog.getRol()== Rol.ADMIN.getNum()){
            addButton.setVisibility(View.VISIBLE);
            addButton.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), AddPeliculaActivity.class);
                startActivity(intent);
            });
        }

        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(this);
        myRecyclerViewAdapter.setListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PeliculaActivity.class);
            int position = recycler.getChildAdapterPosition(view);
            intent.putExtra("position", position);
//            intent.putExtra("admin", admin);
            startActivity(intent);
        });
        recycler.setAdapter(myRecyclerViewAdapter);

        recycler.setLayoutManager(new GridLayoutManager(this,2));

    }
}