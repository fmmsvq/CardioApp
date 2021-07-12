package com.example.cardioapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cardioapp.LoginActivity;
/** Un Intent es un objeto que proporciona vinculación en tiempo de ejecución entre componentes separados, como dos actividades. El Intent representa la intención que tiene una app de realizar una tarea. Puedes usar intents para varias tareas; pero, en esta lección, tu intent inicia otra actividad.
 * */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Arranca la siguiente actvidad pasados unos segundos
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent mInHome = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(mInHome);
                MainActivity.this.finish();
            }
        }, 3000);
    }
}