package com.example.user.musicpal.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.user.musicpal.model.dao.room.ArtistaDaoUtil;
import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

public class RoomControllerArtista {
    private Context context;
    private ArtistaDaoUtil artistaDaoUtil;

    public RoomControllerArtista(Context context) {
        this.context = context;
        artistaDaoUtil = new ArtistaDaoUtil(context);
    }

    public void insertarArtistas(List<Artista> artistas) {
        if (hayInternet() && artistas != null && artistas.size() > 0) {
            artistaDaoUtil.insertarArtistas(artistas);
        }
    }

    public void obtenerArtistas(final ResultListener<List<Artista>> resultListenerVista) {
        if (!(hayInternet())) {
            artistaDaoUtil.obtenerArtistas(new ResultListener<List<Artista>>() {
                @Override
                public void finish(List<Artista> resultado) {
                    resultListenerVista.finish(resultado);
                }
            });
        } else {
            resultListenerVista.finish(null);
        }

    }

    private boolean hayInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
