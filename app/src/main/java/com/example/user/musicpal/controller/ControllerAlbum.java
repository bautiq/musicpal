package com.example.user.musicpal.controller;


import android.content.Context;

import com.example.user.musicpal.model.dao.DaoAlbum;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

public class ControllerAlbum {
    private Context context;
    private Integer offset;
    private Boolean hayPaginas;
    private int LIST_SIZE = 5;


    public ControllerAlbum(Context context) {
        this.context = context;
        hayPaginas = true;
        offset = 0;
    }

    public List<Album> getListaAlbumes(String categoria) {

        DaoAlbum daoAlbum = new DaoAlbum();
        List<Album> list = daoAlbum.obtenerAlbumes(context, categoria);
        return list;
    }

    public List<Album> getListaAlbumOnline() {
        return null;
    }

    public void obtenerAlbumes(final ResultListener<List<Album>> resultListenerDeLaVista) {
        if (hayInternet()) {
            DaoAlbum daoRetroFit = new DaoAlbum();
            daoRetroFit.obtenerAlbumes(new ResultListener<List<Album>>() {
                @Override
                public void finish(List<Album> resultado) {
                    if (resultado.size() < LIST_SIZE) {
                        hayPaginas = false;
                    }
                    resultListenerDeLaVista.finish(resultado);
                    offset += resultado.size();
                }
            }, offset, LIST_SIZE);
        }
    }

    private boolean hayInternet() {
        return true;
    }

    public Boolean getHayPaginas() {
        return hayPaginas;
    }
}
