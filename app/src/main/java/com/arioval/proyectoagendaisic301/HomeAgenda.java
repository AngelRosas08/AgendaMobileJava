package com.arioval.proyectoagendaisic301;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeAgenda extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_agenda);
    }
    public void irAgregarContacto(View v){
        Intent i = new Intent(HomeAgenda.this,AgregarContacto.class);
        startActivity(i);
    }
    public void irVerAgenda(View v){
        Intent i = new Intent(HomeAgenda.this,VerAgenda.class);
        startActivity(i);
    }
    public void irBuscar(View v){
        Intent i = new Intent(HomeAgenda.this,Buscar.class);
        startActivity(i);
    }
}

