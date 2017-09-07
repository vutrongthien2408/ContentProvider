package com.framgia.trongthien.contentprovider.screen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.framgia.trongthien.contentprovider.R;
import com.framgia.trongthien.contentprovider.data.local.MyProvider;
import com.framgia.trongthien.contentprovider.data.local.SongProvider;
import com.framgia.trongthien.contentprovider.data.model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private SongAdapter songAdapter;
    private List<Song> songs = new ArrayList<>();
    private ListView lvListSong;
    private MyProvider myProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        lvListSong = findViewById(R.id.lvListSong);
    }

    private void initData() {
        myProvider = new MyProvider(this);
        songAdapter = new SongAdapter(songs, this);
        songs.addAll(myProvider.readData());
        lvListSong.setAdapter(songAdapter);
        lvListSong.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnAddSong:
                Random random = new Random();
                final Song song = new Song(random.nextInt(100) + "title", "artist", 3);
                Toast.makeText(this, "haha", Toast.LENGTH_SHORT).show();
                myProvider.insertSong(song);

                songs.clear();
                songs.addAll(myProvider.readData());
                songAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        myProvider.deleteSong(songs.get(i));
        songs.clear();
        songs.addAll(myProvider.readData());
        songAdapter.notifyDataSetChanged();
    }
}
