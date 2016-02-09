package com.domain.kevin.myreportrash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.domain.kevin.myreportrash.ReporTrash_clases.Usuario;
import com.domain.kevin.myreportrash.ReporTrash_db.DBHandler;

public class RegistroUsuario extends AppCompatActivity {

    private Button registrar;
    private EditText nombre;
    private EditText apellidos;
    private EditText email;
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private Intent intent;
    public Usuario usuario;
    public static String Usuario_OBJETO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrouser_layout);

        this.setTitle("Nuevo Usuario");

        final DBHandler db = new DBHandler(this);
        registrar = (Button)findViewById(R.id.btn_Registrar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = (EditText) findViewById(R.id.txt_pass1);
                confirmPassword = (EditText) findViewById(R.id.txt_pass2);

                if (password.getText().toString().contentEquals(confirmPassword.getText().toString())) {
                    nombre = (EditText) findViewById(R.id.txt_Nombre);
                    apellidos = (EditText) findViewById(R.id.txt_Apellido);
                    email = (EditText) findViewById(R.id.txt_CorreoElectronico);
                    username = (EditText) findViewById(R.id.txt_username);

                    db.addUser(new Usuario(username.getText().toString(), password.getText().toString(), nombre.getText().toString(), apellidos.getText().toString(), email.getText().toString()));
                    usuario = db.getUsuario(username.getText().toString());
                    intent = new Intent(RegistroUsuario.this, Profile.class);
                    intent.putExtra(Usuario_OBJETO, usuario);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Password ingresado incorrecto.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        intent = new Intent(RegistroUsuario.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
