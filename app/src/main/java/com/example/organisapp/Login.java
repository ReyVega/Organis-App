package com.example.organisapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private Button buttonOrganis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        buttonOrganis = findViewById(R.id.organisLogin);
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
