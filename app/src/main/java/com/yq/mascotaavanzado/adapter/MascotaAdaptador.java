package com.yq.mascotaavanzado.adapter;

import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.yq.mascotaavanzado.R;
import com.yq.mascotaavanzado.database.BaseDatos;
import com.yq.mascotaavanzado.pojo.Mascota;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{
    ArrayList<Mascota> mascotas;

    public MascotaAdaptador(ArrayList<Mascota> mascotas){
        this.mascotas = mascotas;
    }

    @NonNull
    @Override
    //Inflar el layout y lo pasará al viewholder para que obtenga los views
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    //Asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder mascotaViewHolder, int position) {
        Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.contador.setText(String.valueOf(mascota.getContador()));
        mascotaViewHolder.tvNombreCV.setText(mascota.getNombre());
        mascotaViewHolder.tvIdentificacionCV.setText(mascota.getIdentificacion());
        mascotaViewHolder.tvRazaCV.setText(mascota.getRaza());
        mascotaViewHolder.imgLike.setImageResource(R.mipmap.ic_bone);

        mascotaViewHolder.imgLike.setOnClickListener(v -> {
            final Dialog dialog =new Dialog(v.getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.raiting);
            dialog.show();
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.CENTER);

            RatingBar ratingBar = dialog.findViewById(R.id.raitingBar);

            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    BaseDatos database = new BaseDatos(v.getContext());
                    mascota.setRaiting((int)rating);
                    database.insertarRating(mascota);
                    mascotaViewHolder.contador.setText(String.valueOf((database.obtenerRatingMascota(mascota))));
                    database.close();

                }
            });
        });
    }

    @Override
    public int getItemCount() { return mascotas.size();}

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        protected final AppCompatImageView imgFoto, imgLike;
        protected TextView tvNombreCV, contador, tvIdentificacionCV, tvRazaCV;


        public MascotaViewHolder(View itemView){
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFoto);
            tvNombreCV = itemView.findViewById(R.id.tvNombreCV);
            tvIdentificacionCV = itemView.findViewById(R.id.tvIdentificacionCV);
            tvRazaCV = itemView.findViewById(R.id.tvRazaCV);
            imgLike = itemView.findViewById(R.id.imgLike);
            contador = itemView.findViewById(R.id.contador);

        }
    }
}
