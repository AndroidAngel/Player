package com.androidangel.player;

public class Song {

    private int mSongResource;
    private String mTitle;
    private String mArtist;
    private int mImageResource;

    public Song(String title, String artist, int imageResource, int songResource) {
        mTitle = title;
        mArtist = artist;
        mImageResource = imageResource;
        mSongResource = songResource;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getArtist() {
        return mArtist;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public int getSongResource() {
        return mSongResource;
    }

}

