package com.example.proyectocinepau.model.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectocinepau.R;
import com.example.proyectocinepau.activitys.CompraEntradaActivity;
import com.example.proyectocinepau.db.controller.SalaController;
import com.example.proyectocinepau.db.controller.SesionController;
import com.example.proyectocinepau.model.ButacaOcupada;
import com.example.proyectocinepau.model.ButacasCompradas;
import com.example.proyectocinepau.model.Sala;
import com.example.proyectocinepau.model.tipos.TipoSala;

import java.util.List;
import java.util.Objects;

public class EntradasAdapter extends RecyclerView.Adapter<EntradasAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private View.OnClickListener clicker;
    private List<ButacaOcupada> butacaOcupadas;
    private Context context;
    private SesionController sesionController;
    private Sala sala;

    public EntradasAdapter(Context context, List<ButacaOcupada> butacaOcupadas, SesionController sesionController,Sala sala) {
        this.context = context;
        this.butacaOcupadas = butacaOcupadas;
        this.sesionController=sesionController;
        this.sala=sala;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }



    @NonNull
    @Override
    public EntradasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.entrada_element, parent, false);
        view.setOnClickListener(clicker);
        return new EntradasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntradasAdapter.ViewHolder viewHolder, int position) {
        ButacaOcupada a = butacaOcupadas.get(position);


        if(Objects.equals(TipoSala.S_3D.getSala(), sala.getTipo())){
            viewHolder.precio.setText("9€");
        }else if(Objects.equals(TipoSala.S_4DX.getSala(), sala.getTipo())){
            viewHolder.precio.setText("11€");
        }else if(Objects.equals(TipoSala.NORMAL.getSala(), sala.getTipo())){
            viewHolder.precio.setText("7€");
        }

        viewHolder.columna.setText("Columna: "+a.getX()+"");
        viewHolder.fila.setText("Fila: "+a.getY()+"");
    }

    @Override
    public int getItemCount() {
        return butacaOcupadas.size();
    }

    public void setOnClickListener(View.OnClickListener click){
        clicker = click;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fila, columna, precio;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fila = itemView.findViewById(R.id.fila);
            columna = itemView.findViewById(R.id.columna);
            precio = itemView.findViewById(R.id.precio);

        }
    }
}
