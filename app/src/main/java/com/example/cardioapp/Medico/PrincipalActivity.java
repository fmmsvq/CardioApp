package com.example.cardioapp.Medico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.example.cardioapp.AyudaActivity;
import com.example.cardioapp.ConfigActivity;
import com.example.cardioapp.LoginActivity;
import com.example.cardioapp.R;
import com.google.android.material.navigation.NavigationView;

public class PrincipalActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;

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

    }
/**Cierre del menú con la pulsación del botón Atrás o back de Android.**/
    public void onBackPressed() {
    // 1. Instanciar AlertDialog.Builder con el constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
    // 2. Encadenamos varios métodos para establecer las características
        builder.setMessage(R.string.mensaje_popup).setTitle(R.string.titulo_popup);
    // 3. Añadimos los botones
        builder.setPositiveButton(R.string.si_popup, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            // User tab OK
                startActivity(new Intent(PrincipalActivity.this, LoginActivity.class));
            }
        });
        builder.setNegativeButton(R.string.no_popup, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            // User tab NO
                startActivity(new Intent(PrincipalActivity.this, PrincipalActivity.class));
            }
        });
    // 4. Obtener el AlertDialog del Builder mediante el método create()
        AlertDialog dialog = builder.create();

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else if(!drawerLayout.isDrawerOpen(GravityCompat.START)){
            dialog.show();
        }else {
            super.onBackPressed();
        }
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        // Manejar navegación de los clicks sobre los elementos de la vista.
        int id = item.getItemId();

        if (id == R.id.menu_principal) {
            drawerLayout.closeDrawer(GravityCompat.START);
            //startActivity(new Intent(PrincipalActivity.this, PrincipalActivity.class));
        }else  if (id == R.id.menu_pacientes) {
            startActivity(new Intent(PrincipalActivity.this, ListaPacientesActivity.class));
        }else  if (id == R.id.menu_config) {
            startActivity(new Intent(PrincipalActivity.this, ConfigActivity.class));
        }else  if (id == R.id.menu_notif) {
            startActivity(new Intent(PrincipalActivity.this, ListaPacientesActivity.class));
        }else  if (id == R.id.nav_share) {
            startActivity(new Intent(PrincipalActivity.this, AyudaActivity.class));
        }
        onBackPressed();//Quitamos menú para que no se muestre al volver
        return item.isChecked();
    }

   /* private void crearBD(){
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
    }*/

    public void onClickNotificaciones(View view){
        Intent intent = new Intent(PrincipalActivity.this, NotificacionesActivity.class);
        startActivity(intent);
    }

    public void onClickListaPacientes(View view){
        Intent intent = new Intent(PrincipalActivity.this, ListaPacientesActivity.class);
        startActivity(intent);
    }
/**Se llama cuando la captura de puntero está habilitada o deshabilitada para la ventana actual.*/
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    /*private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }*/

}