package com.example.organisapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

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
                    if(Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(
                                       correo.getText().toString().trim()).find()) {
                        if(password.getText().toString().equals(confirmacion.getText().toString())) {
                            if(Pattern.compile("[0-9]").matcher(password.getText().toString()).find() &&
                                    Pattern.compile("[a-zA-Z]").matcher(password.getText().toString()).find() &&
                                    password.getText().toString().length() > 6) {
                                boolean newEmail = true;
                                for(Usuario u : MainActivity.usuarios){
                                    if(u.getCorreo().equals(correo.getText().toString().trim())){
                                        newEmail = false;
                                        break;
                                    }
                                }
                                if(newEmail){
                                    MainActivity.usuarioActual = new Usuario(
                                            MainActivity.ID++,
                                            nombre.getText().toString().trim(),
                                            apellidos.getText().toString().trim(),
                                            correo.getText().toString().trim(),
                                            password.getText().toString().trim()
                                    );
                                    MainActivity.usuarios.add(MainActivity.usuarioActual);
                                    openOrganis();
                                } else {
                                    Toast.makeText(getApplicationContext(), "El correo ingresado fue registrado previamente", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "La contraseña debe contener al menos 7 carácteres y al menos un numero o letra", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Confirmación de contraseña inválida", Toast.LENGTH_SHORT).show();
                        }
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
