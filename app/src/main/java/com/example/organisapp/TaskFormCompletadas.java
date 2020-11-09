package com.example.organisapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class TaskFormCompletadas extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_taskformcompletadas);
        final int position = (int) getIntent().getSerializableExtra("position");

        final EditText nombreActividad = (EditText) findViewById(R.id.nombreActividad3);
        final EditText descripcion = (EditText) findViewById(R.id.descripcion3);
        final Button fecha = (Button) findViewById(R.id.fechaFinalizacion3);
        final Button hora = (Button) findViewById(R.id.horaTask3);

        nombreActividad.setText(MainActivity.usuarioActual.getCompletadas().get(position).getNombreActividad());
        descripcion.setText(MainActivity.usuarioActual.getCompletadas().get(position).getDescripcion());
        fecha.setText(MainActivity.usuarioActual.getCompletadas().get(position).getFecha());
        hora.setText(MainActivity.usuarioActual.getCompletadas().get(position).getHora());

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Button fecha = (Button) findViewById(R.id.fechaFinalizacion3);
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                DatePickerDialog dp = new DatePickerDialog(TaskFormCompletadas.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                fecha.setText(day + "/" + month + "/" + year);
                            }
                        }, year, month, day);
                dp.show();;
            }
        });

        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Button hora = (Button) findViewById(R.id.horaTask3);
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog tm = new TimePickerDialog(
                        TaskFormCompletadas.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                if(minute < 10) {
                                    hora.setText(hourOfDay + ":0" + minute);
                                } else {
                                    hora.setText(hourOfDay + ":" + minute);
                                }
                            }
                        }, hour,minute,android.text.format.DateFormat.is24HourFormat(TaskFormCompletadas.this));
                tm.show();
            }
        });

        Button guardar = (Button) findViewById(R.id.modificarActividad2);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombreActividad.getText().toString().trim().equals("") || descripcion.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Rellenar todos los datos.", Toast.LENGTH_SHORT).show();
                } else {
                    Task newTask = new Task(
                            nombreActividad.getText().toString().trim(),
                            descripcion.getText().toString().trim(),
                            "Alta",
                            fecha.getText().toString().trim(),
                            hora.getText().toString().trim());
                    MainActivity.usuarioActual.getCompletadas().set(position, newTask);
                    openOrganis();
                }
            }
        });

        Button eliminar = findViewById(R.id.eliminarActividad2);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.usuarioActual.getCompletadas().remove(position);
                openOrganis();
            }
        });
    }

    public void openOrganis() {
        Intent intent = new Intent(this, Organis.class);
        startActivity(intent);
    }
}
