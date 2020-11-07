package com.example.organisapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

                for(Usuario user : MainActivity.usuarios) {
                    if(user.getCorreo().toString().trim().equals(correo.getText().toString().trim())
                       && user.getContrasena().toString().equals(password.getText().toString())) {
                        MainActivity.usuarioActual = user;
                        openOrganis();
                        return;
                    }
                }
                Toast.makeText(getApplicationContext(), "Credenciales incorrectas.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openOrganis() {
        Intent intent = new Intent(this, Organis.class);
        startActivity(intent);
    }
}
