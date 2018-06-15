package com.example.user.musicpal.controller;


import android.content.Context;

import com.example.user.musicpal.model.dao.AlbumDao;
import com.example.user.musicpal.model.pojo.Album;

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
}
