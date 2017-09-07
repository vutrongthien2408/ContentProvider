package com.framgia.trongthien.contentprovider.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by TrongThien on 9/7/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Song.db";
    public static final String SQL_CREATE_SONG = "CREATE TABLE "
            + SongContract.SongEntry.TABLE_NAME
            + " ("
            + SongContract.SongEntry._ID
            + " INTEGER PRIMARY KEY,"
            + SongContract.SongEntry.COLUMN_TITLE
            + " TEXT,"
            + SongContract.SongEntry.COLUMN_ARTIST
            + " TEXT,"
            + SongContract.SongEntry.COLUMN_SIZE
            + " INTEGER)";
    private static final String SQL_DELETE_SONG =
            "DROP TABLE IF EXISTS " + SongContract.SongEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("CreatSQL", "DatabaseHelper");
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_SONG);
        Log.d("CreatSQL", "create");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_SONG);
        sqLiteDatabase.execSQL(SQL_CREATE_SONG);
    }
}
