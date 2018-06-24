package com.example.user.musicpal.controller;


import com.example.user.musicpal.model.dao.DaoCancion;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

public class ControllerCancion {

    public void obtenerCancionesPorAlbum(final ResultListener<List<Cancion>> listener, int id) {
        DaoCancion daoCancion = new DaoCancion();
        daoCancion.obtenerCancionesPorAlbum(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                listener.finish(resultado);
            }
        }, id);
    }

    public void obtenerCancionesPorArtista(final ResultListener<List<Cancion>> listener, int id) {
        DaoCancion daoCancion = new DaoCancion();
        daoCancion.obtenerCancionesPorArtista(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                listener.finish(resultado);
            }
        },id);
    }
}

