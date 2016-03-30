package com.example.davidsanta.mirestaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMamap=(Button) findViewById(R.id.btnMapa);
        btnMamap.setOnClickListener(this);

        Button btnCarta=(Button) findViewById(R.id.btnVerCarta);
        btnCarta.setOnClickListener(this);

        Button btnReserva=(Button) findViewById(R.id.btnReserva);
        btnReserva.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMapa:
                Intent intento = new Intent(getApplicationContext(),MapsActivity.class );
                startActivity(intento);
            break;
            case R.id.btnVerCarta:
                Intent intento1 = new Intent(getApplicationContext(),CartaActivity.class );
                startActivity(intento1);
            break;
            case R.id.btnReserva:
                Intent intento2 = new Intent(getApplicationContext(),LoginActivity.class );
                startActivity(intento2);
                break;
        }

    }

}
