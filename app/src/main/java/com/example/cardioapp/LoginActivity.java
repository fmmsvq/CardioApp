package com.example.cardioapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cardioapp.Medico.PrincipalActivity;
import com.example.cardioapp.bd.Message;
import com.example.cardioapp.bd.myDbAdapter;

public class LoginActivity extends AppCompatActivity {//implements View.OnClickListener {
    EditText email, pass;// , updateold, updatenew, delete;
    myDbAdapter helper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnSignin = findViewById(R.id.signin);
        Button btnLogin = findViewById(R.id.login);

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);

        //Abrimos Actividad de SignIn pulsando el boton de Registrarse
        btnSignin.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, SignInActivity.class);
            startActivity(i);
        });
        //Abrimos Actividad de Principal pulsando el boton de LogIn
        btnLogin.setOnClickListener(v -> {
            if(comprobarUser(email,pass)){
                Intent i = new Intent(LoginActivity.this, PrincipalActivity.class);
                startActivity(i);
            }
        });
        helper = new myDbAdapter(this);
    }

    private boolean comprobarUser(EditText email, EditText pass) {
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
    }

}
