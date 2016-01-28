package com.domain.kevin.myreportrash.ReporTrash_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.domain.kevin.myreportrash.ReporTrash_clases.Basura;
import com.domain.kevin.myreportrash.ReporTrash_clases.Usuario;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
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
    private static final String BASURA_USUARIO_ID = "userid";
    private static final String BASURA_LATITUD = "0.0";
    private static final String BASURA_LONGUITUD = "0.0";
    private static final String BASURA_FECHA = null;
    public DBHandler(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLES = "CREATE TABLE "+ TABLE_USUARIOS +
                "(" + USUARIO_KEY + " INTEGER PRIMARY KEY, " +
                USUARIO_USERNAME + " TEXT NOT NULL, "+
                USUARIO_PASSWORD + " TEXT NOT NULL, "+
                USUARIO_NOMBRE + " TEXT NOT NULL, "+
                USUARIO_APELLIDO + " TEXT NOT NULL, "+
                USUARIO_EMAIL + " TEXT NOT NULL "+");"+
                "CREATE TABLE "+ TABLE_BASURAS +
                "(" + BASURA_KEY + " INTEGER PRIMARY KEY, "+
                BASURA_DETALLE + " TEXT NOT NULL, "+
                BASURA_IMAGEN + " TEXT, "+
                BASURA_LATITUD + " REAL NOT NULL, "+
                BASURA_LONGUITUD + " REAL NOT NULL, "+
                BASURA_FECHA + " DATETIME, "+
                BASURA_USUARIO_ID + " INTEGER PREFERENCES "+ TABLE_USUARIOS + "("+USUARIO_KEY+") ON UPDATE CASCADE);";
        db.execSQL(CREATE_TABLES);
        //db.execSQL(CREATE_BASURA_TABLE);

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
        values.put(BASURA_LATITUD, basura.getLatitud().toString());
        values.put(BASURA_LONGUITUD, basura.getLonguitud().toString());
        values.put(BASURA_USUARIO_ID,basura.getUsuario_id());
        values.put(BASURA_FECHA,getDateTime());

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
                BASURA_LATITUD,
                BASURA_LONGUITUD,
                BASURA_USUARIO_ID
        }, BASURA_KEY + "=?", new String[]{id},null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
            contact = new Basura(Integer.parseInt(cursor.getString(0)),//idBasura
                    cursor.getString(1), // Direccion
                    cursor.getString(2), //Detalle
                    cursor.getString(3), //Imagen
                    Double.parseDouble(cursor.getString(4)), //Latitud
                    Double.parseDouble(cursor.getString(5)), //Longitud
                    Integer.parseInt(cursor.getString(6))); //UsuarioID
        }
        return contact;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
