package com.example.cardioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;

public class ListaPacientesActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private MenuItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);

        drawerLayout = findViewById(R.id.drawerlayout2);
        findViewById(R.id.btnmenu2).setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        NavigationView navigationView = findViewById(R.id.menu2);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(ListaPacientesActivity.this);
        }
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