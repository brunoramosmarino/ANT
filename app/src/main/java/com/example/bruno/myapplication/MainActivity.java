package com.example.bruno.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView text1,text2, text3;
    private String t1="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1=(TextView)findViewById(R.id.txt1);
        text2=(TextView)findViewById(R.id.txt2);
        text3=(TextView)findViewById(R.id.txt3);
        AdministrarBase admin = new AdministrarBase(this, "administracion",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select numero, valor from gastosfijos", null);
        text1.setText("");
        Integer filaactual= 0;
        Integer contador = fila.getCount()-1;
        Integer total = 0;
        try {
            if (fila.moveToFirst()) {
                do {
                    total= total + Integer.parseInt(fila.getString(1));
                    filaactual = fila.getPosition();

                    fila.moveToNext();
                } while (contador != filaactual);
                text1.setText(total+"");
            }
        }catch (Exception ex){
            Toast.makeText(this, "error base de datos", Toast.LENGTH_LONG).show();
        }
        fila.close();
        Cursor fila2 = BaseDeDatos.rawQuery("select numero, valor from gastosnofijos", null);
        text2.setText("");
        Integer filaactual2= 0;
        Integer contador2 = fila2.getCount()-1;
        Integer total2 = 0;

        try {
            if (fila2.moveToFirst()) {
                do {
                    total2= total2 + Integer.parseInt(fila2.getString(1));
                    filaactual2 = fila2.getPosition();

                    fila2.moveToNext();
                } while (contador2 != filaactual2);
                text2.setText(total2+"");
            }

        }catch (Exception ex){
            Toast.makeText(this, "error base de datos", Toast.LENGTH_LONG).show();
        }
        fila2.close();
        Cursor fila3 = BaseDeDatos.rawQuery("select numero, valor from sueldos", null);
        text3.setText("");
        Integer filaactual3= 0;
        Integer contador3 = fila3.getCount()-1;
        Integer total3 = 0;
        try {
            if (fila3.moveToFirst()) {
                do {

                    total3= Integer.parseInt(fila3.getString(1))-(total+total2);


                } while (contador3 != filaactual3);
                text3.setText(total3+"");
                t1=total3+"";
            }
        }catch (Exception ex){
            Toast.makeText(this, "error base de datos", Toast.LENGTH_LONG).show();
        }

        BaseDeDatos.close();

    }
    @Override
    protected void onResume(){
        super.onResume();
        AdministrarBase admin = new AdministrarBase(this, "administracion",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select numero, valor from gastosfijos", null);
        text1.setText("");
        Integer filaactual= 0;
        Integer contador = fila.getCount()-1;
        Integer total = 0;
        try {
            if (fila.moveToFirst()) {
                do {
                    total= total + Integer.parseInt(fila.getString(1));
                    filaactual = fila.getPosition();

                    fila.moveToNext();
                } while (contador != filaactual);
                text1.setText(total+"");
            }
        }catch (Exception ex){
            Toast.makeText(this, "error base de datos", Toast.LENGTH_LONG).show();
        }
        fila.close();
        Cursor fila2 = BaseDeDatos.rawQuery("select numero, valor from gastosnofijos", null);
        text2.setText("");
        Integer filaactual2= 0;
        Integer contador2 = fila2.getCount()-1;
        Integer total2 = 0;
        try {
            if (fila2.moveToFirst()) {
                do {
                    total2= total2 + Integer.parseInt(fila2.getString(1));
                    filaactual2 = fila2.getPosition();

                    fila2.moveToNext();
                } while (contador2 != filaactual2);
                text2.setText(total2+"");
            }
        }catch (Exception ex){
            Toast.makeText(this, "error base de datos", Toast.LENGTH_LONG).show();
        }
        fila2.close();
        Cursor fila3 = BaseDeDatos.rawQuery("select numero, valor from sueldos", null);
        text3.setText("");
        Integer filaactual3= 0;
        Integer contador3 = fila3.getCount()-1;
        Integer total3= 0;
        try {
            if (fila3.moveToFirst()) {
                do {

                    total3= Integer.parseInt(fila3.getString(1))-(total+total2);


                } while (contador3 != filaactual3);
                text3.setText(total3+"");
            }
        }catch (Exception ex){
            Toast.makeText(this, "error base de datos", Toast.LENGTH_LONG).show();
        }
        BaseDeDatos.close();

    }
    public void GastosFijos(View view) {
        Intent vergastosfijos = new Intent(this, VerGastosFijos.class);
        startActivity(vergastosfijos);
    }
    public void VerGastosNoFijos (View view) {
        Intent gastosfijos = new Intent(this, VerGastosNoFijos.class);
        startActivity(gastosfijos);
    }
    public void Agregarsueldo(View view){
        Intent agregarsueldo = new Intent(this, AgregarSueldo.class);
        agregarsueldo.putExtra("total1",t1);

        startActivity(agregarsueldo);


        }



}

