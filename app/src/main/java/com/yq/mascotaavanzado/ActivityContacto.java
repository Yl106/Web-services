package com.yq.mascotaavanzado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ActivityContacto extends AppCompatActivity {

    protected EditText txt_campo1, txt_email, txt_mensaje;
    protected Button btnEnviar;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        txt_campo1 = findViewById(R.id.txt_campo1);
        txt_email = findViewById(R.id.txt_email);
        txt_mensaje = findViewById(R.id.txt_mensaje);
        btnEnviar = findViewById(R.id.btnEnviar);

        toolbar = findViewById(R.id.toolbarcontacto);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });
    }
    public void  enviar (View v){
        String enviaremail = txt_email.getText().toString();
        String enviarnombre = txt_campo1.getText().toString();
        String enviarmensaje = txt_mensaje.getText().toString();

        Intent emailIntent = new Intent((Intent.ACTION_SEND));

        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {enviaremail});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, enviarnombre);
        emailIntent.putExtra(Intent.EXTRA_TEXT, enviarmensaje);
        startActivity(Intent.createChooser(emailIntent, "Email "));
    }
    }