package com.example.cardioapp.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MedicoDbAdapter {
    DbHelper helper;
    public MedicoDbAdapter(Context context){
        helper = new DbHelper(context);
    }

    public boolean comprobarUserEnBD(String email) {
        boolean res = false;
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {DbHelper.UID, DbHelper.EMAIL, DbHelper.MyPASSWORD};
        //No existen los boolean, se almacenan como INTEGER con el valor 0(falso) o 1(verdadero). Igual sucede con los campos fecha y hora. Estos se pueden almacenas como TEXT, REAL o INTEGER. En función del tipo elegido se almacenará en distintos formatos, por ejemplo, si es TEXT, el dato tendrá el formato "YYYY-MM-DD HH:MM:SS.SSS".
        Cursor cursor =db.rawQuery("SELECT * FROM "+ DbHelper.TABLE_NAME+" WHERE "+ DbHelper.EMAIL  + " = ?;",new String[] {email});
        return cursor.getCount()==0?false: true;
    }

    public boolean comprobarPassEnBD(String email, String pass) {
        boolean res = false;
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {DbHelper.UID, DbHelper.EMAIL, DbHelper.MyPASSWORD};
        //No existen los boolean en BD, se almacenan como INTEGER con el valor 0(falso) o 1(verdadero). Igual sucede con los campos fecha y hora. Estos se pueden almacenas como TEXT, REAL o INTEGER. En función del tipo elegido se almacenará en distintos formatos, por ejemplo, si es TEXT, el dato tendrá el formato "YYYY-MM-DD HH:MM:SS.SSS".
        Cursor cursor =db.rawQuery("SELECT * FROM "+ DbHelper.TABLE_NAME+" WHERE "+ DbHelper.EMAIL + " = ? AND "+ DbHelper.MyPASSWORD+" = ?;", new String[] {email, pass});
        return cursor.getCount()==0?false: true;
    }

    public long insertData(String email, String pass, String dni ) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long id=0;
        //Comprobar si está ya en la BD
        //Cursor cursor =db.rawQuery("SELECT * FROM "+DbHelper.TABLE_NAME, null);
        //if(cursor.getCount()==0){
        if(db!=null){
            contentValues.put(DbHelper.EMAIL, email);
            contentValues.put(DbHelper.MyPASSWORD, pass);
            contentValues.put(DbHelper.DNI, dni);
            contentValues.put(DbHelper.NOMBRE, "null");
            contentValues.put(DbHelper.CENTRO_MEDICO, "null");
            contentValues.put(DbHelper.ANIO_NACIMIENTO, "null");
            id = db.insert(DbHelper.TABLE_NAME, null , contentValues); //devuelve el ID de fila de la fila recién insertada, o -1 si ocurrió un error
        }
        return id;
    }

    public String getData() {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {DbHelper.UID, DbHelper.EMAIL, DbHelper.MyPASSWORD, DbHelper.DNI};//, DbHelper.NOMBRE, DbHelper.CENTRO_MEDICO, DbHelper.ANIO_NACIMIENTO};
        Cursor cursor =db.query(DbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext()){
            int cid =cursor.getInt(cursor.getColumnIndex(DbHelper.UID));
            String email =cursor.getString(cursor.getColumnIndex(DbHelper.EMAIL));
            String  password =cursor.getString(cursor.getColumnIndex(DbHelper.MyPASSWORD));
            String  dni =cursor.getString(cursor.getColumnIndex(DbHelper.DNI));
            /*String  nombre =cursor.getString(cursor.getColumnIndex(DbHelper.NOMBRE));
            String  centromedico =cursor.getString(cursor.getColumnIndex(DbHelper.CENTRO_MEDICO));
            String  anioNacimiento =cursor.getString(cursor.getColumnIndex(DbHelper.ANIO_NACIMIENTO));*/
            buffer.append(cid+ "   " + email + "   " + password + "   " + dni);//nombre+ "   " + centromedico+ "   " + anioNacimiento);
        }
        return buffer.toString();
    }

    public  int delete(String email){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] whereArgs ={email};

        int count =db.delete(DbHelper.TABLE_NAME , DbHelper.EMAIL+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String oldName , String newName){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.NOMBRE,newName);
        String[] whereArgs= {oldName};
        int count =db.update(DbHelper.TABLE_NAME,contentValues, DbHelper.NOMBRE+" = ?",whereArgs );
        return count;
    }

    static class DbHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "cardioApp";    // Database Name
        public static final String TABLE_NAME = "MED_TBL";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version

        private static final String UID="_id";     // Column I (Primary Key)
        private static final String EMAIL = "Email";    //Column 2
        private static final String MyPASSWORD= "Password";    // Column 3
        private static final String DNI = "DNI";  // Column 4
        private static final String NOMBRE= "Nombre";    // Column 45
        private static final String CENTRO_MEDICO= "CentroMedico";    // Column 6
        private static final String ANIO_NACIMIENTO= "AnioNacimiento";    // Column 7
        //private static final String TIPO_USER= "Tipo_User";    // Column III

        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+EMAIL+" VARCHAR(255) ,"+ MyPASSWORD+" VARCHAR(225), "+DNI+ " VARCHAR(225), "+NOMBRE+" VARCHAR(255), "+CENTRO_MEDICO+" VARCHAR(255), "+ANIO_NACIMIENTO+" VARCHAR(255));"; //+TIPO_USER+ VARCHAR(225));xº
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public DbHelper(Context context) {
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
