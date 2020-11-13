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

    private EditText nombreActividad,
                     descripcion;
    private Button fecha,
                   hora,
                   guardar,
                   eliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_taskformcompletadas);
        final int position = (int) getIntent().getSerializableExtra("position");

        this.nombreActividad = (EditText) findViewById(R.id.nombreActividad3);
        this.descripcion = (EditText) findViewById(R.id.descripcion3);
        this.fecha = (Button) findViewById(R.id.fechaFinalizacion3);
        this.hora = (Button) findViewById(R.id.horaTask3);
        this.guardar = (Button) findViewById(R.id.modificarActividad2);
        this.eliminar = findViewById(R.id.eliminarActividad2);


        this.nombreActividad.setText(MainActivity.usuarioActual.getCompletadas().get(position).getNombreActividad());
        this.descripcion.setText(MainActivity.usuarioActual.getCompletadas().get(position).getDescripcion());
        this.fecha.setText(MainActivity.usuarioActual.getCompletadas().get(position).getFecha());
        this.hora.setText(MainActivity.usuarioActual.getCompletadas().get(position).getHora());

        this.fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        this.hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        this.guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombreActividad.getText().toString().trim().equals("") || descripcion.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Rellenar todos los datos.", Toast.LENGTH_SHORT).show();
                } else {
                    Task newTask = new Task(
                            nombreActividad.getText().toString().trim(),
                            descripcion.getText().toString().trim(),
                            fecha.getText().toString().trim(),
                            hora.getText().toString().trim());
                    MainActivity.usuarioActual.getCompletadas().set(position, newTask);
                    openOrganis();
                }
            }
        });

        this.eliminar.setOnClickListener(new View.OnClickListener() {
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
