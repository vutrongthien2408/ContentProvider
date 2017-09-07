package com.framgia.trongthien.contentprovider.screen;

import android.content.Context;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.framgia.trongthien.contentprovider.R;
import com.framgia.trongthien.contentprovider.data.model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TrongThien on 9/7/2017.
 */

public class SongAdapter extends BaseAdapter {
    private List<Song> songs = new ArrayList<>();
    private LayoutInflater inflater;

    public SongAdapter(List<Song> songs, Context context) {
        this.songs = songs;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int i) {
        return songs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_song, viewGroup, false);
            viewHolder.tvArtist = view.findViewById(R.id.tvArtist);
            viewHolder.tvTitle = view.findViewById(R.id.tvTitle);
            viewHolder.tvSize = view.findViewById(R.id.tvSize);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvSize.setText(songs.get(i).getSize() + "");
        viewHolder.tvTitle.setText(songs.get(i).getTitle());
        viewHolder.tvArtist.setText(songs.get(i).getArtist());

        return view;
    }

    class ViewHolder {
        TextView tvTitle;
        TextView tvArtist;
        TextView tvSize;
    }
}
