package com.example.knote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "note_db";
    private static final int DATABASE_VERSION =1;

    public DatabaseHelper(Context context){
        super(context, Note.TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Note.create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format("DROP IF TABLE EXIST %s", Note.TABLE_NAME));
        onCreate(db);
    }

    public long insertData(String data){
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Note.COL2, data);

        long id = db.insert(Note.TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public Note getNote(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Note.TABLE_NAME, new String[]{Note.COL1, Note.COL2, Note.COL3}, Note.COL1 + " =?",
                new String[]{ String.valueOf(id)},null,null,null,null);

        if (cursor!= null)
            cursor.moveToFirst();
        Note note = new Note(
                cursor.getInt(cursor.getColumnIndex(Note.COL1)),
                cursor.getString(cursor.getColumnIndex(Note.COL2)),
                cursor.getString(cursor.getColumnIndex(Note.COL3))
        );

        cursor.close();
        return note;
    }

    public List<Note> getAllNotes(){
        List<Note> notes = new ArrayList<>();

        String select = "SELECT * FROM "+ Note.TABLE_NAME + " ORDER BY "+Note.COL3 + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select, null);

        if (cursor.moveToFirst()){
            do{
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(Note.COL1)));
                note.setNote(cursor.getString(cursor.getColumnIndex(Note.COL2)));
                note.setWaktu(cursor.getString(cursor.getColumnIndex(Note.COL3)));

                notes.add(note);
            }while (cursor.moveToNext());
        }

        db.close();

        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Note.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    //masih belum fix
    public void hapusData(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Note.TABLE_NAME, Note.COL1 + " = ?", new String[]{String.valueOf(note.getId())});
        db.close();
    }
}

