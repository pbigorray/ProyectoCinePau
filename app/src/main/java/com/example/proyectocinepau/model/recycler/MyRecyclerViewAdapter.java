//package com.example.proyectocinepau.model.recycler;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.proyectocinepau.R;
//import com.example.proyectocinepau.db.DataBase;
//
//import io.realm.Realm;
//
//public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{
//    private LayoutInflater inflater;
//    private View.OnClickListener listener;
//
//    private Realm con;
//    public MyRecyclerViewAdapter(Context context){
//        con = DataBase.getInstance().connection(context);
//        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        View view=inflater.inflate(R.layout.simple_element,parent,false);
//        View view=inflater.inflate(R.layout.simple_element,parent,false);
//        view.setOnClickListener(listener);
//        return new ViewHolder(view);
//    }
//
//    public void  setListener(View.OnClickListener listener){this.listener=listener;}
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//        Game g = DB.getAll().get(position);
//
//        holder.id.setText("ID: "+g.getId());
//        holder.name.setText(""+g.getName());
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return DB.getAll().size();
//    }
//
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//       TextView id,name;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            id=itemView.findViewById(R.id.idGame);
//            name=itemView.findViewById(R.id.nombre);
//        }
//    }