package com.yq.mascotaavanzado.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yq.mascotaavanzado.R;
import com.yq.mascotaavanzado.adapter.MascotaAdaptador;
import com.yq.mascotaavanzado.database.BaseDatos;
import com.yq.mascotaavanzado.pojo.Mascota;

import java.util.ArrayList;

public class PerfilFragment extends Fragment {

    ArrayList<Mascota> mascotas;
    protected RecyclerView rvMascotas;
    protected BaseDatos database;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        rvMascotas = (RecyclerView) v.findViewById(R.id.rvgrid);

        //Cuadricula
        GridLayoutManager glm = new GridLayoutManager(v.getContext(), 2);
        glm.setOrientation(GridLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(glm);

        database = new BaseDatos(v.getContext());
        inicializarAdaptador(v.getContext());
        return v;
    }
    //Adaptador
    private void inicializarAdaptador(Context context){
        MascotaAdaptador adaptador = new MascotaAdaptador(database.obtenerMascotasFavoritas());
        rvMascotas.setAdapter(adaptador);
    }
}