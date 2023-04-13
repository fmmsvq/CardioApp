package com.example.cardioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Notification;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;

public class PrincipalActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private MenuItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

     // Menu lateral
        drawerLayout = findViewById(R.id.drawerlayout);
        findViewById(R.id.btnmenu).setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        NavigationView navigationView = findViewById(R.id.menu);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(PrincipalActivity.this);
        }


    // Base de Datos
        crearBD();

    // Click Contenedores para abrir ListaPacientes
        findViewById(R.id.contenedor1).setClickable(true);
        findViewById(R.id.contenedor2).setClickable(true);
        findViewById(R.id.contenedor3).setClickable(true);
        findViewById(R.id.contenedor4).setClickable(true);


    }
/**Cierre del menú con la pulsación del botón Atrás o back de Android.**/
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_principal) {
            startActivity(new Intent(PrincipalActivity.this, PrincipalActivity.class));
        }else  if (id == R.id.menu_pacientes) {
            startActivity(new Intent(PrincipalActivity.this, ListaPacientesActivity.class));
        }else  if (id == R.id.menu_config) {
            startActivity(new Intent(PrincipalActivity.this, ConfigActivity.class));
        }else  if (id == R.id.menu_notif) {
            startActivity(new Intent(PrincipalActivity.this, ListaPacientesActivity.class));
        }else  if (id == R.id.nav_share) {
            startActivity(new Intent(PrincipalActivity.this, AyudaActivity.class));
        }
        return item.isChecked();
    }

    private void crearBD(){
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

    /*public void onClick(View view) {
        Intent intent = new Intent(PrincipalActivity.this, ListaPacientesActivity.class);
        startActivity(intent);
    }*/
    public void onClickNotificaciones(View view){
        Intent intent = new Intent(PrincipalActivity.this, NotificacionesActivity.class);
        startActivity(intent);
    }

    public void onClickListaPacientes(View view){
        Intent intent = new Intent(PrincipalActivity.this, ListaPacientesActivity.class);
        startActivity(intent);
    }

    public void onClickConfig(View view) {
        Intent intent = new Intent(PrincipalActivity.this, ConfigActivity.class);
        startActivity(intent);
    }

    public void onClickAyuda(View view) {

    }
    /*private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }*/

}