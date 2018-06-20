package com.example.user.musicpal.controller;


import com.example.user.musicpal.model.dao.CancionDao;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

public class ControllerCancion {

    public void obtenerCancionesPorAlbum(final ResultListener<List<Cancion>> listener, int id) {
        CancionDao cancionDao = new CancionDao();
        cancionDao.obtenerCancionesPorAlbum(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                listener.finish(resultado);
            }
        }, id);
    }

}

