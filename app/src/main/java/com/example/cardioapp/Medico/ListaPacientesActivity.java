package com.example.cardioapp.Medico;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.cardioapp.AyudaActivity;
import com.example.cardioapp.ConfigActivity;
import com.example.cardioapp.Paciente.Paciente;
import com.example.cardioapp.R;
import com.example.cardioapp.bd.PacienteDbAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ListaPacientesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener{
    ListView listaPacientes;
    private DrawerLayout drawerLayout;
    ArrayList<Paciente> arrayPacientes;
    AdapterListaPacientes adapterListaPacientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);
    //AdapterListaPacientes
        listaPacientes = findViewById(R.id.listapacientes);
        arrayPacientes=generaListaPacientes();
        //AdapterListaPacientes adapterListaPacientes = new AdapterListaPacientes(this, arrayPacientes);
     //Menu
        cabeceraMenu();
        drawerLayout = findViewById(R.id.drawerlayout2);
        findViewById(R.id.btnmenu2).setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        NavigationView navigationView = findViewById(R.id.menu2);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(ListaPacientesActivity.this);
        }
        adapterListaPacientes = new AdapterListaPacientes(this, this);
        listaPacientes.setAdapter(adapterListaPacientes);
      //Hacer clikable los elementos de la lista:
        listaPacientes.setOnItemClickListener(this);
    }

    /**Lista de pacientes del xml arrays.xml de la ruta res*/
    private ArrayList<Paciente> generaListaPacientes() {
        String[] nombrePacientes = getResources().getStringArray(R.array.paciente_nombre);
        String[] apellidosPaciente = getResources().getStringArray(R.array.paciente_apellidos);
        String[] edad = getResources().getStringArray(R.array.edad);
        String[] historiaClinica = getResources().getStringArray(R.array.historia_clinica);
        String[] historiaClinica2 = getResources().getStringArray(R.array.historia_clinica2);
        String[] historiaClinica3 = getResources().getStringArray(R.array.historia_clinica3);
        ArrayList<Paciente> list = new ArrayList<>();

        for (int i = 0; i < nombrePacientes.length; i++) {
            if(getResources().getStringArray(R.array.historia_clinica3).length!=0 && getResources().getStringArray(R.array.historia_clinica2).length!=0) {
                list.add(new Paciente(nombrePacientes[i], apellidosPaciente[i], Integer.parseInt(edad[i]), historiaClinica[i], historiaClinica2[i], historiaClinica3[i]));
            }else if(getResources().getStringArray(R.array.historia_clinica2).length!=0 && getResources().getStringArray(R.array.historia_clinica3).length==0){
                list.add(new Paciente(nombrePacientes[i], apellidosPaciente[i], Integer.parseInt(edad[i]), historiaClinica[i], historiaClinica2[i]));
            } else{
                list.add(new Paciente(nombrePacientes[i], apellidosPaciente[i], Integer.parseInt(edad[i]), historiaClinica[i]));

            }
        }
        return list;
    }
    /**Cierre del menú con la pulsación del botón Atrás o back de Android.**/
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /**Menu*/
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_principal) {
            startActivity(new Intent(ListaPacientesActivity.this, PrincipalActivity.class));
        }else  if (id == R.id.menu_pacientes) {
            startActivity(new Intent(ListaPacientesActivity.this, ListaPacientesActivity.class));
        }else  if (id == R.id.menu_config) {
            startActivity(new Intent(ListaPacientesActivity.this, ConfigActivity.class));
        }else  if (id == R.id.menu_notif) {
            startActivity(new Intent(ListaPacientesActivity.this, ListaPacientesActivity.class));
        }else  if (id == R.id.nav_share) {
            startActivity(new Intent(ListaPacientesActivity.this, AyudaActivity.class));
        }
        onBackPressed();//Quitamos menú para que no se muestre al volver
        return item.isChecked();
    }

    /**Correo del medico en la cabecera del menu*/
    public void cabeceraMenu(){
        //Obtenemos una instancia de FirebaseAuth para acceder a los datos de usuario y el propio usuario
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // Obtenemos el correo electrónico del médico que ha iniciado sesión
        String email = currentUser.getEmail();
        NavigationView vistaMenu = findViewById(R.id.menu2);
        View headerView = vistaMenu.getHeaderView(0);
        TextView textoCabecera = headerView.findViewById(R.id.correoMedico);
        textoCabecera.setText(email); // Cambia el texto del encabezado
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int posicion, long id) {
        Intent intent = new Intent();
            intent.setClass(this, VistaPacienteActivity.class);
        intent.putExtra("position", posicion);
        Paciente paciente = (Paciente) listaPacientes.getItemAtPosition(posicion);
        //Implementando serializable en la clase Paciente, podemos pasarlo de la actividad ListaPacientes a VistaPaciente
        intent.putExtra("paciente", paciente);
        intent.putExtra("id", id);

        startActivity(intent);
    }
}