package com.example.cardioapp.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public boolean comprobarUserEnBD(String email) {
        boolean res = false;
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.Email,myDbHelper.MyPASSWORD};
        //No existen los boolean, se almacenan como INTEGER con el valor 0(falso) o 1(verdadero). Igual sucede con los campos fecha y hora. Estos se pueden almacenas como TEXT, REAL o INTEGER. En función del tipo elegido se almacenará en distintos formatos, por ejemplo, si es TEXT, el dato tendrá el formato "YYYY-MM-DD HH:MM:SS.SSS".
        Cursor cursor =db.rawQuery("SELECT * FROM "+myDbHelper.TABLE_NAME+" WHERE "+myDbHelper.Email  + " = ?;",new String[] {email});
        return cursor.getCount()==0?false: true;
    }

    public boolean comprobarPassEnBD(String email, String pass) {
        boolean res = false;
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.Email,myDbHelper.MyPASSWORD};
        //No existen los boolean, se almacenan como INTEGER con el valor 0(falso) o 1(verdadero). Igual sucede con los campos fecha y hora. Estos se pueden almacenas como TEXT, REAL o INTEGER. En función del tipo elegido se almacenará en distintos formatos, por ejemplo, si es TEXT, el dato tendrá el formato "YYYY-MM-DD HH:MM:SS.SSS".
        Cursor cursor =db.rawQuery("SELECT * FROM "+myDbHelper.TABLE_NAME+" WHERE "+myDbHelper.Email + " = ? AND "+myDbHelper.MyPASSWORD+" = ?;", new String[] {email, pass});
        return cursor.getCount()==0?false: true;
    }

    public long insertData(String email, String pass) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long id=0;
        //Comprobar si está ya en la BD
        Cursor cursor =db.rawQuery("SELECT * FROM "+myDbHelper.TABLE_NAME, null);
        if(cursor.getCount()==0){
            contentValues.put(myDbHelper.Email, email);
            contentValues.put(myDbHelper.MyPASSWORD, pass);
            id = db.insert(myDbHelper.TABLE_NAME, null , contentValues);
        }
        return id;
    }

    public String getData() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.Email,myDbHelper.MyPASSWORD};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext()){
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.Email));
            String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        return buffer.toString();
    }

    public  int delete(String email){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={email};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.Email+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String oldName , String newName){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.Email,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.Email+" = ?",whereArgs );
        return count;
    }

    static class myDbHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "tablaLogin";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String Email = "Email";    //Column II
        private static final String MyPASSWORD= "Password";    // Column III
        //private static final String TIPO_USER= "Tipo_User";    // Column III
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Email+" VARCHAR(255) ,"+ MyPASSWORD+" VARCHAR(225));"; //+TIPO_USER+ VARCHAR(225));xº
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}
