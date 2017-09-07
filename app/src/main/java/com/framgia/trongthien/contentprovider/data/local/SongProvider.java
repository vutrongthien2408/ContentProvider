package com.framgia.trongthien.contentprovider.data.local;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.framgia.trongthien.contentprovider.data.model.Song;

import java.util.ArrayList;

/**
 * Created by TrongThien on 9/7/2017.
 */

public class SongProvider extends ContentProvider {

    public static final String AUTHORITY = "framgia.trongthien.ContentProvider";
    public static final String SCHEME = "content://";
    public static final String URL_DATA_BASE = SCHEME + AUTHORITY + "/song";
    public static final Uri CONTENT_URI = Uri.parse(URL_DATA_BASE);
    private static final int URI_SONG = 0;

    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "song", URI_SONG);
    }

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    public boolean onCreate() {
        databaseHelper = new DatabaseHelper(getContext());
        Log.d("CreatSQL", "createSongprovider");
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        sqLiteDatabase = databaseHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case URI_SONG:
                return sqLiteDatabase.query(SongContract.SongEntry.TABLE_NAME,
                        projection, selection, selectionArgs, sortOrder, null, null);
            case UriMatcher.NO_MATCH:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long index;
        databaseHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case URI_SONG:
                index = sqLiteDatabase.insert(SongContract.SongEntry.TABLE_NAME, null, contentValues);
                Uri uri_ = null;
                if (index != -1) {
                    uri_ = ContentUris.withAppendedId(CONTENT_URI, index);
                }
                return uri_;
            case UriMatcher.NO_MATCH:
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case URI_SONG:
                return sqLiteDatabase.delete(SongContract.SongEntry.TABLE_NAME,
                        selection, selectionArgs);
            case UriMatcher.NO_MATCH:
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
