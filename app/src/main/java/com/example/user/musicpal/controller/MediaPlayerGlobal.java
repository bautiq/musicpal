package com.example.user.musicpal.controller;

import android.media.AudioManager;
import android.media.MediaPlayer;

import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.model.pojo.Cancion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MediaPlayerGlobal {
    private static MediaPlayerGlobal mediaPlayerGlobal;
    private MediaPlayer mediaPlayer;
    private Cancion cancion;
    private List<Cancion> playList;

    private MediaPlayerGlobal() {
        Artista artista = new Artista("Linkin Park");
        cancion = new Cancion("In my remains", "http://cdn-preview-3.deezer.com/stream/c-361a62705689675bcd00bcf1e2126684-22.mp3", artista, new Album("Living things", artista, "http://e-cdn-images.deezer.com/images/cover/0ce480e7723712dee352c68fdfef2599/250x250-000000-80-0-0.jpg"));
        List<Cancion> listaInicial = new ArrayList<>();
        listaInicial.add(cancion);
        mediaPlayer = new MediaPlayer();
        try {
            setearPlaylist(listaInicial, false, 0);
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

    public void setearPlaylist(List<Cancion> playListAReproducir, Boolean quieroQueInicie, final Integer posicion) throws IOException {
        final Integer[] posicionNueva = {posicion};
        this.cancion = playListAReproducir.get(posicion);
        this.playList = playListAReproducir;
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.reset();
        mediaPlayer.setDataSource(cancion.getUrlPreview());
        mediaPlayer.prepare();
        if (quieroQueInicie) {
            mediaPlayer.start();
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (!(posicionNueva[0] + 1 > playList.size() - 1)) {
                    posicionNueva[0] += 1;
                    Cancion cancionSiguiente = playList.get(posicionNueva[0]);
                    cancion = cancionSiguiente;
                    try {
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(cancionSiguiente.getUrlPreview());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }
}
