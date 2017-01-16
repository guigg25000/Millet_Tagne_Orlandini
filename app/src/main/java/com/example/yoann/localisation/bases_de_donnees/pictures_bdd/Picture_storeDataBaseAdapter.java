package com.example.yoann.localisation.bases_de_donnees.pictures_bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by Hermann TAGNE on 12/01/2017.
 */

public class Picture_storeDataBaseAdapter {
    static final String DATABASE_NAME = "picture_store.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_CREATE = "create table " + "PICTURE_STORE" + "( "
            + "ID" + " integer primary key autoincrement,"
            + "PICTURE_ID  text,LOCATION_ID text); ";
    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public Picture_storeDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    public Picture_storeDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String pictureID, String locationID) {
        ContentValues newValues = new ContentValues();
        newValues.put("PICTURE_ID", pictureID);
        newValues.put("LOCATION_ID", locationID);
        db.insert("PICTURE_STORE", null, newValues);

    }

    public int deleteEntry(String pictureID) {

        String where = "PICTURE_ID=?";
        int numberOFEntriesDeleted = db.delete("PICTURE_STORE", where,
                new String[] { pictureID });
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String pictureID) {
        Cursor cursor = db.query("PICTURE_STORE", null, " PICTURE_ID=?",
                new String[] { pictureID }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String location_id = cursor.getString(cursor.getColumnIndex("LOCATION_ID"));
        cursor.close();
        return location_id;
    }

    public void updateEntry(String pictureID, String location_id) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("PICTURE_ID", pictureID);
        updatedValues.put("LOCATION_ID", location_id);

        String where = "PICTURE_ID = ?";
        db.update("PICTURE_STORE", updatedValues, where, new String[] { pictureID });
    }
}