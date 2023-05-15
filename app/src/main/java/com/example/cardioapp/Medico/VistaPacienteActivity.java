package com.example.cardioapp.Medico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.example.cardioapp.R;
import com.google.android.material.navigation.NavigationView;

public class VistaPacienteActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_paciente);
        // Menu lateral
        //drawerLayout = findViewById(R.id.drawerlayout3);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerlayout3);
        findViewById(R.id.btnmenu).setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        NavigationView navigationView = findViewById(R.id.menu);
        navigationView.setItemIconTintList(null);
    }
}