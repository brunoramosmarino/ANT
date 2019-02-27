package com.example.bruno.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EliminarGastoFijo extends AppCompatActivity {
    private EditText  numero;
    private TextView nombre, monto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_gasto_fijo);

        monto= (TextView)findViewById(R.id.text2);
        nombre= (TextView)findViewById(R.id.txt1);
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

    public void Eliminar(View view){
        AdministrarBase admin = new AdministrarBase(this, "administracion",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String idg = numero.getText().toString();

        if (!idg.isEmpty()){

            int cantidad = BaseDeDatos.delete("gastosfijos", "numero="+ idg, null);
            BaseDeDatos.close();
            numero.setText("");
            nombre.setText("");
            monto.setText("");

            if(cantidad == 1){
                Intent vermain = new Intent(this, MainActivity.class);
                startActivity(vermain);
            }else{
                Toast.makeText(this, "El gasto no existe", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "Debe intoducir el numero para buscar", Toast.LENGTH_LONG).show();
        }

    }

}
