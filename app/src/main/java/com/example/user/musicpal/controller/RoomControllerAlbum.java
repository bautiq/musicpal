package com.example.user.musicpal.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.user.musicpal.model.dao.room.AlbumDaoUtil;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

public class RoomControllerAlbum {
    private Context context;
    private AlbumDaoUtil albumDaoUtil;


    public RoomControllerAlbum(Context context) {
        this.context = context;
        albumDaoUtil = new AlbumDaoUtil(context);
    }

    public void insertarAlbumes(List<Album> list) {
        if (hayInternet() && list != null && list.size() > 0) {
            albumDaoUtil.insertarListaAlbumes(list);
        }
    }
    public void obtenerAlbumes(final ResultListener<List<Album>> resultListenerVista){
        if (!(hayInternet())){
            albumDaoUtil.obtenerListaAlbumes(new ResultListener<List<Album>>() {
                @Override
                public void finish(List<Album> resultado) {
                    if(resultado != null && resultado.size() > 0){
                        resultListenerVista.finish(resultado);
                    }
                }
            });
        }
    }

    private boolean hayInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
