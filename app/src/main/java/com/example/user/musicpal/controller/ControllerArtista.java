package com.example.user.musicpal.controller;


import com.example.user.musicpal.model.dao.ArtistaDao;
import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

public class ControllerArtista {
    public void obtenerArtistas(final ResultListener<List<Artista>> escuchadorDeLaVista){
        if (hayInternet()){
            ArtistaDao artistaDao = new ArtistaDao();
            artistaDao.obtenerArtistas(new ResultListener<List<Artista>>() {
                @Override
                public void finish(List<Artista> resultado) {
                    escuchadorDeLaVista.finish(resultado);
                }
            });

        }
    }


    private boolean hayInternet() {
        return true;
    }
}
