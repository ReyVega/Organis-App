package com.example.organisapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
                openOrganis();
            }
        });
    }

    public void openOrganis() {
        Intent intent = new Intent(this, Organis.class);
        startActivity(intent);
    }
}
