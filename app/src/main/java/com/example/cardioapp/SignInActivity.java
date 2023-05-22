package com.example.cardioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;

import com.example.cardioapp.bd.Message;
import com.example.cardioapp.bd.myDbAdapter;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {
    EditText Email, Pass, Pass2, DNI;
    myDbAdapter helper;
    //static String codigo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Button btnSignin = findViewById(R.id.signin);//SIGN IN

        //DATOS DE ENTRADA
        Email= (EditText) findViewById(R.id.email);
        DNI= (EditText) findViewById(R.id.DNI);
        /*CentroMedico= (EditText) findViewById(R.id.centromedico);
        AnioNacimiento= (EditText) findViewById(R.id.anionacimiento);*/
        Pass= (EditText) findViewById(R.id.signup_pass1);
        Pass2 = (EditText) findViewById(R.id.signup_pass2);

        helper = new myDbAdapter(this);
        //Lanzamos el método addUser que hace una inserción en BD, pulsando el boton de LogIn
        btnSignin.setOnClickListener(v -> {
            addUser(findViewById(R.id.drawerlayout3));
        });
    }
    public void addUser(View view) {
        String email = Email.getText().toString();
        String pass = Pass.getText().toString();
        String pass2 = Pass2.getText().toString();
        String dni = DNI.getText().toString();
       /* String nombre = Nombre.getText().toString();
        String centromedico = CentroMedico.getText().toString();
        String anionacimiento = AnioNacimiento.getText().toString();*/

        if (!validarEmail(email)) {
            Message.message(getApplicationContext(), "Email no válido");
        } else if (email.isEmpty() || pass.isEmpty()) {
            Message.message(getApplicationContext(), "Añada su correo y contraseña");
        } else if (!pass.equals(pass2)) {
            Message.message(getApplicationContext(), "Las contraseñas no coinciden");
        }else{
            long id = helper.insertData(email,pass, dni);
            if(id<=0){//Error code id=-1
                Message.message(getApplicationContext(),"Error creando usuario");
            } else {//EMAIL DE CONFIRMACIÓN
                //codigo = generarCodigo();
                Message.message(getApplicationContext(),"Hemos enviado un código de confirmación a "+email+" para confirmar su identidad.");//Hemos enviado un código de confirmación a su correo,
                alerta();
            //EMAIL DE CONFIRMACIÓN DE CORREO
                /*Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",email, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "¡Enhorabuena! Estás a sólo un paso de registrarte en CardioApp del Hospital Virgen del Rocío.\nAñade este código en la App: "+codigo+"\n para registrarte correctamente.");//generar codigo
                try {
                    this.startActivity(emailIntent);
                } catch(ActivityNotFoundException ex) {
                    Message.message(this, "Error enviando el correo a "+email);
                }
                Intent i = new Intent(SignInActivity.this, CodigoRegistroActivity.class);
                startActivity(i);*/
            }
        }
    }


/**PopUp para avisar de que se ha creado el user y pasamos a la pantalla del código de confirmación del correo */
    public void alerta(){
        // 1. Instanciar AlertDialog.Builder con el constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 2. Encadenamos varios métodos para establecer las características
        builder.setMessage(R.string.mensaje_popup_usuariocreado).setTitle(R.string.titulo_popup_usuariocreado);
        // 3. Añadimos los botones
        builder.setPositiveButton(R.string.ok_popup_usuariocreado, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User tab OK
                startActivity(new Intent(SignInActivity.this, LoginActivity.class));
            }
        });
        // 4. Obtener el AlertDialog del Builder mediante el método create()
        AlertDialog dialog = builder.create();
    }

    /*private String generarCodigo() {
        Random rnd = new Random();
        int num = ThreadLocalRandom.current().nextInt(+ 1);

        for (int i = 0; i < 7; i++) {
            if (i < 4) {
                num += rnd.nextInt(8);
            } else {
                num += (char) (rnd.nextInt(91) + 65);
            }
        }
        return String.valueOf(num);
    }*/

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    public void viewdata(View view) {
        String data = helper.getData();
        Message.message(this,data);
    }
}