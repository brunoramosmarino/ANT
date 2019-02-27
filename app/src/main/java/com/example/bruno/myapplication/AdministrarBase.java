package com.example.bruno.myapplication;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdministrarBase extends SQLiteOpenHelper {


    public AdministrarBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table gastosfijos(numero int Primary key,valor int , nombre int)");
        BaseDeDatos.execSQL("create table gastosnofijos(numero int Primary key, valor int , nombre text)");
        BaseDeDatos.execSQL("create table sueldos(numero int primary key, valor int)");
        BaseDeDatos.execSQL("create table ahorros(numero int primary key, valor int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
