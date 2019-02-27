package com.example.bruno.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarGastoNoFijo extends AppCompatActivity {
    private EditText nombre, monto ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_gasto_no_fijo);
        monto= (EditText)findViewById(R.id.text2);
        nombre= ( EditText)findViewById(R.id.txt1);

    }

    public void Agregar(View view) {
        AdministrarBase admin = new AdministrarBase(this, "administracion",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select numero, valor from gastosnofijos", null);
        String nombredelgasto = nombre.getText().toString();
        String montodelgasto = monto.getText().toString();
        String numerodelgasto = fila.getCount()+"";
        if(!nombredelgasto.isEmpty() && !montodelgasto.isEmpty()){
            ContentValues registro= new ContentValues();
            registro.put("numero", numerodelgasto);
            registro.put("valor", montodelgasto);
            registro.put("nombre", nombredelgasto);
            BaseDeDatos.insert("gastosnofijos", null, registro);
            BaseDeDatos.close();
            monto.setText("");
            nombre.setText("");
        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
        }
        Intent vermain = new Intent(this, MainActivity.class);
        startActivity(vermain);
    }



}
