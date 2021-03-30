package com.tolgaalperkus.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY,name VARCHAR,age INT)");
            database.execSQL("INSERT INTO musicians(name,age) VALUES ('Lars',50)");
            database.execSQL("INSERT INTO musicians(name,age) VALUES ('James',60)");
            database.execSQL("INSERT INTO musicians(name,age) VALUES ('Kirk',55)");

            Cursor cursor = database.rawQuery("SELECT * FROM musicians",null);

            int idIx = cursor.getColumnIndex("id");
            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");

            while(cursor.moveToNext()){
                System.out.println("Name :"+cursor.getString(nameIx));
                System.out.println("Age :"+cursor.getInt(ageIx));
                System.out.println("Id :"+cursor.getInt(idIx));
            }
            cursor.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}