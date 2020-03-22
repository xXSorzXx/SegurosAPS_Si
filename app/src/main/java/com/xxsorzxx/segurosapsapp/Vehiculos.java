package com.xxsorzxx.segurosapsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.github.clans.fab.FloatingActionMenu;

public class Vehiculos extends AppCompatActivity {

    public EditText etAsegurado,etAño,etLinea,etMarca,etValor,etAsientos;
    public CheckBox CBAlquiler, CBDeducible, CBRodado;
    public RadioButton RBVh1,RBVh2,RBVh3,RBVh4,RBVh5;

    FloatingActionMenu famVh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculos);
        getSupportActionBar().hide();
        famVh=(FloatingActionMenu)findViewById(R.id.FamPrincipal);
        famVh.setClosedOnTouchOutside(true);



    }

    public void ClickVhAgro (View view) {

        //Se le asignan los valores a las variables para enviarlas al otro activity
        etAsegurado = (EditText)findViewById(R.id.etAsegurado);
        etAño = (EditText)findViewById(R.id.etAño);
        etLinea = (EditText)findViewById(R.id.etLinea);
        etMarca = (EditText)findViewById(R.id.etMarca);
        etValor = (EditText)findViewById(R.id.etValor);
        etAsientos = (EditText)findViewById(R.id.etAsientos);
        CBAlquiler = (CheckBox)findViewById(R.id.checkAlquiler);
        CBDeducible=(CheckBox)findViewById(R.id.checkDeducible);
        CBRodado=(CheckBox)findViewById(R.id.checkRodado);
        RBVh1=(RadioButton)findViewById(R.id.RVh1);
        RBVh2=(RadioButton)findViewById(R.id.RVh2);
        RBVh3=(RadioButton)findViewById(R.id.RVh3);
        RBVh4=(RadioButton)findViewById(R.id.RVh4);
        RBVh5=(RadioButton)findViewById(R.id.RVh5);


        String Auxiliar = "";
        String Alquiler = CondicionCheck(Auxiliar, CBAlquiler);
        String Deducible = CondicionCheck(Auxiliar, CBDeducible);
        String Rodado = CondicionCheck(Auxiliar, CBRodado);

        String Vehiculo="";
        if(RBVh1.isChecked() == true){
            Vehiculo = "Automóvil";
        } else if (RBVh2.isChecked() == true){
            Vehiculo = "Camioneta 4x4";
        } else if (RBVh3.isChecked() == true){
            Vehiculo = "Pick-Up 4x2";
        } else if (RBVh4.isChecked() == true){
            Vehiculo = "Pick-Up 4x4";
        } else if (RBVh5.isChecked() == true){
            Vehiculo = "Pick-Up 4x2";
        }




        //Se envia al otro activity
        Intent cotizar = new Intent(this,VhAgromercantil.class);

        //Se envian los datos almacenados en las variables al otro activiy
        cotizar.putExtra("NombreAsegurado", etAsegurado.getText().toString());
        cotizar.putExtra("Año", etAño.getText().toString());
        cotizar.putExtra("Linea", etLinea.getText().toString());
        cotizar.putExtra("Marca", etMarca.getText().toString());
        cotizar.putExtra("Valor", etValor.getText().toString());
        cotizar.putExtra("Asiento", etAsientos.getText().toString());
        cotizar.putExtra("Alquiler", Alquiler);
        cotizar.putExtra("Deducible", Deducible);
        cotizar.putExtra("Rodado", Rodado);
        cotizar.putExtra("Vehiculo", Vehiculo);
        startActivity(cotizar);

    }
    private String CondicionCheck(String arg1, CheckBox cb1){
        if (cb1.isChecked()){
            arg1 = "Si";

        } else {
            arg1 = "No";
        }
        return arg1;
    }


}
