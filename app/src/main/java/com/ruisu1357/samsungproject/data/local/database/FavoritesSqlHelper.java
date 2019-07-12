package com.ruisu1357.samsungproject.data.local.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ruisu1357.samsungproject.data.remote.models.ImageResponse;

import java.util.ArrayList;
import java.util.Locale;

public class FavoritesSqlHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "fav_db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "favorites_table";
    public static final String COL_ID = "id";
    public static final String COL_URL = "url";

    public static final String TAG = "DatabaseHelper";


    public FavoritesSqlHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format(Locale.US, "CREATE TABLE $s(%s TEXT PRIMARY_KEY, %s TEXT", TABLE_NAME, COL_ID,COL_URL));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public ArrayList<String> getAllFav(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> returnList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                returnList.add(cursor.getString(cursor.getColumnIndex(COL_URL)));
            } while (cursor.moveToNext());
        }

        return returnList;
    }

    public void insertIntoDatabase(ImageResponse imageResponse) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, imageResponse.getId());
        contentValues.put(COL_URL, imageResponse.getUrl());
        db.insert(TABLE_NAME, null, contentValues);

        Log.d(TAG, "insertIntoDatabase: Saved id  " + imageResponse.getId());
    }
}
