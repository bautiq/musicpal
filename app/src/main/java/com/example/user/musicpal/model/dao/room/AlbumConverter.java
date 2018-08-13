package com.example.user.musicpal.model.dao.room;

import android.arch.persistence.room.TypeConverter;

import com.example.user.musicpal.model.pojo.Album;
import com.google.gson.Gson;

public class AlbumConverter {
    private Gson gson;

    public AlbumConverter() {
        gson = new Gson();
    }

    @TypeConverter
    public Album convertirStringAalbum(String s) {
        return gson.fromJson(s, Album.class);
    }

    @TypeConverter
    public String convertirAlbumAstring(Album album) {
        return gson.toJson(album);
    }
}
