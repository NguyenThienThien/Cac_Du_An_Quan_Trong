package com.longthph30891.note_mvp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String Name_db = "QLNote";
    public DbHelper(@Nullable Context context) {
        super(context, Name_db, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableUser = "CREATE TABLE USER (username TEXT PRIMARY KEY, password TEXT)";
        db.execSQL(tableUser);
        String dataUser = "INSERT INTO USER VALUES('admin','Longdeptrai01')";
        db.execSQL(dataUser);

        String tableNote = "CREATE TABLE NOTE (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT)";
        db.execSQL(tableNote);
        String dataNote = "INSERT INTO NOTE VALUES(1,'Note number 1','My dream there are: ZX25R, R15M, Rich')";
        db.execSQL(dataNote);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USER");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NOTE");
        }
        onCreate(sqLiteDatabase);
    }
}
