package com.example.cardioapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {//implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnLogin = findViewById(R.id.login);

        Button btnSignin = findViewById(R.id.signin);//SIGN IN

        //Intent intent = new Intent(MainActivity.this, LoginActivity.class);

        //Abrimos Actividad de SignIn pulsando el boton de SignIn
        btnSignin.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, SignInActivity.class);
            startActivity(i);
        });
        //Abrimos Actividad de Principal pulsando el boton de LogIn
        btnLogin.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, PrincipalActivity.class);
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
