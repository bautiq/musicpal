package com.example.user.musicpal.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContenedorPlaylist {

    @SerializedName("data")
    private List<Playlist>playlists;

    public List<Playlist> getPlaylists() {
        return playlists;
    }
}
