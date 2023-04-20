package com.arioval.proyectoagendaisic301;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creando el thread(hilo)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent abrirHome = new Intent(MainActivity.this,HomeAgenda.class);
                startActivity(abrirHome);
                finish();   //termina o cierra la actividad actual
            }
        },4000);
    }
}


