package com.example.davidsanta.mirestaurante;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidsanta.mirestaurante.BD.ControladoraBD;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        Button btnReg=(Button) findViewById(R.id.btnRegis);
        btnReg.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        EditText txtUser = (EditText) findViewById(R.id.editNombre);
        EditText txtDocumento = (EditText) findViewById(R.id.editDocumento);
        EditText txtPassword = (EditText) findViewById(R.id.editPassword);

        String nombre = txtUser.getText().toString();
        int documento = Integer.parseInt(txtDocumento.getText().toString());
        String password = txtPassword.getText().toString();

        Cliente c = new Cliente(nombre, documento, password);

        ControladoraBD bd = new ControladoraBD(this);
        bd.openForWrite();

        if(bd.clienteExiste(documento+"")){
            FragmentManager fragmentManager = getFragmentManager();
            AlertaUsuario dialogo = new AlertaUsuario();
            dialogo.show(fragmentManager, "tagAlertaU");
        }else{
            bd.insertCliente(c);
            Intent intento = new Intent(getApplicationContext(),LoginActivity.class );
            startActivity(intento);
            //toast
            Context context = getApplicationContext();
            CharSequence text = "Usuario Creado";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        bd.close();
    }
}
