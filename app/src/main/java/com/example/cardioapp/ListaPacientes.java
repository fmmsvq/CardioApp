package com.example.cardioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;

public class ListaPacientes extends AppCompatActivity {

    private Toolbar menuTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerlayout2);
        findViewById(R.id.btnmenu2).setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

    }


}