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
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class TaskForm extends AppCompatActivity {

    private EditText nombreActividad,
                     descripcion;

    private Button anadir,
                   fecha,
                   hora,
                   cancelar;

    private RadioButton diaria,
                        semanal,
                        mensual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_taskform);

        this.fecha = (Button) findViewById(R.id.fechaFinalizacion);
        this.hora = (Button) findViewById(R.id.horaTask);
        this.anadir = findViewById(R.id.anadirActividad);
        this.cancelar = findViewById(R.id.cancelar);
        this.nombreActividad = (EditText) findViewById(R.id.nombreActividad);
        this.descripcion = (EditText) findViewById(R.id.descripcion);
        this.diaria = (RadioButton) findViewById(R.id.radioDiaria);
        this.semanal = (RadioButton) findViewById(R.id.radioSemanal);
        this.mensual = (RadioButton) findViewById(R.id.radioMensual);
        this.hora = (Button) findViewById(R.id.horaTask);

        this.fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                DatePickerDialog dp = new DatePickerDialog(TaskForm.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                fecha.setText(day + "/" + month + "/" + year);
                            }
                        }, year, month, day);
                dp.show();
            }
        });

        this.hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog tm = new TimePickerDialog(
                        TaskForm.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                if(minute < 10) {
                                    hora.setText(hourOfDay + ":0" + minute);
                                } else {
                                    hora.setText(hourOfDay + ":" + minute);
                                }
                            }
                        }, hour,minute,android.text.format.DateFormat.is24HourFormat(TaskForm.this));
                tm.show();
            }
        });

        this.anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombreActividad.getText().toString().trim().equals("") ||
                   descripcion.getText().toString().trim().equals("") ||
                   fecha.getText().toString().trim().equals("Fecha de finalización") || hora.getText().toString().trim().equals("Hora")){
                    Toast.makeText(getApplicationContext(), "Rellenar todos los datos.", Toast.LENGTH_SHORT).show();
                } else if(!diaria.isChecked() && !semanal.isChecked() && !mensual.isChecked()){
                    Toast.makeText(getApplicationContext(), "Seleccionar diaria, semanal o mensual.", Toast.LENGTH_SHORT).show();
                } else {
                    Task newTask = new Task(
                            nombreActividad.getText().toString().trim(),
                            descripcion.getText().toString().trim(),
                            fecha.getText().toString().trim(),
                            hora.getText().toString().trim());
                    if(diaria.isChecked()){
                        MainActivity.usuarioActual.addTaskDiaria(newTask);
                    } else if(semanal.isChecked()){
                        MainActivity.usuarioActual.addTaskSemanal(newTask);
                    }else if(mensual.isChecked()){
                        MainActivity.usuarioActual.addTaskMensual(newTask);
                    }
                    openOrganis();
                }
            }
        });

        this.cancelar.setOnClickListener(new View.OnClickListener() {
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
