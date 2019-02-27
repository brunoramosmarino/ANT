package com.example.bruno.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.util.Log.println;

public class Ahorros extends AppCompatActivity {
    private EditText num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahorros);
        num = (EditText)findViewById(R.id.txt1);
    }

    public void Sumar(View view) {
        AdministrarBase admin = new AdministrarBase(this, "administracion",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select numero, valor from ahorros where numero=0", null);
        String monto = num.getText().toString();
        String numero= "0";
        if(fila.getCount()>0) {
            ContentValues registro = new ContentValues();
            Integer monto1= Integer.parseInt(monto)+ Integer.parseInt(fila.getString(1));
            registro.put("valor", monto1+"");

            int cantidad = BaseDeDatos.update("ahorros", registro, "numero=0", null);
            fila.close();
            Cursor fila2 = BaseDeDatos.rawQuery("select numero, valor from sueldos where numero=0",null);
            ContentValues registro2 = new ContentValues();
            Integer monto2=  Integer.parseInt(fila2.getString(1))-Integer.parseInt(monto);
            registro.put("valor", monto2+"");
            fila2.close();
            BaseDeDatos.update("sueldos", registro, "numero=0", null);
            BaseDeDatos.close();

            if(cantidad == 1 ){
                Toast.makeText(this, "se modifico", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "no se modifico", Toast.LENGTH_LONG).show();
            }

        }else{
            if(!monto.isEmpty()){
                if (!monto.isEmpty()) {
                    ContentValues registro = new ContentValues();
                    registro.put("numero",numero);
                    registro.put("valor", monto);
                    BaseDeDatos.insert("ahorros", null, registro);
                    fila.close();
                    Cursor fila2 = BaseDeDatos.rawQuery("select numero, valor from sueldos where numero=0",null);
                    ContentValues registro2 = new ContentValues();
                    Integer monto4 = Integer.parseInt(monto);
                    Integer monto2=  Integer.parseInt(fila2.getString(1))-monto4;
                    registro.put("valor", monto2+"");
                    fila2.close();
                    BaseDeDatos.update("sueldos", registro, "numero=0", null);
                    BaseDeDatos.close();
                    num.setText("");


                    Toast.makeText(this, "Se guardo", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void Restar(View view) {
        AdministrarBase admin = new AdministrarBase(this, "administracion",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select numero, valor from ahorros where numero=0", null);
        String monto = num.getText().toString();
        String numero= "0";
        if(fila.moveToFirst()) {
            ContentValues registro = new ContentValues();
            Integer monto4 = Integer.parseInt(monto);
            Integer monto1= monto4 - Integer.parseInt(fila.getString(1));
            registro.put("valor", monto1+"");

            int cantidad = BaseDeDatos.update("ahorros", registro, "numero=0", null);
            BaseDeDatos.close();

            if(cantidad == 1 ){
                Toast.makeText(this, "se modifico", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "no se modifico", Toast.LENGTH_LONG).show();
            }

        }else{
            if(!monto.isEmpty()){
                if (!monto.isEmpty()) {
                    ContentValues registro = new ContentValues();
                    registro.put("numero",numero);
                    registro.put("valor", monto);
                    BaseDeDatos.insert("ahorros", null, registro);
                    BaseDeDatos.close();
                    num.setText("");


                    Toast.makeText(this, "Se guardo", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_LONG).show();
            }
        }
    }
}