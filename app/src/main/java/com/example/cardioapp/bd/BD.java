package com.example.cardioapp.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "medico";
    public static final String COLUMN_NAME_TITLE = "nombre";
    public static final String COLUMN_NAME_SUBTITLE = "hospital";//EMAIL

    public static final String _ID = "id";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BD.TABLE_NAME + " (" +
                    BD._ID + " INTEGER PRIMARY KEY," +
                    BD.COLUMN_NAME_TITLE + " TEXT," +
                    BD.COLUMN_NAME_SUBTITLE + " TEXT)";

    private static final String cardioBD = "comments.sqlite";
    private static final int DB_version = 1;

    public BD(Context context){
        super(context, cardioBD, null, DB_version);

    }
    /**El método onCreate(SQLiteDatabase sqLiteDatabase) se llama solo una vez durante el ciclo de vida de la aplicación.
     * Se llamará cada vez que haya una primera llamada a la función getReadableDatabase() o getWritableDatabase()
     * disponible en la clase super SQLiteOpenHelper. Entonces, la clase SQLiteOpenHelper llama al método onCreate()
     * después de crear la base de datos e instanciar el objeto SQLiteDatabase.
     * El nombre de la base de datos se pasa en la llamada del constructor:
     * @param bd
     * */
    @Override
    public void onCreate(SQLiteDatabase bd) {
       bd.execSQL(SQL_CREATE_ENTRIES);
    }
/**Solo se llama cuando hay una actualización en la versión existente. Entonces, para actualizar una versión, debemos incrementar
 * el valor de la variable de versión pasada en el constructor de la superclase.
 * */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
