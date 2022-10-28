package com.yq.mascotaavanzado.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yq.mascotaavanzado.pojo.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    Context context;

    public BaseDatos(Context context) {
        super(context,ConstantesBaseDatos.DATABASE_NAME,null,ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String queryCrearTablaMascotas = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" +
                ConstantesBaseDatos.TABLE_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_IDENTIFICACION + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_RAZA + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_FOTO + " INTEGER, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_RATING + " INTEGER" + ")";


        String queryCrearTablaFavoritos = "CREATE TABLE " + ConstantesBaseDatos.TABLE_FAV + "(" +
                ConstantesBaseDatos.TABLE_FAV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_FAV_ID_MASCOTA + " INTEGER, " +
                ConstantesBaseDatos.TABLE_FAV_NUMERO_RATING + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_FAV_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" + ConstantesBaseDatos.TABLE_MASCOTAS_ID + "))";

        sqLiteDatabase.execSQL(queryCrearTablaMascotas);
        sqLiteDatabase.execSQL(queryCrearTablaFavoritos);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTAS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_FAV);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Mascota> obtenerMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext()){
            Mascota mascota = new Mascota();
            mascota.setId(registros.getInt(0));
            mascota.setNombre(registros.getString(1));
            mascota.setIdentificacion(registros.getString(2));
            mascota.setRaza(registros.getString(3));
            mascota.setFoto(registros.getInt(4));
            mascotas.add(mascota);
        }
        db.close();

        return mascotas;
    }

    public void insertarMascota(String nombre, String identificacion, String raza, int foto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,nombre);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_IDENTIFICACION,identificacion);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAZA,raza);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO,foto);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS,null,contentValues);
        db.close();
    }

    public void insertarRating(Mascota mascota){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_FAV_ID_MASCOTA,mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_FAV_NUMERO_RATING,mascota.getRaiting());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_FAV,null,contentValues);
        db.close();
    }

    public int obtenerRatingMascota(Mascota mascota){
        int rating = 0;
        String query = "SELECT SUM (" + ConstantesBaseDatos.TABLE_FAV_NUMERO_RATING + ")" +
                " FROM " + ConstantesBaseDatos.TABLE_FAV + " WHERE " + ConstantesBaseDatos.TABLE_FAV_ID_MASCOTA + "=" +
                mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        if (registros.moveToNext()){
            rating = registros.getInt(0);
        }
        db.close();
        return rating;
    }

    public ArrayList<Mascota> obtenerMascotasFavoritas(){

        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS
                + " ORDER BY " + ConstantesBaseDatos.TABLE_MASCOTAS_RATING +" DESC LIMIT 5 " ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext()){
            Mascota mascota = new Mascota();
            mascota.setId(registros.getInt(0));
            mascota.setNombre(registros.getString(1));
            mascota.setIdentificacion(registros.getString(2));
            mascota.setRaza(registros.getString(3));
            mascota.setFoto(registros.getInt(4));
            mascota.setRaiting(registros.getInt(5));
            mascotas.add(mascota);
        }
        db.close();

        return mascotas;
    }
}
