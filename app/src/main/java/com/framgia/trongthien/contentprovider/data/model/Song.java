package com.framgia.trongthien.contentprovider.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.framgia.trongthien.contentprovider.data.local.SongContract;

/**
 * Created by TrongThien on 9/7/2017.
 */

public class Song {
    private int id;
    private String title;
    private String artist;
    private int size;

    public Song(String title, String artist, int size) {

        this.title = title;
        this.artist = artist;
        this.size = size;

    }

    public Song(int id, String title, String artist, int size) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.size = size;
    }

    public Song(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex(SongContract.SongEntry._ID));
        title = cursor.getString(cursor.getColumnIndex(SongContract.SongEntry.COLUMN_TITLE));
        artist = cursor.getString(cursor.getColumnIndex(SongContract.SongEntry.COLUMN_ARTIST));
        size = cursor.getInt(cursor.getColumnIndex(SongContract.SongEntry.COLUMN_SIZE));

    }

    public ContentValues getContentValue() {
        ContentValues values = new ContentValues();
        if (title != null) {
            values.put(SongContract.SongEntry.COLUMN_TITLE, title);
        }
        if (artist != null) {
            values.put(SongContract.SongEntry.COLUMN_ARTIST, artist);
        }
        if (size != 0) {
            values.put(SongContract.SongEntry.COLUMN_SIZE, size);
        }
        if (id != 0) {
            values.put(SongContract.SongEntry._ID, id);
        }
        return values;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getSize() {
        return size;
    }
}
