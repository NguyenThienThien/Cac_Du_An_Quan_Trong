package com.longthph30891.note_mvp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LoginDao {
    private final DbHelper dbHelper;

    public LoginDao(Context context) {
       dbHelper = new DbHelper(context);
    }
    public boolean checkAcount(String username, String password){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM USER WHERE username = ? AND password = ?";
        String[] selectionArgs ={username,password};
        Cursor cursor = db.rawQuery(query,selectionArgs);
        boolean result = cursor.getCount()>0;
        cursor.close();
        db.close();
        return result;
    }
}
