package com.longthph30891.note_mvp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.longthph30891.note_mvp.model.Note;

import java.util.ArrayList;

public class NoteDao {
    private final DbHelper dbHelper;

    public NoteDao(Context context) {
        dbHelper = new DbHelper(context);
    }
    public ArrayList<Note> selectAll(){
        ArrayList<Note> notes = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM NOTE",null);
            if (cursor.getCount() >0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    Note note = new Note();
                    note.setId(cursor.getInt(0));
                    note.setName(cursor.getString(1));
                    note.setDescription(cursor.getString(2));
                    notes.add(note);
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return notes;
    }
    public boolean insert(Note note){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",note.getName());
        values.put("description",note.getDescription());
        long data = db.insert("NOTE",null,values);
        return data >0;
    }
    public boolean update(Note note){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",note.getName());
        values.put("description",note.getDescription());
        long data = db.update("NOTE",values,"id=?",new String[]{String.valueOf(note.getId())});
        return data >0;
    }
    public boolean delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long data = db.delete("NOTE","id=?",new String[]{String.valueOf(id)});
        return data >0;
    }
}
