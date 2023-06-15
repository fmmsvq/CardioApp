package com.example.cardioapp;

import android.content.Intent;
import android.os.Bundle;
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


public class LoginActivity extends AppCompatActivity {//implements View.OnClickListener {
    EditText email, pass;// , updateold, updatenew, delete;
    //MedicoDbAdapter helper;
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
            String passString = email.getText().toString().trim();
            if(emailString.isEmpty()&& passString.isEmpty()){
                Toast.makeText(LoginActivity.this, "Debes poner un email", Toast.LENGTH_SHORT);
            }else{
                loginUsuario(emailString, passString);
            }
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if(currentUser != null){
                currentUser.reload();
            }

           // if(comprobarUser(email,pass)){
                Intent i = new Intent(LoginActivity.this, PrincipalActivity.class);
                startActivity(i);
            //}
        });
        //helper = new MedicoDbAdapter(this);
    }

    private void loginUsuario(String emailString, String passString) {
        mAuth.signInWithEmailAndPassword(emailString,passString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(LoginActivity.this, "Sesion iniciada con éxito", Toast.LENGTH_SHORT);
                }else{
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT);

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Error al iniciar sesion", Toast.LENGTH_SHORT);
            }
        });
    }

    /*private boolean comprobarUser(EditText email, EditText pass) {
        String emailString = email.getText().toString();
        String passString = pass.getText().toString();
        boolean res=false;
        //Comprobar que el user esta en BD
        if (helper.comprobarUserEnBD(emailString)==false){//Si user no está en BD
            Message.message(getApplicationContext(),"El usuario "+email.getText().toString()+" no existe");
        }else if (helper.comprobarPassEnBD(emailString, passString)==false) {//Si pass no coicide
            Message.message(getApplicationContext(), "Contraseña incorrecta");
        }else if(emailString.isEmpty() || passString.isEmpty()){
            Message.message(getApplicationContext(),"Añada su correo y contraseña");
        }else res=true;
        return res;
    }*/

}
