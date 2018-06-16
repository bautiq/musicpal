package com.example.user.musicpal.controller;


import android.content.Context;

import com.example.user.musicpal.model.dao.AlbumDao;
import com.example.user.musicpal.model.dao.DaoRetroFit;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

public class ControllerAlbum {
    private Context context;

    public ControllerAlbum(Context context) {
        this.context = context;
    }

    public List<Album> getListaAlbumes(String categoria) {

        AlbumDao albumDao = new AlbumDao();
        List<Album> list = albumDao.obtenerAlbumes(context, categoria);
        return list;
    }

    public List<Album> getListaAlbumOnline() {
        return null;
    }

    public void obtenerAlbunes(final ResultListener<List<Album>> resultListenerDeLaVista) {
        if (hayInternet()) {
            DaoRetroFit daoRetroFit=new DaoRetroFit();
            daoRetroFit.obtenerAlbunes(new ResultListener<List<Album>>() {
                @Override
                public void finish(List<Album> resultado) {
                    resultListenerDeLaVista.finish(resultado);
                }
            });
        }
    }

    private boolean hayInternet() {
        return true;
    }
}
