package com.example.davidsanta.mirestaurante.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.davidsanta.mirestaurante.Cliente;
import com.example.davidsanta.mirestaurante.Producto;
import com.example.davidsanta.mirestaurante.Reserva;

import java.util.ArrayList;

/**
 * Created by David Santa on 23/03/2016.
 */
public class ControladoraBD {

    private static final int VERSION = 3;
    private static final String NOMBRE_BBDD = "restaurante.db";


    //Tabla Productos
    private static final String TABLA_PRODUCTOS = "tabla_productos";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "NAME";
    private static final int NUM_COL_NAME = 1;
    private static final String COL_DESC = "DESCRIPTION";
    private static final int NUM_COL_DESC = 2;
    private static final String COL_VAL = "VALOR";
    private static final int NUM_COL_VAL = 3;


    //Tabla clientes
    private static final String TABLA_USUARIOS = "tabla_usuario";
    private static final String COL_ID_U = "ID";
    private static final int NUM_COL_ID_U = 0;
    private static final String COL_NAME_U = "NOMBRE";
    private static final int NUM_COL_NAME_U = 1;
    private static final String COL_DOC_U = "DOCUMENTO";
    private static final int NUM_COL_DOC_U = 2;
    private static final String COL_CON_U = "CONTRASEÑA";
    private static final int NUM_COL_CON_U = 3;

    //Tabla reserva
    private static final String TABLA_RESERVA = "tabla_reserva";
    private static final String COL_ID_R = "ID";
    private static final int NUM_COL_ID_R = 0;
    private static final String COL_DOC_R = "DOCUMENTO";
    private static final int NUM_COL_DOC_R = 1;
    private static final String COL_CANT_R= "CANTIDAD";
    private static final int NUM_COL_CANT_R = 2;
    private static final String COL_FECHA_R= "FECHA";
    private static final int NUM_COL_FECHA_R = 3;


    private SQLiteDatabase bdd;

    private BDSQLite base;

    public ControladoraBD(Context context) {
        base = new BDSQLite(context, NOMBRE_BBDD, null, VERSION);
    }

    public void openForWrite() {
        bdd = base.getWritableDatabase();
    }

    public void openForRead() {
        bdd = base.getReadableDatabase();
    }

    public void close() {
        bdd.close();
    }

    public SQLiteDatabase getBdd() {
        return bdd;
    }

    public long insertProducto(Producto p) {
        ContentValues content = new ContentValues();
        content.put(COL_NAME, p.getNombreProducto());
        content.put(COL_DESC, p.getDescProducto());
        content.put(COL_VAL, p.getValor());
        return bdd.insert(TABLA_PRODUCTOS, null, content);
    }

    //Si es necesario?
    public int updateProducto(int id, Producto p) {
        ContentValues content = new ContentValues();
        content.put(COL_NAME, p.getNombreProducto());
        content.put(COL_DESC, p.getDescProducto());
        content.put(COL_VAL, p.getValor());
        return bdd.update(TABLA_PRODUCTOS, content, COL_ID + " = " + id, null);
    }

    //Si es necesario?
    public int removeProducto(String name) {
        return bdd.delete(TABLA_PRODUCTOS, COL_NAME + " = " + name, null);
    }

    //Si es necesario?
    public Producto getProducto(String name) {
        Cursor c = bdd.query(TABLA_PRODUCTOS, new String[] { COL_ID, COL_NAME,
                COL_DESC, COL_VAL }, COL_NAME + "=?", new String[] {name}, null, null, null);
        return null;
    }

    public ArrayList<Producto> getAllProducto() {
        Cursor c = bdd.query(TABLA_PRODUCTOS, new String[] { COL_ID, COL_NAME,
                COL_DESC, COL_VAL }, null, null, null, null, null);
        if (c.getCount() == 0) {
            System.out.println("No hay datos");
            c.close();
            return null;
        }
        ArrayList<Producto> listProducto = new ArrayList<Producto> ();
        while (c.moveToNext()) {
            Producto p = new Producto();
            p.setIdProducto(c.getInt(NUM_COL_ID));
            p.setNombreProducto(c.getString(NUM_COL_NAME));
            p.setDescProducto(c.getString(NUM_COL_DESC));
            p.setValor(c.getInt(NUM_COL_VAL));
            listProducto.add(p);
        }
        c.close();
        return listProducto;
    }

    /*
    public ArrayList<Cliente> getAllCliente() {
        Cursor c = bdd.query(TABLA_USUARIOS, new String[] { COL_ID_U, COL_NAME_U,
                COL_DOC_U, COL_CON_U}, null, null, null, null, null);
        if (c.getCount() == 0) {
            System.out.println("No hay datos");
            c.close();
            return null;
        }
        ArrayList<Cliente> listCliente = new ArrayList<Cliente> ();
        while (c.moveToNext()) {
            Cliente u = new Cliente();
            u.setIdCliente(c.getInt(NUM_COL_ID_U));
            u.setNombreCliente(c.getString(NUM_COL_NAME_U));
            u.setDocumentoCliente(c.getInt(NUM_COL_DOC_U));
            u.setPassword(c.getString(NUM_COL_CON_U));
            listCliente.add(u);
        }
        c.close();
        return listCliente;
    }
    */

    public long insertCliente(Cliente c) {
        ContentValues content = new ContentValues();
        content.put(COL_NAME_U, c.getNombreCliente());
        content.put(COL_DOC_U, c.getDocumentoCliente());
        content.put(COL_CON_U, c.getPassword());
        return bdd.insert(TABLA_USUARIOS, null, content);
    }

    public long insertReserva(Reserva r) {
        ContentValues content = new ContentValues();
        content.put(COL_DOC_R, r.getDocCliente());
        content.put(COL_CANT_R, r.getCantidad());
        content.put(COL_FECHA_R, r.getFecha());
        return bdd.insert(TABLA_RESERVA, null, content);
    }

    public String getPassword(String docCliente){
        Cursor c = bdd.query(TABLA_USUARIOS, new String[] { COL_ID_U,
                COL_CON_U}, COL_DOC_U + "=?", new String[] {docCliente}, null, null, null);
        if(c.getCount() < 1){
            //El usuario no existe
            return null;
        }else{
            c.moveToFirst();
            return c.getString(1);
        }
    }

    public boolean clienteExiste(String docCliente){
        Cursor c = bdd.query(TABLA_USUARIOS, new String[] { COL_ID_U,
                COL_CON_U}, COL_DOC_U + "=?", new String[] {docCliente}, null, null, null);
        if(c.getCount() < 1){
            //El usuario no existe
            return false;
        }else{
            return true;
        }
    }

    public ArrayList<Reserva> getAllReservas() {
        Cursor c = bdd.query(TABLA_RESERVA, new String[] { COL_ID_R, COL_DOC_R,
                COL_CANT_R, COL_FECHA_R}, null, null, null, null, null);
        if (c.getCount() == 0) {
            System.out.println("No hay datos");
            c.close();
            return null;
        }
        ArrayList<Reserva> listCliente = new ArrayList<Reserva> ();
        while (c.moveToNext()) {
            Reserva u = new Reserva();
            u.setIdReserva(c.getInt(NUM_COL_ID_R));
            u.setDocCliente(c.getInt(NUM_COL_DOC_R));
            u.setCantidad(c.getInt(NUM_COL_CANT_R));
            u.setFecha(c.getString(NUM_COL_FECHA_R));
            listCliente.add(u);
        }
        c.close();
        return listCliente;
    }
}
