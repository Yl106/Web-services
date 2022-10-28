package com.yq.mascotaavanzado.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.yq.mascotaavanzado.R;
import com.yq.mascotaavanzado.database.BaseDatos;
import com.yq.mascotaavanzado.pojo.Mascota;

import java.util.ArrayList;

public class MascotagridAdapter extends RecyclerView.Adapter<MascotagridAdapter.MascotaViewHolder>{
    ArrayList<Mascota> mascotas;
    Context context;

    public MascotagridAdapter(ArrayList<Mascota> ma) {
        this.mascotas = ma;

    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardgrid, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder (@NonNull MascotaViewHolder holder,int position){
        BaseDatos database = new BaseDatos(holder.imgFoto.getContext());
        Mascota mascota = mascotas.get(position);
        holder.imgFoto.setImageResource(mascota.getFoto());
        mascota.setRaiting(database.obtenerRatingMascota(mascota));
        holder.contador.setText(String.valueOf(mascota.getRaiting()));
        database.close();
    }

    @Override
    public int getItemCount () {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        protected final AppCompatImageView imgFoto;
        protected final TextView contador;


        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFoto);
            contador = itemView.findViewById(R.id.contador);
        }
    }
}
