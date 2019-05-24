package com.example.lifehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String SWORD="SWORD";
    // SQLiteDatabase db;

    public DBHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists user(phone integer primary key," +
                "password varchar(50) not null," +
                "sex varchar(8) not null)");
        db.execSQL("create table if not exists record_password(phone integer primary key," +
                "password varchar(50) not null," +
                "record varchar(2) not null)");
        db.execSQL("create table if not exists last_login(phone integer primary key)");
        db.execSQL("create table if not exists memo(id integer primary key AUTOINCREMENT," +
                "phone TEXT not null,"+
                "content TEXT not null," +
                "date TEXT not null)");
        db.execSQL("create table if not exists schedule(id integer primary key AUTOINCREMENT," +
                "phone TEXT not null,"+
                "content TEXT not null," +
                "date TEXT not null," +
                "remindDate integer DEFAULT 0," +
                "repeat integer DEFAULT 0," +
                "memo TEXT DEFAULT ''," +
                "place TEXT DEFAULT '')");
        db.execSQL("create table if not exists news(id integer primary key AUTOINCREMENT," +
                "name TEXT ," +
                "phone TEXT not null,"+
                "url TEXT not null)");
        db.execSQL("create table if not exists weather(id integer primary key AUTOINCREMENT," +
                "name TEXT ," +
                "phone TEXT not null,"+
                "url TEXT not null)");
        db.execSQL("create table if not exists area_code(id integer primary key AUTOINCREMENT," +
                "name TEXT not null," +
                "url_pic TEXT not null)");
        db.execSQL("create table if not exists user_information(phone integer primary key ," +
                "name TEXT ,"+
                "sex varchar(8) not null," +
                "color integer DEFAULT 0," +
                "tx integer DEFAULT 0," +
                "number_of_memo integer DEFAULT 0," +
                "number_of_schedule integer DEFAULT 0," +
                "number_of_news integer DEFAULT 0)");

        /*
        String sql = "create table user(phone varchar(20),password varchar(20),sex varchar(8))";
        db.execSQL(sql);
        */
        Log.i(SWORD,"create a Database");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(SWORD,"update a Database");

    }
}