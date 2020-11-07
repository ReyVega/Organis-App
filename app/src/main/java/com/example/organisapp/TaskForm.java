package com.example.organisapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

                if(nombreActividad.getText().toString().trim().equals("") ||
                   descripcion.getText().toString().trim().equals("") ||
                   fecha.getText().toString().trim().equals("") || hora.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Rellenar todos los datos.", Toast.LENGTH_SHORT).show();
                }else if(!diaria.isChecked() && !semanal.isChecked() && !mensual.isChecked()){
                    Toast.makeText(getApplicationContext(), "Seleccionar diaria, semanal o mensual.", Toast.LENGTH_SHORT).show();
                }else{
                    Task newTask = new Task(
                            nombreActividad.getText().toString().trim(),
                            descripcion.getText().toString().trim(),
                            "Alta",
                            fecha.getText().toString().trim(),
                            hora.getText().toString().trim());
                    if(diaria.isChecked()){
                        MainActivity.usuarioActual.addTaskDiaria(newTask);
                    }else if(semanal.isChecked()){
                        MainActivity.usuarioActual.addTaskSemanal(newTask);
                    }else{
                        MainActivity.usuarioActual.addTaskMensual(newTask);
                    }
                    openOrganis();
                }
            }
        });

        Button cancelar = findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOrganis();
            }
        });
    }

    public void openOrganis() {
        Intent intent = new Intent(this, Organis.class);
        startActivity(intent);
    }
}
