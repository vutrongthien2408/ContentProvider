package com.framgia.trongthien.contentprovider.data.local;

import android.provider.BaseColumns;

/**
 * Created by TrongThien on 9/7/2017.
 */

public class SongContract {
    public SongContract() {
    }

    public static class SongEntry implements BaseColumns{
        public static final String TABLE_NAME = "tbl_song";
        public static final String COLUMN_SIZE = "size";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_ARTIST = "artist";

    }
}
