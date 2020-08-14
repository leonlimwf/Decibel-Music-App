package com.example.musicsteam;

import android.app.Application;
import android.media.MediaPlayer;

public class MainApplication extends Application {
    public MediaPlayer player = null;

    @Override
    public void onCreate() {
        super.onCreate(); //telling the program to run this in addition to of the existing code on my oncreate
    }


    //get set method here
    public MediaPlayer getMediaPlayer() {
        return this.player;
    }

    public void setMediaPlayer(MediaPlayer player) {
        this.player = player;
    }
}
