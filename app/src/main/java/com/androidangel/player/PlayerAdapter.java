package com.androidangel.player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayerAdapter extends ArrayAdapter<Song> {


    public PlayerAdapter(Context context, int list_item, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.song_list_item, parent, false);
        }
        Song currentSong = getItem(position);
        TextView titleTv = listItemView.findViewById(R.id.tv_title);
        titleTv.setText(currentSong.getTitle());

        TextView artistTv = listItemView.findViewById(R.id.tv_artist);
        artistTv.setText(currentSong.getArtist());

        ImageView imageViewView = listItemView.findViewById(R.id.imageView);
        imageViewView.setImageResource(currentSong.getImageResource());

        View textContainer = listItemView.findViewById(R.id.textContainer);
        textContainer.getContext();
        return listItemView;
    }
}
