package com.example.davidsanta.mirestaurante;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.davidsanta.mirestaurante.BD.ControladoraBD;

import java.util.ArrayList;


public class CartaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);

        ListView list = (ListView) findViewById(R.id.listView);



        ControladoraBD productoData = new ControladoraBD(this);
        productoData.openForWrite();

        ArrayList<Producto> listProducto = productoData.getAllProducto();
        productoData.close();
        ArrayAdapter<Producto> adapter = new ArrayAdapter<Producto>(this, android.R.layout.simple_list_item_1, listProducto);
        list.setAdapter(adapter);
    }
}
