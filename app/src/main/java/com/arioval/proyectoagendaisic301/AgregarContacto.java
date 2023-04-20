package com.arioval.proyectoagendaisic301;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

public class AgregarContacto extends AppCompatActivity {
    private TextInputEditText txNombre;
    private TextInputEditText txTelefono;
    private TextInputEditText txCorreo;
    private TextInputEditText txDomicilio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_agregar_contacto);

            txNombre = findViewById(R.id.txtNombre);
            txTelefono = findViewById(R.id.txtTelefono);
            txCorreo = findViewById(R.id.txtCorreo);
            txDomicilio = findViewById(R.id.txtDomicilio);

        }catch (Exception e){
            Toast.makeText(this,"Excepcion: "+e, Toast.LENGTH_LONG).show();
        }
    }

    public void agregarAlumno(View v){
        try {

            //datos de formulario agregarAlumno
            String nombre = txNombre.getText().toString();
            String tel = txTelefono.getText().toString();
            String correo = txCorreo.getText().toString();
            String domicilio = txDomicilio.getText().toString();

            //validando que las cajas de texto no esten vacias
            if (nombre.trim().length() == 0) {
                txNombre.setError("ingrese un nombre dentro del campo");
                return;
            }
            if (tel.trim().length() == 0) {
                txTelefono.setError("inngrese un numero de telefono dentro del campo");
                return;
            }
            if (correo.trim().length() == 0) {
                txCorreo.setError("ingrese un correo dentro del campo");
                return;
            }
            if (domicilio.trim().length() == 0) {
                txDomicilio.setError("ingrese un domicilio dentro del campo");
                return;
            }
            //instrucciones se ejecutan si los campos no estan vacios
            //conectando al servidor de SQLite
            ConexionSQLite conexion = new ConexionSQLite(this, "agenda", null, 1);
            //crear objeto para manipular la base de datos
            SQLiteDatabase baseDatos = conexion.getWritableDatabase();

            //crear un objeto para construir el registro del alumno
            ContentValues registroAlumno = new ContentValues();
            //agregando valores al registro
            registroAlumno.put("nombre", nombre);
            registroAlumno.put("telefono", tel);
            registroAlumno.put("correo", correo);
            registroAlumno.put("domicilio", domicilio);
            //guardando el registro en la tabla
            baseDatos.insert("alumno", null, registroAlumno);
            Toast.makeText(this, "contacto registrado correctamente", Toast.LENGTH_SHORT).show();
            LimpiarFormulario();
            conexion.close();
        }catch (Exception e){
            Toast.makeText(this,"Excepcion: "+e, Toast.LENGTH_LONG).show();
        }
    }
            //metodo para borrar los datos de las cajas de texto
            private void LimpiarFormulario(){
                txNombre.setText(null);
                txTelefono.setText(null);
                txCorreo.setText(null);
                txDomicilio.setText(null);
                //colocamos el puntero en la caja de tecto nombre
                txNombre.requestFocus();
            }
            public void regresar(View v){
                finish();
            }
            public void cancelar(View v){
                LimpiarFormulario();
            }
}