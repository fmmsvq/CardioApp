package com.example.cardioapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {//implements View.OnClickListener {

    Button botonSignin = findViewById(R.id.signin);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Intent intent = new Intent(MainActivity.this, LoginActivity.class);

        //Abrimos Actividad de SignIn pulsando el boton de SignIn
        botonSignin.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, SignInActivity.class);
            startActivity(i);
        });
    }
    /*
    @Override
    public void onClick(View v) {
        Intent i = new Intent(LoginActivity.this, SignInActivity.class);
        LoginActivity.this.startActivity(i);
        LoginActivity.this.finish();
    }*/
}
