package com.example.user.musicpal.controller;


import android.content.Context;

import com.example.user.musicpal.model.dao.ArtistaDao;
import com.example.user.musicpal.model.dao.DaoAlbum;
import com.example.user.musicpal.model.dao.DaoCancion;
import com.example.user.musicpal.model.dao.DaoPlaylist;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

public class ControllerGlobal {
    private Context context;
    private Integer offset;
    private Boolean hayPaginas;
    private static final Integer LIST_SIZE = 10;


    public ControllerGlobal(Context context) {
        this.context = context;
        hayPaginas = true;
        offset = 0;
    }


    public void obtenerAlbumesOnline(final ResultListener<List<Album>> resultListenerDeLaVista) {
        if (hayInternet()) {
            DaoAlbum daoRetroFit = new DaoAlbum();
            daoRetroFit.obtenerAlbumes(new ResultListener<List<Album>>() {
                @Override
                public void finish(List<Album> resultado) {
                    if (resultado.size() < LIST_SIZE) {
                        hayPaginas = false;
                    }
                    offset += resultado.size();
                    resultListenerDeLaVista.finish(resultado);
                }
            }, offset, LIST_SIZE);
        }
    }

    public void obtenerCancionesPorAlbum(final ResultListener<List<Cancion>> listener, Integer id) {
        DaoCancion daoCancion = new DaoCancion();
        daoCancion.obtenerCancionesPorAlbum(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                listener.finish(resultado);
            }
        }, id);
    }

    public void obtenerArtistasOnline(final ResultListener<List<Artista>> resultListenerDeLaVista) {
        if (hayInternet()) {
            ArtistaDao artistaDao = new ArtistaDao();
            artistaDao.obtenerArtistas(new ResultListener<List<Artista>>() {
                @Override
                public void finish(List<Artista> resultado) {
                    if (resultado.size() < LIST_SIZE) {
                        hayPaginas = false;
                    }
                    offset += resultado.size();
                    resultListenerDeLaVista.finish(resultado);
                }
            }, offset, LIST_SIZE);
        }
    }

    public void obtenerCancionesPorArtista(final ResultListener<List<Cancion>> listener, Integer id) {
        DaoCancion daoCancion = new DaoCancion();
        daoCancion.obtenerCancionesPorArtista(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                listener.finish(resultado);
            }
        }, id);
    }

    public void obtenerPlaylistOnline(final ResultListener<List<Playlist>> resultListenerDeLaVista) {
        if (hayInternet()) {
            DaoPlaylist daoPlaylist = new DaoPlaylist();
            daoPlaylist.obtenerPlaylist(new ResultListener<List<Playlist>>() {
                @Override
                public void finish(List<Playlist> resultado) {

                    if (resultado.size() < LIST_SIZE) {
                        hayPaginas = false;
                    }
                    offset += resultado.size();
                    resultListenerDeLaVista.finish(resultado);
                }
            }, offset, LIST_SIZE);
        }
    }

    public void obtenerCancionesPorPlaylist(final ResultListener<List<Cancion>> listener, double id) {
        DaoCancion daoCancion = new DaoCancion();
        daoCancion.obtenerCancionesPorPlaylist(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                listener.finish(resultado);
            }
        }, id);
    }

    private boolean hayInternet() {
        return true;
    }

    public Boolean getHayPaginas() {
        return hayPaginas;
    }
}
