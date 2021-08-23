package com.example.cardioapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.content.Context;

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
    @Override
    public void onCreate(SQLiteDatabase bd) {
       bd.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
