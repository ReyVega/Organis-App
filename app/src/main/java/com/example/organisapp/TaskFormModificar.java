package com.example.organisapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

    private EditText nombreActividad,
                     descripcion;
    private Button fechaFinalizacion,
                   horaTask,
                   modificarActividad,
                   eliminarActividad;
    private CheckBox completado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_taskformmodificar);

        final int position = (int) getIntent().getSerializableExtra("position");
        this.nombreActividad = (EditText) findViewById(R.id.nombreActividad2);
        this.descripcion = (EditText) findViewById(R.id.descripcion2);
        this.fechaFinalizacion = (Button) findViewById(R.id.fechaFinalizacion2);
        this.horaTask = (Button) findViewById(R.id.horaTask2);
        this.modificarActividad = (Button) findViewById(R.id.modificarActividad);
        this.eliminarActividad = (Button) findViewById(R.id.eliminarActividad);
        this.completado = (CheckBox) findViewById(R.id.taskCompletada2);

        this.fechaFinalizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                DatePickerDialog dp = new DatePickerDialog(TaskFormModificar.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                fechaFinalizacion.setText(day + "/" + month + "/" + year);
                            }
                        }, year, month, day);
                dp.show();;
            }
        });

        this.horaTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog tm = new TimePickerDialog(
                        TaskFormModificar.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                if(minute < 10) {
                                    horaTask.setText(hourOfDay + ":0" + minute);
                                } else {
                                    horaTask.setText(hourOfDay + ":" + minute);
                                }
                            }
                        }, hour,minute,android.text.format.DateFormat.is24HourFormat(TaskFormModificar.this));
                tm.show();
            }
        });

        if(((String) getIntent().getSerializableExtra("tipoTask")).equals("diaria")) {
            this.nombreActividad.setText(MainActivity.usuarioActual.getDiarias().get(position).getNombreActividad());
            this.descripcion.setText(MainActivity.usuarioActual.getDiarias().get(position).getDescripcion());
            this.fechaFinalizacion.setText(MainActivity.usuarioActual.getDiarias().get(position).getFecha());
            this.horaTask.setText(MainActivity.usuarioActual.getDiarias().get(position).getHora());
        } else if(((String) getIntent().getSerializableExtra("tipoTask")).equals("semanal")) {
            this.nombreActividad.setText(MainActivity.usuarioActual.getSemanales().get(position).getNombreActividad());
            this.descripcion.setText(MainActivity.usuarioActual.getSemanales().get(position).getDescripcion());
            this.fechaFinalizacion.setText(MainActivity.usuarioActual.getSemanales().get(position).getFecha());
            this.horaTask.setText(MainActivity.usuarioActual.getSemanales().get(position).getHora());
        } else if(((String) getIntent().getSerializableExtra("tipoTask")).equals("mensual")) {
            this.nombreActividad.setText(MainActivity.usuarioActual.getMensuales().get(position).getNombreActividad());
            this.descripcion.setText(MainActivity.usuarioActual.getMensuales().get(position).getDescripcion());
            this.fechaFinalizacion.setText(MainActivity.usuarioActual.getMensuales().get(position).getFecha());
            this.horaTask.setText(MainActivity.usuarioActual.getMensuales().get(position).getHora());
        }

        this.modificarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombreActividad.getText().toString().trim().equals("") || descripcion.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Rellenar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    Task ts = new Task(nombreActividad.getText().toString().trim(),descripcion.getText().toString().trim(),
                            fechaFinalizacion.getText().toString().trim(),horaTask.getText().toString().trim());
                    if(((String) getIntent().getSerializableExtra("tipoTask")).equals("diaria")) {
                        if(completado.isChecked()) {
                            MainActivity.usuarioActual.getCompletadas().add(ts);
                            MainActivity.usuarioActual.getDiarias().remove(position);
                        } else {
                            MainActivity.usuarioActual.getDiarias().set(position, ts);
                        }
                    } else if(((String) getIntent().getSerializableExtra("tipoTask")).equals("semanal")) {
                        if(completado.isChecked()) {
                            MainActivity.usuarioActual.getCompletadas().add(ts);
                            MainActivity.usuarioActual.getSemanales().remove(position);
                        } else {
                            MainActivity.usuarioActual.getSemanales().set(position, ts);
                        }
                    } else if(((String) getIntent().getSerializableExtra("tipoTask")).equals("mensual")) {
                        if(completado.isChecked()) {
                            MainActivity.usuarioActual.getCompletadas().add(ts);
                            MainActivity.usuarioActual.getMensuales().remove(position);
                        } else {
                            MainActivity.usuarioActual.getMensuales().set(position, ts);
                        }
                    }
                    openOrganis();
                }
            }
        });

        this.eliminarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((String) getIntent().getSerializableExtra("tipoTask")).equals("diaria")) {
                    MainActivity.usuarioActual.getDiarias().remove(position);
                } else if(((String) getIntent().getSerializableExtra("tipoTask")).equals("semanal")) {
                    MainActivity.usuarioActual.getSemanales().remove(position);
                } else if(((String) getIntent().getSerializableExtra("tipoTask")).equals("mensual")) {
                    MainActivity.usuarioActual.getMensuales().remove(position);
                }
                openOrganis();
            }
        });

    }

    public void openOrganis() {
        Intent intent = new Intent(this, Organis.class);
        startActivity(intent);
    }
}
