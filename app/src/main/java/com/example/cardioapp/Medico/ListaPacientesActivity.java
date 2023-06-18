package com.example.cardioapp.Medico;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.cardioapp.AyudaActivity;
import com.example.cardioapp.ConfigActivity;
import com.example.cardioapp.Paciente.Paciente;
import com.example.cardioapp.R;
import com.example.cardioapp.bd.PacienteDbAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ListaPacientesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    PacienteDbAdapter helper;
    ListView listaPacientes;
    private DrawerLayout drawerLayout;
    ArrayList<Paciente> arrayPacientes;
    AdapterListaPacientes adapterListaPacientes;
    //TextView nombrePaciente, apellidosPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);
    //AdapterListaPacientes
        listaPacientes = findViewById(R.id.listapacientes);
        arrayPacientes=generaListaPacientes();
        //AdapterListaPacientes adapterListaPacientes = new AdapterListaPacientes(this, arrayPacientes);
     //Menu
        drawerLayout = findViewById(R.id.drawerlayout2);
        findViewById(R.id.btnmenu2).setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        NavigationView navigationView = findViewById(R.id.menu2);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(ListaPacientesActivity.this);
        }
        adapterListaPacientes = new AdapterListaPacientes(this, this);
        listaPacientes.setAdapter(adapterListaPacientes);
      //  helper = new PacienteDbAdapter(this);



    }

    private ArrayList<Paciente> generaListaPacientes() {
        String[] nombrePacientes = getResources().getStringArray(R.array.paciente_nombre);
        String[] apellidosPaciente = getResources().getStringArray(R.array.paciente_apellidos);
        String[] edad = getResources().getStringArray(R.array.edad);
        String[] historiaClinica = getResources().getStringArray(R.array.historia_clinica);
        ArrayList<Paciente> list = new ArrayList<>();

        for (int i = 0; i < nombrePacientes.length; i++) {
            list.add(new Paciente(nombrePacientes[i], apellidosPaciente[i], Integer.parseInt(edad[i]), historiaClinica[i]));//Integer.valueOf()
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
}