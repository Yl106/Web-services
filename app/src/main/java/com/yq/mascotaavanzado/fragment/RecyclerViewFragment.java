package com.yq.mascotaavanzado.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yq.mascotaavanzado.R;
import com.yq.mascotaavanzado.adapter.MascotaAdaptador;
import com.yq.mascotaavanzado.database.BaseDatos;
import com.yq.mascotaavanzado.pojo.Mascota;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {

    ArrayList<Mascota> mascotas;
    protected RecyclerView rvMascotas;
    protected BaseDatos db;

    public RecyclerViewFragment(){
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        rvMascotas = v.findViewById(R.id.rvMascotas2);

        //Lista
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);
        //Cuadricula
        //GridLayoutManager glm = new GridLayoutManager(this, 4);

        db = new BaseDatos(v.getContext());
        db.insertarMascota("Romeo","Pasivo", "San Bernardo", R.mipmap.ic_bernardo);
        db.insertarMascota("Cookie","Activo", "Husky",R.mipmap.ic_husky);
        db.insertarMascota("Luna","Alerta", "Yorkshire", R.mipmap.ic_yorkshire);
        db.insertarMascota("Motas", "Pasivo", "Caniche",R.mipmap.ic_caniche);
        db.insertarMascota("Caballero","Activo", "Bulldog",R.mipmap.ic_bulldogg);

        inicializarAdaptador(v.getContext());

        return v;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
    //Adaptador
    private void inicializarAdaptador(Context context){
         BaseDatos db = new BaseDatos(context);
        MascotaAdaptador adaptador = new MascotaAdaptador(db.obtenerMascotas());
        rvMascotas.setAdapter(adaptador);
    }
}