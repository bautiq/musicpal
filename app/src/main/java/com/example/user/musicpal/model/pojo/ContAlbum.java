package com.example.user.musicpal.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContAlbum {

    @SerializedName("data")
    private List<Album> listaAlbumes;

    public List<Album> obtenerListaDeAlbumes() {
        return listaAlbumes;
    }
}
