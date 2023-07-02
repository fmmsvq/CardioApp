package com.example.cardioapp.Medico;

import static com.example.cardioapp.R.id;
import static com.example.cardioapp.R.layout;
import static com.example.cardioapp.R.string;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.cardioapp.AyudaActivity;
import com.example.cardioapp.ConfigActivity;
import com.example.cardioapp.LoginActivity;
import com.example.cardioapp.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jjoe64.graphview.GraphView;

import org.w3c.dom.Text;

public class PrincipalActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_principal);

        //Grafica paciente
        graphView  = findViewById(id.graficaPpal);

        // Menu lateral
        cabeceraMenu();
        drawerLayout = findViewById(id.drawerlayout);
        findViewById(id.btnvolver).setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        NavigationView navigationView = findViewById(id.menu);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(PrincipalActivity.this);
        }
    }

    /**Cierre del menú con la pulsación del botón Atrás o back de Android.**/
    public void onBackPressed() {
    // 1. Instanciar AlertDialog.Builder con el constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
    // 2. Encadenamos varios métodos para establecer las características
        builder.setMessage(string.mensaje_popup).setTitle(string.titulo_popup);
    // 3. Añadimos los botones
        builder.setPositiveButton(string.si_popup, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            // User tab OK
                startActivity(new Intent(PrincipalActivity.this, LoginActivity.class));
            }
        });
        builder.setNegativeButton(string.no_popup, new DialogInterface.OnClickListener() {
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

    public void onClickVistaPaciente(View view){
        Intent intent = new Intent(PrincipalActivity.this, VistaPacienteActivity.class);
        startActivity(intent);
    }

    /**Se llama cuando la captura de puntero está habilitada o deshabilitada para la ventana actual.*/
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    /**Correo del medico en la cabecera del menu*/
    public void cabeceraMenu(){
        //Obtenemos una instancia de FirebaseAuth para acceder a los datos de usuario y el propio usuario
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // Obtenemos el correo electrónico del médico que ha iniciado sesión
        String email = currentUser.getEmail();
        NavigationView vistaMenu = findViewById(R.id.menu);
        View headerView = vistaMenu.getHeaderView(0);
        TextView textoCabecera = headerView.findViewById(id.correoMedico);
        textoCabecera.setText(email); // Cambia el texto del encabezado
    }

}