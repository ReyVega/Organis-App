package com.example.organisapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class TaskFormModificar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_taskformmodificar);

        EditText nombreActividad2 = (EditText) findViewById(R.id.nombreActividad2);
        EditText descripcion2 = (EditText) findViewById(R.id.descripcion2);
        Button fechaFinalizacion2 = (Button) findViewById(R.id.fechaFinalizacion2);
        Button horaTask2 = (Button) findViewById(R.id.horaTask2);
        Button modificarActividad = (Button) findViewById(R.id.modificarActividad);
        Button eliminarActividad = (Button) findViewById(R.id.eliminarActividad);


        if(((String) getIntent().getSerializableExtra("tipoTask")).equals("diaria")) {
            nombreActividad2.setText(MainActivity.usuarioActual.getDiarias().get((int) getIntent().getSerializableExtra("position")).getNombreActividad());
            descripcion2.setText(MainActivity.usuarioActual.getDiarias().get((int) getIntent().getSerializableExtra("position")).getDescripcion());
            fechaFinalizacion2.setText(MainActivity.usuarioActual.getDiarias().get((int) getIntent().getSerializableExtra("position")).getFecha());
            horaTask2.setText(MainActivity.usuarioActual.getDiarias().get((int) getIntent().getSerializableExtra("position")).getHora());
        } else if(((String) getIntent().getSerializableExtra("tipoTask")).equals("semanal")) {
            nombreActividad2.setText(MainActivity.usuarioActual.getSemanales().get((int) getIntent().getSerializableExtra("position")).getNombreActividad());
            descripcion2.setText(MainActivity.usuarioActual.getSemanales().get((int) getIntent().getSerializableExtra("position")).getDescripcion());
            fechaFinalizacion2.setText(MainActivity.usuarioActual.getSemanales().get((int) getIntent().getSerializableExtra("position")).getFecha());
            horaTask2.setText(MainActivity.usuarioActual.getSemanales().get((int) getIntent().getSerializableExtra("position")).getHora());
        } else if(((String) getIntent().getSerializableExtra("tipoTask")).equals("mensual")) {
            nombreActividad2.setText(MainActivity.usuarioActual.getMensuales().get((int) getIntent().getSerializableExtra("position")).getNombreActividad());
            descripcion2.setText(MainActivity.usuarioActual.getMensuales().get((int) getIntent().getSerializableExtra("position")).getDescripcion());
            fechaFinalizacion2.setText(MainActivity.usuarioActual.getMensuales().get((int) getIntent().getSerializableExtra("position")).getFecha());
            horaTask2.setText(MainActivity.usuarioActual.getMensuales().get((int) getIntent().getSerializableExtra("position")).getHora());
        }

        modificarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrganis();
            }
        });

        eliminarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrganis();
            }
        });

    }

    public void openOrganis() {
        Intent intent = new Intent(this, Organis.class);
        startActivity(intent);
    }
}
