package com.example.user.musicpal.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ContenedorDeTracks {

    @SerializedName("data")
    private List<Cancion> listaDeTracks;

    public List<Cancion> getCancionList() {
        return listaDeTracks;
    }
}
