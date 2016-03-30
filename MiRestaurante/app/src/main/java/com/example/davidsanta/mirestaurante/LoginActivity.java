package com.example.davidsanta.mirestaurante;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.davidsanta.mirestaurante.BD.ControladoraBD;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button btnRegistrarse=(Button) findViewById(R.id.btnRegistrarse);
        btnRegistrarse.setOnClickListener(this);

        Button btnReserva=(Button) findViewById(R.id.btnReser);
        btnReserva.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistrarse:
                Intent intento = new Intent(getApplicationContext(),RegistroActivity.class );
                startActivity(intento);
                break;
            case R.id.btnReser:
                String docCliente = ((EditText) findViewById(R.id.editText)).getText().toString();
                String password = ((EditText) findViewById(R.id.editText2)).getText().toString();

                ControladoraBD bd = new ControladoraBD(this);
                bd.openForRead();
                String passBD = bd.getPassword(docCliente);
                bd.close();

                if(password != null && password.equals(passBD)){
                    //Exito
                    Intent intento1 = new Intent(getApplicationContext(),ReservaActivity.class );
                    //le pasamos el documento al otro activity
                    intento1.putExtra("documento",docCliente );
                    startActivity(intento1);
                }else{
                    //Falla, popup
                    FragmentManager fragmentManager = getFragmentManager();
                    AlertaLogin dialogo = new AlertaLogin();
                    dialogo.show(fragmentManager, "tagAlertaL");
                }
                break;
        }
    }
}
