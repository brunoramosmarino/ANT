package com.example.bruno.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarGastoFijo extends AppCompatActivity {
    private EditText nombre, monto, numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_gasto_fijo);
        monto= (EditText)findViewById(R.id.text2);
        nombre= ( EditText)findViewById(R.id.txt1);
        numero= (EditText)findViewById(R.id.text3);

    }

    public void Buscar(View view){
        AdministrarBase admin = new AdministrarBase(this, "administracion",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String nombres = numero.getText().toString();
        if(!nombres.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("select numero, nombre, valor from gastosfijos where numero ="+ nombres, null);

            if(fila.moveToFirst()){
                nombre.setText(fila.getString(1));
                monto.setText(fila.getString(2));
                BaseDeDatos.close();

            }else{
                Toast.makeText(this, "El gasto no existe", Toast.LENGTH_LONG).show();
                BaseDeDatos.close();
            }

        }else{
            Toast.makeText(this, "Introduce el nombre del gasto", Toast.LENGTH_LONG).show();
        }

    }
    public void Modificar(View view){
        AdministrarBase admin = new AdministrarBase(this, "administracion",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String numerodegasto = numero.getText().toString();
        String nombredegasto = nombre.getText().toString();
        String valordegasto = monto.getText().toString();

        if(!numerodegasto.isEmpty() && !nombredegasto.isEmpty() && !valordegasto.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("numero", numerodegasto);
            registro.put("nombre", nombredegasto);
            registro.put("valor", valordegasto);

            int cantidad = BaseDeDatos.update("gastosfijos", registro, "numero="+numerodegasto, null);
            BaseDeDatos.close();

            if(cantidad == 1 ){
                Intent vermain = new Intent(this, MainActivity.class);
                startActivity(vermain);
            }else{
                Toast.makeText(this, "El gasto no existe", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_LONG).show();
        }
    }
}

