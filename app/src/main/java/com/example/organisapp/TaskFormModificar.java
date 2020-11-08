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

        Button eliminarActividad = (Button) findViewById(R.id.eliminarActividad);

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
