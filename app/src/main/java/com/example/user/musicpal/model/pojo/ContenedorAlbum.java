package com.example.user.musicpal.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContenedorAlbum {
    @SerializedName("album")
    private List<Album> albumList;

    public List<Album> getAlbumList() {
        return albumList;
    }
}
