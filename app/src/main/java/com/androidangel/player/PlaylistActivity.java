package com.androidangel.player;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity {

    ArrayList<Song> songs;
    ListView listView;
    PlayerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        ArrayList<Song> songs = new ArrayList<>();

        songs.add(new Song("Friend of Mine", "Avicii", R.drawable.musical_notes, R.raw.friend_of_mine_avicii));
        songs.add(new Song("The Nights", "Avicii", R.drawable.musical_notes, R.raw.the_nights_avicii));
        songs.add(new Song("Waiting For Love", "Avicii", R.drawable.musical_notes, R.raw.waiting_for_love_avicii));
        songs.add(new Song("Wake Me Up", "Avicii", R.drawable.musical_notes, R.raw.wake_me_up_avicii));
        songs.add(new Song("Without You", "Avicii", R.drawable.musical_notes, R.raw.without_you_avicii));


        listView = findViewById(R.id.list);
        adapter = new PlayerAdapter(this, R.layout.song_list_item, songs);
        listView.setAdapter(adapter);
        setupListViewListener();
    }

    private void setupListViewListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent playerIntent = new Intent(PlaylistActivity.this, PlayerActivity.class);
                startActivity(playerIntent);
            }
        });
    }
}
