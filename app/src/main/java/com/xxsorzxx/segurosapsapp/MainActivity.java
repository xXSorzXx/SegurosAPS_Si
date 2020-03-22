package com.xxsorzxx.segurosapsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }
    public void Vh (View view) {
        Intent siguiente = new Intent(this, Vehiculos.class);
        startActivity(siguiente);
    }

    public void Salir(View view){
        System.exit(0);
    }
}
