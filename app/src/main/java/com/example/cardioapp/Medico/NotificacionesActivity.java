package com.example.cardioapp.Medico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cardioapp.R;

/**Esta Activity va a ser la lista de pacientes pero filtrada por orden de actualizacion de notificaciones*/
public class NotificacionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);
        if (savedInstanceState == null) {
            /*getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, NotificacionesFragment.newInstance())
                    .commitNow();*/
        }
    }
}