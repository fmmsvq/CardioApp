package com.example.cardioapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    EditText Email, Pass, Pass2, DNI;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //DATOS DE ENTRADA
        Email= (EditText) findViewById(R.id.email);
        DNI= (EditText) findViewById(R.id.DNI);
        Pass= (EditText) findViewById(R.id.signup_pass1);
        Pass2 = (EditText) findViewById(R.id.signup_pass2);
        Button btnSignin = findViewById(R.id.signin);//SIGN IN

        mAuth = FirebaseAuth.getInstance();

        btnSignin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String dni = DNI.getText().toString().trim();
                String pass = Pass.getText().toString().trim();
                String pass2 = Pass2.getText().toString().trim();
                if(email.isEmpty()){
                    Toast.makeText(SignInActivity.this, "El email es obligatorio", Toast.LENGTH_SHORT);
                }else if(pass.isEmpty()){

                }else{
                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignInActivity.this, "Usuario registrado", Toast.LENGTH_SHORT);
                                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(SignInActivity.this, "Error registrando usuario" + task.getException().getMessage(), Toast.LENGTH_SHORT);
                            }
                        }
                    });
                }
             }
        });
        //loginRedirec.setOnClick?
    }
    public void onStart() {
        super.onStart();
        // Comprobar si el usuario está ya registrado y actualizarlo.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

}