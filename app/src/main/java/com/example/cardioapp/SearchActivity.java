package com.example.cardioapp;



import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


public class SearchActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            miBusqueda(query);
        }

    }

    private void miBusqueda(String query) {
        SQLiteDatabase bd = openOrCreateDatabase("your database name",MODE_PRIVATE,null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS TutorialsPoint(Username VARCHAR,Password VARCHAR);");
        bd.execSQL("INSERT INTO TutorialsPoint VALUES('admin','admin');");

    }
   /* public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.id.btnsearch, menu);

        return true;
    }*/

}