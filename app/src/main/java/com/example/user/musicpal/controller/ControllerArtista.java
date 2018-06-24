package com.example.user.musicpal.controller;


import android.content.Context;

import com.example.user.musicpal.model.dao.DaoArtista;
import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

public class ControllerArtista {

    private Context context;

    public ControllerArtista(Context context) {
        this.context = context;
    }

    public void obtenerArtistasOnline(final ResultListener<List<Artista>> resultListenerDeLaVista) {
        if (hayInternet()) {
            DaoArtista daoRetroFit = new DaoArtista();
            daoRetroFit.obtenerArtistas(new ResultListener<List<Artista>>() {
                @Override
                public void finish(List<Artista> resultado) {
                    resultListenerDeLaVista.finish(resultado);
                }
            });
        }
    }

    private boolean hayInternet() {
        return true;
    }
}

