package com.arioval.proyectoagendaisic301;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class VerAgenda extends AppCompatActivity {
    private ListView listaAlumnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ver_agenda);

            listaAlumnos = findViewById(R.id.listAlumnos);

            //conectando al servidor de SQLite
            ConexionSQLite conexion = new ConexionSQLite(this, "agenda", null, 1);
            //crear objeto para manipular la base de datos
            SQLiteDatabase baseDatos = conexion.getWritableDatabase();

            Cursor datosAlumno = baseDatos.rawQuery("SELECT idalumno || '\nnombre: ' || nombre ||'\ntelefono: ' || telefono || '\ncorreo: ' || correo || '\nDomicilio: ' || domicilio FROM alumno",null);
            //contabilizando el numero de registro en datosAlumno
            int cont = datosAlumno.getCount();
            //creando array con el tamaño de registros
            String arrayAlumno [] = new String[cont];

            //moviendo el puntero de cursor al objeto 1
            datosAlumno.moveToFirst();
            int i=0;
            //pasando registros de cursor(datosAlumno) al array (arraylumnos)
            while (i < cont) {
                //pasando del cursor al array
                arrayAlumno[i] = datosAlumno.getString(0);
                datosAlumno.moveToNext();
                i++;
            }
            //pasando los datos del array al arrayAdapte
            ArrayAdapter<String> adapterAlumnos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayAlumno);

            //pasando el arrayAdapter al listView
            listaAlumnos.setAdapter(adapterAlumnos);
        }catch (Exception e){
            Toast.makeText(this,"Excepcion:"+e,Toast.LENGTH_LONG).show();
        }
    }
    public void regresarHome(View v){
        finish();
    }
}