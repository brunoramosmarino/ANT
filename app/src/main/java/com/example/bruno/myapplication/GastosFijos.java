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

public class GastosFijos extends AppCompatActivity {
    private EditText nombre, monto ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_fijos);

        monto= (EditText)findViewById(R.id.text2);
        nombre= ( EditText)findViewById(R.id.txt1);
    }
    public void Agregar(View view) {
        AdministrarBase admin = new AdministrarBase(this, "administracion",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select numero, valor from gastosfijos", null);
        String nombredelgasto = nombre.getText().toString();
        String montodelgasto = monto.getText().toString();
        String numerodelgasto = fila.getCount()+"";
        if(!nombredelgasto.isEmpty() && !montodelgasto.isEmpty()){
            ContentValues registro= new ContentValues();
            registro.put("numero", numerodelgasto);
            registro.put("valor", montodelgasto);
            registro.put("nombre", nombredelgasto);
            BaseDeDatos.insert("gastosfijos", null, registro);
            BaseDeDatos.close();
            monto.setText("");
            nombre.setText("");

            Toast.makeText(this, "Se guardo", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
        }
        Intent vermain = new Intent(this, MainActivity.class);
        startActivity(vermain);
    }


    public void Buscar(View view){
        AdministrarBase admin = new AdministrarBase(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();
        String nombres = nombre.getText().toString();
        if(!nombres.isEmpty()){

            Cursor fila = BaseDeDatabase.rawQuery("select nombre, valor from gastosfijos where nombre ="+ nombres, null);

            if(fila.moveToFirst()){
                monto.setText(fila.getString(0));
                BaseDeDatabase.close();

            }else{
                Toast.makeText(this, "El gasto no existe", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this, "Introduce el nombre del gasto", Toast.LENGTH_LONG).show();
        }

    }
}
