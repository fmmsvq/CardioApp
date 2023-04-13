package com.example.cardioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cardioapp.ui.main.NotificacionesFragment;

public class NotificacionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, NotificacionesFragment.newInstance())
                    .commitNow();
        }
    }
}