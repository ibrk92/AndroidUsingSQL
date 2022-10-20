package com.example.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SQLite Veri tabaninin olsuturulmasi

        // Tum islemleri try and catch icerisinde yapacagiz

        try {

            SQLiteDatabase database = this.openOrCreateDatabase("Musicians", MODE_PRIVATE,null); // veri tabanini actik
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY,name VARCHAR,age INTEGER)");//musicians tablo ismi oldu
            //database.execSQL("INSERT INTO musicians (name, age) VALUES('James',50)"); // Kayit ettik
            //database.execSQL("INSERT INTO musicians(name, age) VALUES('Lars', 60)");
            //database.execSQL("INSERT INTO musicians (name, age) VALUES('Kirk',40)");

            // Guncelleme yapmak; (UPDATE KULLANIMI)

            //database.execSQL("UPDATE musicians SET name = 'Kirk Hammett' WHERE id = 3");
            //database.execSQL("UPDATE musicians SET age = 61 WHERE name = 'Lars'");

            //DELETE ISLEMLERI

            //database.execSQL("DELETE FROM musicians WHERE id = 2");

            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name = 'James'",null); // herseyi cek dedik selection arg olarak ise filtrelemeyi sordu suan icin yapmadik, istersen filtreleyedebilirsin
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE '%s'" ,null); // sonu s ile biten isimleri getirir
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'K%'" ,null); // ismi K ile baslayanlari getirecek.

            Cursor cursor = database.rawQuery("SELECT * FROM musicians",null);
            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");
            int idIndex = cursor.getColumnIndex("id");

            while(cursor.moveToNext()){ // bu cursor ya da imlec tum verileri gezsin yani okusun demek
                System.out.println("Name: " + cursor.getString(nameIndex));
                System.out.println("Age: " + cursor.getInt(ageIndex));
                System.out.println("Id: " + cursor.getInt(idIndex));

            }

            cursor.close();

        }catch (Exception e){
            e.printStackTrace(); // hata olursa hatayi yakalayacak ve detaylari gorecegiz.
        }

    }


}