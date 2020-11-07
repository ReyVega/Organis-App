package com.example.organisapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TaskForm extends AppCompatActivity {

    private Button anadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_taskform);

        this.anadir = findViewById(R.id.anadirActividad);
        this.anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nombreActividad = (EditText) findViewById(R.id.nombreActividad);
                EditText descripcion = (EditText) findViewById(R.id.descripcion);
                Switch diaria = (Switch) findViewById(R.id.switchDiaria);
                Switch semanal = (Switch) findViewById(R.id.switchSemanal);
                Switch mensual = (Switch) findViewById(R.id.switchMensual);
                EditText fecha = (EditText) findViewById(R.id.fechaFinalizacion);
                EditText hora = (EditText) findViewById(R.id.horaTask);

                openOrganis();
            }
        });
    }

    public void openOrganis() {
        Intent intent = new Intent(this, Organis.class);
        startActivity(intent);
    }
}
