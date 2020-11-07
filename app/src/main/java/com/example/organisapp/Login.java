package com.example.organisapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    private Button buttonOrganis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        buttonOrganis = findViewById(R.id.organisLogin);
        buttonOrganis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText correo = (EditText) findViewById(R.id.correoLogin);
                EditText password = (EditText) findViewById(R.id.passwordLogin);

                if(!(correo.getText().toString().trim().equals("") || password.getText().toString().equals(""))) {
                    if(Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(
                            correo.getText().toString().trim()).find()) {
                        for(Usuario user : MainActivity.usuarios) {
                            if(user.getCorreo().toString().trim().equals(correo.getText().toString().trim())
                                    && user.getContrasena().toString().equals(password.getText().toString())) {
                                MainActivity.usuarioActual = user;
                                openOrganis();
                                return;
                            }
                        }
                        Toast.makeText(getApplicationContext(), "Credencial no encontrada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "El correo ingresado no es válido", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Rellenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openOrganis() {
        Intent intent = new Intent(this, Organis.class);
        startActivity(intent);
    }
}
