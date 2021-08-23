package com.example.cardioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

    // Menu lateral
        final DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
        findViewById(R.id.btnmenu).setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // Base de Datos
        BD bd = new BD(this);
        SQLiteDatabase db = bd.getWritableDatabase();
        if (db != null) {
            // Insert con execSQL
            //db.execSQL("INSERT INTO comments (user, comment) VALUES ('Digital Learning','Esto es un comentario insertado usando el método execSQL()')");

            // Insert con ContentValues
            ContentValues valores = new ContentValues();
            valores.put("Medico","María del Pozo Gómez");
            valores.put("Paciente","Armando Lagares Marco");
            valores.put("Paciente","Carlos Fernandez del Pozo");
            valores.put("Paciente","Carmen Dominguez Holgado");
            valores.put("Paciente","Marta Lopez Flores");
            valores.put("Paciente","Raquel Flores Capitan");
            valores.put("Paciente","Teresa Pedrosa Lopez");
            valores.put("Paciente","Joaquin Bermudez Díaz");
            valores.put("Paciente","Pedro Macías Ibáñez");
            //valores.put("","");
        }
    }
}