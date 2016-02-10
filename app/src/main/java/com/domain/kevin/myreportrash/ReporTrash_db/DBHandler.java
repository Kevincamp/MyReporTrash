package com.domain.kevin.myreportrash.ReporTrash_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.domain.kevin.myreportrash.ReporTrash_clases.Basura;
import com.domain.kevin.myreportrash.ReporTrash_clases.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "ReporTrashDB1";
    // Nombres de Tablas
    private static final String TABLE_USUARIOS = "usuariosInfo";
    private static final String TABLE_BASURAS = "basuraInfo";
    // Columnas de Tablas
    private static final String USUARIO_KEY = "id";
    private static final String USUARIO_USERNAME = "username";
    private static final String USUARIO_PASSWORD = "password";
    private static final String USUARIO_NOMBRE = "nombre";
    private static final String USUARIO_APELLIDO = "apellido";
    private static final String USUARIO_EMAIL = "email";

    private static final String BASURA_KEY = "id";
    private static final String BASURA_DIRECCION = "direccion";
    private static final String BASURA_DETALLE = "detalle";
    private static final String BASURA_IMAGEN = "imagen";
    //private static final Double BASURA_LATITUD = 0.0;
    //private static final Double BASURA_LONGUITUD = 0.0;
    private static final String BASURA_USUARIO_ID = "usuarioId";
    //private static final String BASURA_FECHA = null;
    public DBHandler(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USUARIO_TABLE = "CREATE TABLE "+ TABLE_USUARIOS +
                "(" + USUARIO_KEY + " INTEGER PRIMARY KEY, " +
                USUARIO_USERNAME + " TEXT NOT NULL, "+
                USUARIO_PASSWORD + " TEXT NOT NULL, "+
                USUARIO_NOMBRE + " TEXT NOT NULL, "+
                USUARIO_APELLIDO + " TEXT NOT NULL, "+
                USUARIO_EMAIL + " TEXT NOT NULL "+");";
        String CREATE_BASURA_TABLE =  "CREATE TABLE "+ TABLE_BASURAS +
                "(" + BASURA_KEY + " INTEGER PRIMARY KEY, "+
                BASURA_DIRECCION + " TEXT NOT NULL, "+
                BASURA_DETALLE + " TEXT NOT NULL, "+
                BASURA_IMAGEN + " TEXT, "+
                BASURA_USUARIO_ID + " INTEGER, "+
                "FOREIGN KEY ("+BASURA_USUARIO_ID+") REFERENCES "+TABLE_USUARIOS+" ("+USUARIO_KEY+"));";
        db.execSQL(CREATE_USUARIO_TABLE);
        db.execSQL(CREATE_BASURA_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BASURAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        // Creating tables again
        onCreate(db);
    }

    // Agregando un nuevo Usuario
    public void addUser(Usuario usuario){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USUARIO_USERNAME, usuario.getUsername());
        values.put(USUARIO_PASSWORD, usuario.getPassword());
        values.put(USUARIO_NOMBRE, usuario.getNombre());
        values.put(USUARIO_APELLIDO, usuario.getApellido());
        values.put(USUARIO_EMAIL, usuario.getEmail());
        db.insert(TABLE_USUARIOS, null, values);
        db.close();
    }

    public Usuario getUsuario(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Usuario contact = new Usuario();
        Cursor cursor = db.query(TABLE_USUARIOS, new String[]{
                USUARIO_KEY,
                USUARIO_USERNAME,
                USUARIO_PASSWORD,
                USUARIO_NOMBRE,
                USUARIO_APELLIDO,
                USUARIO_EMAIL
        }, USUARIO_USERNAME + "=?", new String[]{username},null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
            contact = new Usuario(Integer.parseInt(cursor.getString(0)), //id
                    cursor.getString(1), // username
                    cursor.getString(2), //password
                    cursor.getString(3), // nombre
                    cursor.getString(4), // apellido
                    cursor.getString(5)); //email
        }
        return contact;
    }

    public void addBasura(Basura basura){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BASURA_DIRECCION, basura.getDireccion());
        values.put(BASURA_DETALLE, basura.getDetalle());
        values.put(BASURA_IMAGEN, basura.getImagen());
        values.put(BASURA_USUARIO_ID,basura.getUsuario_id());
        db.insert(TABLE_BASURAS, null, values);
        db.close();
    }

    public Basura getBasura(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Basura contact = new Basura();
        Cursor cursor = db.query(TABLE_BASURAS, new String[]{
                BASURA_KEY,
                BASURA_DIRECCION,
                BASURA_DETALLE,
                BASURA_IMAGEN,
                //BASURA_LATITUD.toString(),
                //BASURA_LONGUITUD.toString(),
                BASURA_USUARIO_ID
        }, BASURA_KEY + "=?", new String[]{id},null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
            contact = new Basura(Integer.parseInt(cursor.getString(0)),//idBasura
                    cursor.getString(1), // Direccion
                    cursor.getString(2), //Detalle
                    cursor.getString(3), //Imagen
                    //Double.parseDouble(cursor.getString(4)), //Latitud
                    //Double.parseDouble(cursor.getString(5)), //Longitud
                    Integer.parseInt(cursor.getString(6))); //UsuarioID
        }
        return contact;
    }

    public Basura getBasuraByUserID(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Basura contact = new Basura();
        Cursor cursor = db.query(TABLE_BASURAS, new String[]{
                BASURA_KEY,
                BASURA_DIRECCION,
                BASURA_DETALLE,
                BASURA_IMAGEN,
                //BASURA_LATITUD.toString(),
                //BASURA_LONGUITUD.toString(),
                BASURA_USUARIO_ID
        }, BASURA_USUARIO_ID + "=?", new String[]{id},null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
            contact = new Basura(Integer.parseInt(cursor.getString(0)),//idBasura
                    cursor.getString(1), // Direccion
                    cursor.getString(2), //Detalle
                    cursor.getString(3), //Imagen
                    //Double.parseDouble(cursor.getString(4)), //Latitud
                    //Double.parseDouble(cursor.getString(5)), //Longitud
                    Integer.parseInt(cursor.getString(6))); //UsuarioID
        }
        return contact;
    }

    public ArrayList<Basura> selectAllBasura(int userID){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Basura> list = new ArrayList<Basura>();
        Cursor cursor = db.query(TABLE_BASURAS,
                null,
                BASURA_USUARIO_ID +"=?",
                new String[] {Integer.toString(userID)},
                null,null,null);
        if(cursor.moveToFirst()){
            do{
            Basura basura = new Basura(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getInt(4));
            list.add(basura);
            }while(cursor.moveToNext());
        }
        if(cursor != null && cursor.isClosed()){
            cursor.close();
        }
        return list;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
