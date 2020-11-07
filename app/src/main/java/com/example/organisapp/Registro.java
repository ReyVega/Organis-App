package com.example.organisapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {

    private Button buttonOrganis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro);
        buttonOrganis = findViewById(R.id.organisRegistro);
        buttonOrganis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nombre = (EditText) findViewById(R.id.nombreRegistro);
                EditText apellidos = (EditText) findViewById(R.id.apellidosRegistro);
                EditText correo = (EditText) findViewById(R.id.correoRegistro);
                EditText password = (EditText) findViewById(R.id.passwordRegistro);
                EditText confirmacion = (EditText) findViewById(R.id.confirmacionRegistro);

                if(!(nombre.getText().toString().trim().equals("") ||
                        apellidos.getText().toString().trim().equals("") ||
                        correo.getText().toString().trim().equals("") ||
                        password.getText().toString().trim().equals("") ||
                        confirmacion.getText().toString().trim().equals(""))) {
                    if(password.getText().toString().equals(confirmacion.getText().toString())) {
                        openOrganis();
                    } else {
                        Toast.makeText(getApplicationContext(), "Confirmación de contraseña inválida", Toast.LENGTH_SHORT).show();
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
