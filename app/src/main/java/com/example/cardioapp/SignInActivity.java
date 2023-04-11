package com.example.cardioapp;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Button btnSignin = findViewById(R.id.signin);//SIGN IN

        //Abrimos Actividad de Principal pulsando el boton de LogIn
        btnSignin.setOnClickListener(v -> {
            Intent i = new Intent(SignInActivity.this, PrincipalActivity.class);
            startActivity(i);
        });
    }
}