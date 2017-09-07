package com.framgia.trongthien.contentprovider.data.local;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.framgia.trongthien.contentprovider.data.model.Song;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by TrongThien on 9/7/2017.
 */

public class MyProvider {
    private ContentResolver resolver;

    public MyProvider(Context context) {
        resolver = context.getContentResolver();
    }

    public ArrayList<Song> readData() {
        ArrayList<Song> songs = new ArrayList<>();
        Cursor cursor = resolver.query(SongProvider.CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            while (cursor.moveToNext()) {
                songs.add(new Song(cursor));
            }
        }
        return songs;
    }

    public void insertSong(Song song) {
        resolver.insert(SongProvider.CONTENT_URI, song.getContentValue());
    }

    public void deleteSong(Song song) {
        String[] str = {song.getTitle()};
        resolver.delete(SongProvider.CONTENT_URI,
                SongContract.SongEntry.COLUMN_TITLE + "=?", str);
    }
}
