package com.example.cardioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.cardioapp.bd.Message;
import android.widget.Button;
import android.widget.EditText;


public class CodigoRegistroActivity extends AppCompatActivity {
    Button btn;
    EditText codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_registro);
        btn = findViewById(R.id.btn_confirmar_codigo);
        codigo = findViewById(R.id.codigo_registro);
    //Comprobar codigo
        /*if(!SignInActivity.codigo.equals(codigo)){
            Message.message(this, "Código incorrecto");
        }else{
            Intent i = new Intent(CodigoRegistroActivity.this, LoginActivity.class);
            startActivity(i);
        }*/

    }
}