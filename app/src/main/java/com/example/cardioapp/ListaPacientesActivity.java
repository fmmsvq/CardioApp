package com.example.cardioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;


import com.google.android.material.navigation.NavigationView;

public class ListaPacientesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerlayout2);
        findViewById(R.id.btnmenu2).setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        NavigationView navigationView = findViewById(R.id.menu2);
        navigationView.setItemIconTintList(null);
    }


}