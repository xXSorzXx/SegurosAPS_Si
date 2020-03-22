package com.xxsorzxx.segurosapsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import javax.xml.validation.Validator;

public class VhAgromercantil extends AppCompatActivity {

    TextView tvNombreG,tvMarcaG,tvLineaG,tvAñoG,tvValorG,tvAsientosG;
    TextView tvAlquilerG, tvRodadoG, tvDeducibleG, tvTipoG;
    //Variables de las coberturas
    TextView tvVuelcosSumaG,tvVuelcosDeducibleG,tvRoboSumaG, tvRoboDeducibleG, tvOcupantesGmG,tvOcupantesApG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vh_agromercantil);
        getSupportActionBar().hide();
        //se enlaza el TextView con la variable
        tvNombreG = (TextView) findViewById(R.id.tvNombre);
        tvMarcaG = (TextView) findViewById(R.id.tvMarca);
        tvLineaG = (TextView) findViewById(R.id.tvLinea);
        tvAñoG = (TextView) findViewById(R.id.tvAño);
        tvValorG = (TextView) findViewById(R.id.tvValor);
        tvAsientosG = (TextView) findViewById(R.id.tvAsientos);
        tvAlquilerG = (TextView) findViewById(R.id.tvAlquiler);
        tvRodadoG = (TextView) findViewById(R.id.tvRodado);
        tvDeducibleG = (TextView) findViewById(R.id.tvDeducible);
        tvTipoG = (TextView) findViewById(R.id.tvTipo);


//Se le asigna el nombre al texto
        String tvNombre = getIntent().getStringExtra("NombreAsegurado");
        tvNombreG.setText(tvNombre);
        String tvMarca = getIntent().getStringExtra("Marca");
        tvMarcaG.setText(tvMarca);
        String tvLinea = getIntent().getStringExtra("Linea");
        tvLineaG.setText(tvLinea);
        String tvAño = getIntent().getStringExtra("Año");
        tvAñoG.setText(tvAño);
        String tvValor = getIntent().getStringExtra("Valor");
        if (tvValor.equals("")){
            tvValor = "1";
        }
        tvValorG.setText("Q." + tvValor);

        String tvAsiento = getIntent().getStringExtra("Asiento");
        if (tvAsiento.equals("")){
            tvAsiento = "1";
        }
            SiEsCero(tvAsiento);
        tvAsientosG.setText(tvAsiento);
        String tvAlquiler = getIntent().getStringExtra("Alquiler");
        tvAlquilerG.setText(tvAlquiler);
        String tvRodado = getIntent().getStringExtra("Rodado");
        tvRodadoG.setText(tvRodado);
        String tvDeducible = getIntent().getStringExtra("Deducible");
        tvDeducibleG.setText(tvDeducible);
        String tvTipo = getIntent().getStringExtra("Vehiculo");
        tvTipoG.setText(tvTipo);



                                //Se le asignan las variables a los TextView de las Coberturas
                                     //**************Sección 1 *************************
        //Colisiones Suma Asegurada
        tvVuelcosSumaG = (TextView) findViewById(R.id.tvVuelcosSuma);
        //Colsiones Deducible
        tvVuelcosDeducibleG = (TextView) findViewById(R.id.tvVuelcosDeducible);
        //Robo Suma Asegurada
        tvRoboSumaG = (TextView) findViewById(R.id.tvRoboSuma);
        //Robo Deducible
        tvRoboDeducibleG = (TextView)findViewById(R.id.tvRoboDeducible);
        tvOcupantesGmG = (TextView)findViewById(R.id.tvOcupantesSumaGm);
        tvOcupantesApG = (TextView)findViewById(R.id.tvOcupantesSumaAp);

                                    //Se le asignan valores a los TextView coberturas
                                     //****************Sección 1 *******************
        //Colisiones Suma Asegurada
        tvVuelcosSumaG.setText("Q." + tvValor);
        //Colisiones Deducible - LLamado al método Deducible de colisión
        DeducibleColisión(tvTipo, tvDeducible, tvValor);
        //Robo Suma Asegurada
        tvRoboSumaG.setText("Q." + tvValor);
        //Robo Deducible - LLamado al método de Deducible de Robo
        DeducibleRobo(tvTipo, tvDeducible, tvValor);
        //Gastos médicos y Ap a ocupantes llamado a método
        SeccionLesionesAOcupantes(tvAsiento);
    }


    //*****************0 Deducible Condicionante********************
        public void DeducibleColisión(String tvTipo, String tvDeducible, String tvValor){
            if (tvDeducible.equals("No")) {
                Double Porcentaje = 0.02;
                if (tvTipo.equals("Automóvil") || tvTipo.equals("Camioneta 4x2") || tvTipo.equals("Camioneta 4x4")) {
                    Porcentaje = 0.02;
                } else if (tvTipo.equals("Pick-Up 4x2") || tvTipo.equals("Pick-Up 4x4")) {
                    Porcentaje = 0.05;
                }
                Double Deducible;
                Double Valor = Double.parseDouble(tvValor);

                if ((Valor * Porcentaje) > 2000) {
                    Deducible = Valor * Porcentaje;
                } else {
                    Deducible = 2000.00;
                }
                String ResultadoDeducible = String.valueOf(Deducible);
                tvVuelcosDeducibleG.setText("Q." + ResultadoDeducible);
            }else {
                tvVuelcosDeducibleG.setText("0%");
            }
        }

    public void DeducibleRobo(String tvTipoRobo, String tvDeducibleRobo, String tvValorRobo){
        if (tvDeducibleRobo.equals("No")) {
            Double PorcentajeRobo = 0.03;
            if (tvTipoRobo.equals("Automóvil") || tvTipoRobo.equals("Camioneta 4x2") || tvTipoRobo.equals("Camioneta 4x4")) {
                PorcentajeRobo = 0.03;
            } else if (tvTipoRobo.equals("Pick-Up 4x2") || tvTipoRobo.equals("Pick-Up 4x4")) {
                PorcentajeRobo = 0.10;
            }
            Double DeducibleRobo;
            Double ValorRobo = Double.parseDouble(tvValorRobo);

            if ((ValorRobo * PorcentajeRobo) > 2000) {
                DeducibleRobo = ValorRobo * PorcentajeRobo;
            } else {
                DeducibleRobo = 2000.00;
            }
            String ResultadoDeducibleRobo = String.valueOf(DeducibleRobo);
            tvRoboDeducibleG.setText("Q." + ResultadoDeducibleRobo);
        }else {
            tvRoboDeducibleG.setText("0%");
        }
    }

    public void SeccionLesionesAOcupantes (String Asientos){
        Double valor = 100000.0;
        Double ocupantes = Double.parseDouble(Asientos);
        Double GmyApOcupantes = valor/ocupantes;
        String OcupantesProtegidos = String.valueOf(GmyApOcupantes);
        tvOcupantesGmG.setText(OcupantesProtegidos);
        tvOcupantesApG.setText(OcupantesProtegidos);
    }

    public void SiEsCero(String Cero){

    }


}
