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

public class AgregarSueldo extends AppCompatActivity {
    private EditText  sueldo ;
    private Integer a= 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_sueldo);
        sueldo= (EditText)findViewById(R.id.txt1);

    }

    public void Agregarsueldo(View view) {

        AdministrarBase admin = new AdministrarBase(this, "administracion",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila2 = BaseDeDatos.rawQuery("select numero, valor from gastosfijos", null  );
        Integer filaactual= 0;
        Integer contador = fila2.getCount()-1;
        Integer total = 0;
        try {
            if (fila2.moveToFirst()) {
                do {
                    total= total + Integer.parseInt(fila2.getString(1));
                    filaactual = fila2.getPosition();

                    fila2.moveToNext();
                } while (contador != filaactual);

            }
        }catch (Exception ex){
            Toast.makeText(this, "error base de datos", Toast.LENGTH_LONG).show();
        }
        fila2.close();
        Cursor fila3 = BaseDeDatos.rawQuery("select numero, valor from gastosnofijos", null);
        Integer filaactual2= 0;
        Integer contador2 = fila3.getCount()-1;
        Integer total2 = 0;
        try {
            if (fila3.moveToFirst()) {
                do {
                    total2= total2 + Integer.parseInt(fila3.getString(1));
                    filaactual2 = fila3.getPosition();

                    fila3.moveToNext();
                } while (contador2 != filaactual2);

            }
        }catch (Exception ex){
            Toast.makeText(this, "error base de datos", Toast.LENGTH_LONG).show();
        }
        fila3.close();
        Cursor fila = BaseDeDatos.rawQuery("select numero, valor from sueldos", null);


        String montodelgasto = sueldo.getText().toString();
        String numerodelgasto = (fila.getCount())+"";
        if(!montodelgasto.isEmpty()){
            if(fila.moveToFirst()){
                Integer a = (Integer.parseInt(fila.getString(1))-(total+total2))+Integer.parseInt(montodelgasto);
                fila.close();
                montodelgasto=a+"";
                ContentValues registro = new ContentValues();
                registro.put("valor", montodelgasto);
                int cantidad = BaseDeDatos.update("sueldos", registro, "numero=0", null);

                Cursor fila4 = BaseDeDatos.rawQuery("select numero, valor from gastosnofijos", null);
                Integer filaactual3= 0;
                Integer contador3 = fila4.getCount()-1;

                try {
                    if (fila4.moveToFirst()) {
                        do {

                            filaactual3 = fila4.getPosition();
                            String idg = fila4.getString(0);

                            BaseDeDatos.delete("gastosnofijos", "numero="+ idg, null);
                            fila4.moveToNext();

                        } while (contador3 != filaactual3);

                    }
                }catch (Exception ex){
                    Toast.makeText(this, "error base de datos", Toast.LENGTH_LONG).show();
                }
                fila4.close();


                BaseDeDatos.close();

                if(cantidad == 1 ){
                    Intent vermain = new Intent(this, MainActivity.class);
                    startActivity(vermain);
                }else{
                    Toast.makeText(this, "No se guardo", Toast.LENGTH_LONG).show();
                }
            }else{
                ContentValues registro = new ContentValues();
                registro.put("numero", numerodelgasto);
                registro.put("valor", montodelgasto);
                BaseDeDatos.insert("sueldos", null, registro);
                BaseDeDatos.close();
                sueldo.setText("");

            }

            }else {
                Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_LONG).show();


            }

        }

}
