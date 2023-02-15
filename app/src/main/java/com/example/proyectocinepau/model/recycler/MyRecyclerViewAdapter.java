package com.example.proyectocinepau.model.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectocinepau.R;
import com.example.proyectocinepau.db.controller.PeliculaController;
import com.example.proyectocinepau.model.Pelicula;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.Realm;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private View.OnClickListener listener;

    private Realm con;
    private List<Pelicula> listaPeliculas;
    private PeliculaController peliculaController;


    public MyRecyclerViewAdapter(Context context) {
//        con = DataBase.getInstance().connection(context);
        peliculaController=new PeliculaController(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        long pelicuals = con.where(Pelicula.class).count();
//        if (pelicuals == 0) {
//            addDefault();
//        }
//        listaPeliculas = con.where(Pelicula.class).equalTo("cartelera",true).findAll();
        listaPeliculas = peliculaController.getCartelera();

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view=inflater.inflate(R.layout.simple_element,parent,false);
        View view = inflater.inflate(R.layout.simple_element, parent, false);
        view.setOnClickListener(listener);
        return new ViewHolder(view);
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pelicula p = listaPeliculas.get(position);

        holder.nombre.setText("" + p.getTitulo());
        Picasso.get().load(p.getUrlImagen()).into(holder.imagen);
    }


    @Override
    public int getItemCount() {
//        return (int) con.where(Pelicula.class).equalTo("cartelera",true).count();
        return peliculaController.getSizeCaratelera();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombrePelicula);
            imagen = itemView.findViewById(R.id.imagen);
        }
    }
}