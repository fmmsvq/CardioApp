package com.example.cardioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class VistaPacienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_paciente);
        // Menu lateral
        final DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
        findViewById(R.id.btnmenu).setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        NavigationView navigationView = findViewById(R.id.menu);
        navigationView.setItemIconTintList(null);
    }
}