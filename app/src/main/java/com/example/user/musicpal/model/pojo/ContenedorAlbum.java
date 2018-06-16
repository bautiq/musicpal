package com.example.user.musicpal.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContenedorAlbum {


    private List<Album> data;

    public List<Album> obtenerData() {
        return data;
    }
}
