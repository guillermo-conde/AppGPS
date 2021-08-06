package com.example.appgps;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnInicio;
    Intent cambioInterfaz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInicio = (Button) findViewById(R.id.btnEmpezar);

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambioInterfaz = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(cambioInterfaz);
            }
        });
    }
}