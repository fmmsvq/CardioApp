package com.example.cardioapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cardioapp.Medico.PrincipalActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    EditText email, pass;
    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnSignin = findViewById(R.id.signin);
        Button btnLogin = findViewById(R.id.login);
        FirebaseApp.initializeApp(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);

        //Abrimos Actividad de SignIn pulsando el boton de Registrarse
        btnSignin.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, SignInActivity.class);
            startActivity(i);
        });
        //Abrimos Actividad de Principal pulsando el boton de LogIn
        btnLogin.setOnClickListener(v -> {
            String emailString = email.getText().toString().trim();
            String passString = pass.getText().toString().trim();
            FirebaseUser currentUser = mAuth.getCurrentUser();

            if(emailString.isEmpty()){
                Toast.makeText(LoginActivity.this, "El email no puede estar vacio", Toast.LENGTH_SHORT).show();
            }else if(!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()){
                Toast.makeText(LoginActivity.this, "Debe poner un email valido", Toast.LENGTH_SHORT).show();
            }else if(passString.isEmpty()){
                Toast.makeText(LoginActivity.this, "La contrasena no puede estar vacia", Toast.LENGTH_SHORT).show();
            }else{
                loginUsuario(emailString, passString);
            }
            if(currentUser != null){
                currentUser.reload();
            }
            /*Intent i = new Intent(LoginActivity.this, PrincipalActivity.class);
            startActivity(i);*/
           // if(comprobarUser(email,pass)){


        });
    }

    private void loginUsuario(String emailString, String passString) {
        mAuth.signInWithEmailAndPassword(emailString,passString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Sesion iniciada con exito", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, "Error al iniciar sesion" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(LoginActivity.this, "Error al iniciar sesion" + e.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

}
