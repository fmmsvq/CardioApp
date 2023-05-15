package com.example.cardioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;

import com.example.cardioapp.bd.Message;
import com.example.cardioapp.bd.myDbAdapter;

import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {
    EditText Email, Pass, Pass2;// , updateold, updatenew, delete;
    myDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Button btnSignin = findViewById(R.id.signin);//SIGN IN

        //Lanzamos el método addUser que hace una inserción en BD, pulsando el boton de LogIn
        btnSignin.setOnClickListener(v -> {
            addUser(findViewById(R.id.drawerlayout3));

        });
        Email= (EditText) findViewById(R.id.email);
        Pass= (EditText) findViewById(R.id.password);
        Pass2 = (EditText) findViewById(R.id.password2);

        helper = new myDbAdapter(this);
    }
    public void addUser(View view) {
        String email = Email.getText().toString();
        String pass = Pass.getText().toString();
        String pass2 = Pass2.getText().toString();

        if (!validarEmail(email)) {
            Message.message(getApplicationContext(), "Email no válido");
        } else if (email.isEmpty() || pass.isEmpty()) {
            Message.message(getApplicationContext(), "Añada su correo y contraseña");
        } else if (!pass.equals(pass2)) {
            Message.message(getApplicationContext(), "Las contraseñas no coinciden");
        }else{
            long id = helper.insertData(email,pass);
            if(id<=0){
                Message.message(getApplicationContext(),"Error creando usuario");
            } else {
                Message.message(getApplicationContext(),"Usuario creado correctamente");//Hemos enviado un código de confirmación a su correo,
                Intent i = new Intent(SignInActivity.this, LoginActivity.class);
                startActivity(i);
            }
        }
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    public void viewdata(View view) {
        String data = helper.getData();
        Message.message(this,data);
    }
}