package com.example.user.musicpal.controller;

import android.media.AudioManager;
import android.media.MediaPlayer;

import com.example.user.musicpal.model.pojo.Cancion;

import java.io.IOException;


public class MediaPlayerGlobal {
    private static MediaPlayerGlobal mediaPlayerGlobal;
    private MediaPlayer mediaPlayer;

    private MediaPlayerGlobal(){
        mediaPlayer = new MediaPlayer();

    }

    public static MediaPlayerGlobal getInstance(){
        if(mediaPlayerGlobal == null){
            mediaPlayerGlobal = new MediaPlayerGlobal();
        }
        return mediaPlayerGlobal;
    }

    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }

    public void agregarCancionClikeada(Cancion cancion) throws IOException {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.reset();
        mediaPlayer.setDataSource(cancion.getUrlPreview());
        mediaPlayer.prepare();
        mediaPlayer.start();
    }
}
