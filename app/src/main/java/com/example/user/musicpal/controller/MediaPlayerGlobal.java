package com.example.user.musicpal.controller;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.utils.ResultListener;

import java.io.IOException;


public class MediaPlayerGlobal {
    private static MediaPlayerGlobal mediaPlayerGlobal;
    private MediaPlayer mediaPlayer;
    private Cancion cancion;

    private MediaPlayerGlobal() {
        Artista artista = new Artista("Linkin Park");
        cancion = new Cancion("In my remains", "http://cdn-preview-3.deezer.com/stream/c-361a62705689675bcd00bcf1e2126684-22.mp3", artista, new Album("Living things", artista, "http://e-cdn-images.deezer.com/images/cover/0ce480e7723712dee352c68fdfef2599/250x250-000000-80-0-0.jpg"));
        mediaPlayer = new MediaPlayer();
        try {
            agregarCancionClikeada(cancion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static MediaPlayerGlobal getInstance() {
        if (mediaPlayerGlobal == null) {
            mediaPlayerGlobal = new MediaPlayerGlobal();
        }
        return mediaPlayerGlobal;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void agregarCancionClikeada(Cancion cancion, Boolean quieroQueInicie) throws IOException {
        this.cancion = cancion;
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.reset();
        mediaPlayer.setDataSource(cancion.getUrlPreview());
        mediaPlayer.prepare();
        if(quieroQueInicie){
            mediaPlayer.start();
        }
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }
}
