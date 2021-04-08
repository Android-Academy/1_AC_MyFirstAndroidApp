package com.vullnetlimani.myapplication.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyUsersDB.db";
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DATA = "data_birth";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_EMAIL = "email";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(

                "create table " + TABLE_NAME +
                        "(" + COLUMN_ID + " text primary key," +
                        COLUMN_NAME + " text," +
                        COLUMN_DATA + " text," +
                        COLUMN_ADDRESS + " text," +
                        COLUMN_EMAIL + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String id, String name, String data_birth, String address, String email) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();// Kjo hapet ketu databaza

        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", id);
        contentValues.put("name", name);
        contentValues.put("data_birth", data_birth);
        contentValues.put("address", address);
        contentValues.put("email", email);

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return true;
    }

    public Cursor listAllData() {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select "
                + COLUMN_ID + ", "
                + COLUMN_NAME + ", "
                + COLUMN_DATA + ", "
                + COLUMN_ADDRESS + ", "
                + COLUMN_EMAIL + ", "
                + COLUMN_ID + " from " + TABLE_NAME, null);
        return cursor;

    }

    public Cursor searchData(String searchInput) {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from "
                + TABLE_NAME
                + " where _id = '"
                + searchInput + "' or name LIKE '%"
                + searchInput + "%'", null);
        return cursor;
    }

    public boolean updateData(String id, String name, String data, String address, String email) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_DATA, data);
        contentValues.put(COLUMN_ADDRESS, address);
        contentValues.put(COLUMN_EMAIL, email);
        db.update(TABLE_NAME, contentValues, "_id = ? ", new String[]{id});
        return true;
    }

    public boolean deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "_id = ? ", new String[]{id});
        return true;
    }
}
