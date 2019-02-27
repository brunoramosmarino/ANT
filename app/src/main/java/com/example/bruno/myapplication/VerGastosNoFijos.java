package com.example.bruno.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VerGastosNoFijos extends AppCompatActivity {
    private ListView lv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_gastos_no_fijos);
        AdministrarBase admin = new AdministrarBase(this, "administracion",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select numero, valor, nombre from gastosnofijos", null);
        Integer filaactual= 0;
        Integer contador = fila.getCount()-1;
        List<String> monto = new ArrayList<String>();
        try {
            if (fila.moveToFirst()) {
                do {

                    monto.add("Nombre: "+fila.getString(2)+" - Precio: "+ fila.getString(1));

                    fila.moveToNext();
                } while (contador != filaactual);

            }
        }catch (Exception ex){

        }


        BaseDeDatos.close();

        lv1 = (ListView)findViewById(R.id.lv1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, monto);


        lv1.setAdapter(adapter);

    }
    @Override
    protected void onResume(){
        super.onResume();
        AdministrarBase admin = new AdministrarBase(this, "administracion",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select numero, valor, nombre from gastosnofijos", null);
        Integer filaactual= 0;
        Integer contador = fila.getCount()-1;
        List<String> monto = new ArrayList<String>();
        try {
            if (fila.moveToFirst()) {
                do {

                    monto.add("Nombre: "+fila.getString(2)+" - Precio: "+ fila.getString(1));

                    fila.moveToNext();
                } while (contador != filaactual);

            }
        }catch (Exception ex){

        }


        BaseDeDatos.close();

        lv1 = (ListView)findViewById(R.id.lv1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, monto);


        lv1.setAdapter(adapter);
    }

    public void VerGastosFijos (View view) {
        Intent gastosfijos = new Intent(this, AgregarGastoNoFijo.class);
        startActivity(gastosfijos);
    }

    public void ModificarGastosFijos(View view) {
        Intent modificargastosfijos = new Intent(this, ModificarGastoNoFijo.class);
        startActivity(modificargastosfijos);
    }
    public void Eliminar(View view) {
        Intent eliminargastosnofijos = new Intent(this, EliminarGastoNoFijo.class);
        startActivity(eliminargastosnofijos);
    }
}

