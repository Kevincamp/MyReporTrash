package com.domain.kevin.myreportrash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.domain.kevin.myreportrash.ReporTrash_clases.Usuario;
import com.domain.kevin.myreportrash.ReporTrash_db.DBHandler;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private TextView sinCuenta;
    private EditText txt_user;
    private EditText txt_pass;
    private String username;
    private String password;
    public Usuario usuario;
    public static String Usuario_OBJETO;

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        this.setTitle("ReporTrasH");

        final DBHandler db = new DBHandler(this);
        db.addUser(new Usuario("admin", "admin", "Kevin", "Campuzano", "kcampuza@hotmail.com"));

        txt_user = (EditText)findViewById(R.id.txt_username);
        txt_pass = (EditText)findViewById(R.id.txt_pass);
        txt_user.setText("admin");
        txt_pass.setText("admin");

        login = (Button)findViewById(R.id.btn_login);
        sinCuenta = (TextView)findViewById(R.id.lbl_sinCuenta);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                username = txt_user.getText().toString();
                password = txt_pass.getText().toString();
                try {
                    usuario = db.getUsuario(username);
                    if(usuario != null && usuario.getPassword().contentEquals(password)){
                        intent = new Intent(MainActivity.this, Profile.class);
                        intent.putExtra("username",usuario.getUsername().toString());
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Password incorrecto.",
                                Toast.LENGTH_SHORT).show();
                        txt_pass.clearComposingText();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Usuario no Existe.",
                            Toast.LENGTH_SHORT).show();
                };

            }
        });

        sinCuenta.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, RegistroUsuario.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
