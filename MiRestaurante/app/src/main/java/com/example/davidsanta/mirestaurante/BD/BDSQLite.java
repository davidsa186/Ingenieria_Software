package com.example.davidsanta.mirestaurante.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by David Santa on 23/03/2016.
 */
public class BDSQLite extends SQLiteOpenHelper{

        //Aqui se crea la tabla productos
        private static final String TABLA_PRODUCTOS = "tabla_productos";
        private static final String COL_ID = "ID";
        private static final String COL_NAME = "NAME";
        private static final String COL_DESC = "DESCRIPTION";
        private static final String COL_VAL = "VALOR";

        private static final String CREATE_TABLE_PRODUCTOS = "CREATE TABLE " + TABLA_PRODUCTOS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT NOT NULL, " +
                COL_DESC + " TEXT NOT NULL, " +
                COL_VAL + " TEXT NOT NULL);";


        //Aqui se crea la tabla usuario
        private static final String TABLA_USUARIOS = "tabla_usuario";
        private static final String COL_ID_U = "ID";
        private static final String COL_NAME_U = "NOMBRE";
        private static final String COL_DOC_U= "DOCUMENTO";
        private static final String COL_CONTRA_U = "CONTRASEÑA";

        private static final String CREATE_TABLE_USUARIOS = "CREATE TABLE " + TABLA_USUARIOS + " (" +
                COL_ID_U + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME_U + " TEXT NOT NULL, " +
                COL_DOC_U + " INTEGER NOT NULL, " +
                COL_CONTRA_U + " TEXT NOT NULL);";

        //Aqui se crea la tabla reserva
        private static final String TABLA_RESERVA = "tabla_reserva";
        private static final String COL_ID_R = "ID";
        private static final String COL_DOC_R = "DOCUMENTO";
        private static final String COL_CANT_R= "CANTIDAD";
        private static final String COL_FECHA_R= "FECHA";

        private static final String CREATE_TABLE_RESERVA = "CREATE TABLE " + TABLA_RESERVA + " (" +
                COL_ID_R + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DOC_R + " INTEGER NOT NULL, " +
                COL_CANT_R + " INTEGER NOT NULL, " + COL_FECHA_R + " TEXT NOT NULL);";


        public BDSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super (context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

                db.execSQL(CREATE_TABLE_PRODUCTOS);
                db.execSQL("INSERT INTO tabla_productos (NAME, DESCRIPTION, VALOR) VALUES ('Pollo', 'Delicioso', 10000);");
                db.execSQL("INSERT INTO tabla_productos (NAME, DESCRIPTION, VALOR) VALUES ('Carne', 'Deliciosa', 11000);");
                db.execSQL("INSERT INTO tabla_productos (NAME, DESCRIPTION, VALOR) VALUES ('Coca-Cola', 'Refrescante', 2000);");
                db.execSQL(CREATE_TABLE_USUARIOS);
                db.execSQL(CREATE_TABLE_RESERVA);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //En este método, debe gestionar los incrementos de versión de su base de datos
            db.execSQL("DROP TABLE " + TABLA_PRODUCTOS);
            db.execSQL("DROP TABLE " + TABLA_USUARIOS);
            db.execSQL("DROP TABLE " + TABLA_RESERVA);
            onCreate(db);
        }
}
