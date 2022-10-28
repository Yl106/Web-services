package com.yq.mascotaavanzado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.yq.mascotaavanzado.adapter.MascotaAdaptador;
import com.yq.mascotaavanzado.pojo.Mascota;

import java.util.ArrayList;

public class SecondaryActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected RecyclerView rvMascotas;
    ArrayList<Mascota> mascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        toolbar = findViewById(R.id.toolbarcontacto);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        rvMascotas = findViewById(R.id.rvMascotas2);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);

        iniciaradaptador();

    }
    //Adaptador
    public void iniciaradaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas);
        rvMascotas.setAdapter(adaptador);
    }

}