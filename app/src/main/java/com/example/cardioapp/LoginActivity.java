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
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        Button btnSignin = findViewById(R.id.signin);//SIGN IN
        Button btnLogin = findViewById(R.id.login);
        //Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        helper = new myDbAdapter(this);
        //Abrimos Actividad de SignIn pulsando el boton de SignIn
        btnSignin.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, SignInActivity.class);
            startActivity(i);
        });
        //Abrimos Actividad de Principal pulsando el boton de LogIn
        btnLogin.setOnClickListener(v -> {
            if(comprobarUserBD(email,pass)){
                Intent i = new Intent(LoginActivity.this, PrincipalActivity.class);
                startActivity(i);
            }/*else{
                Message.message(getApplicationContext(), "El usuario "+email.getText().toString()+" no existe");
            }*/
        });
    }

    private boolean comprobarUserBD(EditText email, EditText pass) {
        String emailString = email.getText().toString();
        String passString = pass.getText().toString();
        boolean res=false;
        //Comprobar que el user esta en BD
        if (helper.comprobarUserEnBD(emailString)==false){//Si user no está en BD
            Message.message(getApplicationContext(),"Usuario no válido");
        }else if (helper.comprobarPassEnBD(emailString, passString)==false) {//Si user no está en BD
            Message.message(getApplicationContext(), "Contraseña incorrecta");
        }else if(emailString.isEmpty() || passString.isEmpty()){
            Message.message(getApplicationContext(),"Añada su correo y contraseña");
        }else res=true;
        return res;
    }
    /*
    @Override
    public void onClick(View v) {
        Intent i = new Intent(LoginActivity.this, SignInActivity.class);
        LoginActivity.this.startActivity(i);
        LoginActivity.this.finish();
    }*/
}
