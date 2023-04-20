package com.arioval.proyectoagendaisic301;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Buscar extends AppCompatActivity {
    //conectando al servidor de SQLite
    ConexionSQLite conexion = new ConexionSQLite(this, "agenda", null, 1);
    private EditText txIdAlumno;
    private TextInputEditText txNombre;
    private TextInputEditText txTelefono;
    private TextInputEditText txCorreo;
    private TextInputEditText txDomicilio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_buscar);

            txIdAlumno = findViewById(R.id.txtId);
            txNombre = findViewById(R.id.txtNombreBuscar);
            txTelefono = findViewById(R.id.txtTelefonoBuscar);
            txCorreo = findViewById(R.id.txtCorreoBuscar);
            txDomicilio = findViewById(R.id.txtDomicilioBuscar);
        }catch (Exception e){
            Toast.makeText(this,"excepcion :"+e, Toast.LENGTH_LONG).show();
        }
    }
    public void buscarAlumno(View V) {
        //crear objeto para manipular la base de datos
        SQLiteDatabase baseDatos = conexion.getWritableDatabase();

        //estraer el id alumno
        String id = txIdAlumno.getText().toString();

        if (id.trim().length() == 0) {
            txIdAlumno.setError("debe ingresar el id a buscar");
            return;
        }
        //extraemos el registro en la tabla alumno
        Cursor registro = baseDatos.rawQuery("SELECT * FROM alumno WHERE idalumno= "+id, null);
        //cambiando registro del formulario
        if (registro.moveToFirst()){
            txNombre.setText(registro.getString(1));
            txTelefono.setText(registro.getString(2));
            txCorreo.setText(registro.getString(3));
            txDomicilio.setText(registro.getString(4));
        }else{
            Toast.makeText(this,"contacto no encontrado con id ="+ id,Toast.LENGTH_SHORT).show();
            limpiarFormulario();
        }
        baseDatos.close();
    }
    public void actualizarAlumno(View v){
        //crear objeto para manipular la base de datos
        SQLiteDatabase baseDatos = conexion.getWritableDatabase();

        //extraer losndatos del fotmulario ya editados por usuario
        String id = txIdAlumno.getText().toString();
        String nombre = txNombre.getText().toString();
        String tel = txTelefono.getText().toString();
        String correo = txCorreo.getText().toString();
        String domicilio = txDomicilio.getText().toString();

        if (id.trim().length() == 0){
            txIdAlumno.setError("el id del alumno no puede estar vacio");
            return;
        }
        if (nombre.trim().length() == 0){
            txNombre.setError("el nombre del alumno no puede estar vacio");
            return;
        }
        if (tel.trim().length() == 0){
            txTelefono.setError("el telefono del alumno no puede estar vacio");
            return;
        }
        if (correo.trim().length() == 0){
            txCorreo.setError("el correo del alumno no puede estar vacio");
            return;
        }
        if (domicilio.trim().length() == 0){
            txDomicilio.setError("el domicilio del alumno no puede estar vacio");
            return;
        }
        //creando el objeto ContentVaues con los valores del registro
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("nombre",nombre);
        nuevoRegistro.put("telefono",tel);
        nuevoRegistro.put("correo",correo);
        nuevoRegistro.put("domicilio",domicilio);

        //actualizando el registro Alumno
        int numRegActualizados = baseDatos.update("alumno",nuevoRegistro,"idalumno:"+id,null);
        if (numRegActualizados == 1){
            Toast.makeText(this,"actualizado correctamente :)", Toast.LENGTH_SHORT);
            limpiarFormulario();
        }else{
            Toast.makeText(this,"problemas al actualizar :(",Toast.LENGTH_SHORT);
        }
        baseDatos.close();
    }
    public void borrarAlumno(View v){
        //crear objeto para manipular la base de datos
        SQLiteDatabase baseDatos = conexion.getWritableDatabase();

        //estraer el id alumno
        String id = txIdAlumno.getText().toString();

        if (id.trim().length() == 0) {
            txIdAlumno.setError("debe ingresar el id a buscar");
            return;
        }
        //borrando el registro del alumno
        int numRegEliminados = baseDatos.delete("alumno","idalumno="+id,null);
        if (numRegEliminados == 1){
            Toast.makeText(this,"Registro del alumno eliminado correctamente", Toast.LENGTH_SHORT);
            limpiarFormulario();
        }
        baseDatos.close();
    }
    public void cancelarBuscar(View v){
        txIdAlumno.setText(null);
        limpiarFormulario();
    }
    public void regresarBuscar(View v){
        finish();
    }

    private void limpiarFormulario(){
        txNombre.setText(null);
        txTelefono.setText(null);
        txCorreo.setText(null);
        txDomicilio.setText(null);
        txIdAlumno.requestFocus();
    }
}