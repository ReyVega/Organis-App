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

        final EditText nombreActividad2 = (EditText) findViewById(R.id.nombreActividad2);
        final EditText descripcion2 = (EditText) findViewById(R.id.descripcion2);
        final Button fechaFinalizacion2 = (Button) findViewById(R.id.fechaFinalizacion2);
        final Button horaTask2 = (Button) findViewById(R.id.horaTask2);
        Button modificarActividad = (Button) findViewById(R.id.modificarActividad);
        Button eliminarActividad = (Button) findViewById(R.id.eliminarActividad);

        fechaFinalizacion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Button fecha = (Button) findViewById(R.id.fechaFinalizacion);
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                DatePickerDialog dp = new DatePickerDialog(TaskFormModificar.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                fechaFinalizacion2.setText(day + "/" + month + "/" + year);
                            }
                        }, year, month, day);
                dp.show();;
            }
        });

        horaTask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Button hora = (Button) findViewById(R.id.horaTask);
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog tm = new TimePickerDialog(
                        TaskFormModificar.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                if(minute < 10) {
                                    horaTask2.setText(hourOfDay + ":0" + minute);
                                } else {
                                    horaTask2.setText(hourOfDay + ":" + minute);
                                }
                            }
                        }, hour,minute,android.text.format.DateFormat.is24HourFormat(TaskFormModificar.this));
                tm.show();
            }
        });

        final int position = (int) getIntent().getSerializableExtra("position");
        if(((String) getIntent().getSerializableExtra("tipoTask")).equals("diaria")) {
            nombreActividad2.setText(MainActivity.usuarioActual.getDiarias().get(position).getNombreActividad());
            descripcion2.setText(MainActivity.usuarioActual.getDiarias().get(position).getDescripcion());
            fechaFinalizacion2.setText(MainActivity.usuarioActual.getDiarias().get(position).getFecha());
            horaTask2.setText(MainActivity.usuarioActual.getDiarias().get(position).getHora());
        } else if(((String) getIntent().getSerializableExtra("tipoTask")).equals("semanal")) {
            nombreActividad2.setText(MainActivity.usuarioActual.getSemanales().get(position).getNombreActividad());
            descripcion2.setText(MainActivity.usuarioActual.getSemanales().get(position).getDescripcion());
            fechaFinalizacion2.setText(MainActivity.usuarioActual.getSemanales().get(position).getFecha());
            horaTask2.setText(MainActivity.usuarioActual.getSemanales().get(position).getHora());
        } else if(((String) getIntent().getSerializableExtra("tipoTask")).equals("mensual")) {
            nombreActividad2.setText(MainActivity.usuarioActual.getMensuales().get(position).getNombreActividad());
            descripcion2.setText(MainActivity.usuarioActual.getMensuales().get(position).getDescripcion());
            fechaFinalizacion2.setText(MainActivity.usuarioActual.getMensuales().get(position).getFecha());
            horaTask2.setText(MainActivity.usuarioActual.getMensuales().get(position).getHora());
        }

        modificarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task ts = new Task(nombreActividad2.getText().toString().trim(),descripcion2.getText().toString().trim(),"Alta",
                        fechaFinalizacion2.getText().toString().trim(),horaTask2.getText().toString().trim());
                if(((String) getIntent().getSerializableExtra("tipoTask")).equals("diaria")) {
                    MainActivity.usuarioActual.getDiarias().set(position, ts);
                } else if(((String) getIntent().getSerializableExtra("tipoTask")).equals("semanal")) {
                    MainActivity.usuarioActual.getSemanales().set(position, ts);
                } else if(((String) getIntent().getSerializableExtra("tipoTask")).equals("mensual")) {
                    MainActivity.usuarioActual.getMensuales().set(position, ts);
                }
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
