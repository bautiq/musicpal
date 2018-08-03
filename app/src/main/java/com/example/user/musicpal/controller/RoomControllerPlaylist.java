package com.example.user.musicpal.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.user.musicpal.model.dao.room.PlaylistDaoUtil;
import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

public class RoomControllerPlaylist {
    private Context context;
private PlaylistDaoUtil playlistDaoUtil;
    public RoomControllerPlaylist(Context context) {
        this.context = context;
        playlistDaoUtil = new PlaylistDaoUtil(context);
    }

    public void insertarPlaylists(List<Playlist> playlistList){
        playlistDaoUtil.insertarPlaylists(playlistList);
    }
    public void obtenerPlaylists(final ResultListener<List<Playlist>> resultListenerVista){
        if (!(hayInternet())){
            playlistDaoUtil.obtenerPlaylists(new ResultListener<List<Playlist>>() {
                @Override
                public void finish(List<Playlist> resultado) {
                    resultListenerVista.finish(resultado);
                }
            });
        }else{
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
