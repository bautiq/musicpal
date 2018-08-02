package com.example.user.musicpal.model.dao.room;

import android.arch.persistence.room.TypeConverter;

import com.example.user.musicpal.model.pojo.Artista;
import com.google.gson.Gson;


public class ArtistaConverter {
    private Gson gson;

    public ArtistaConverter() {
        this.gson = new Gson();
    }

    @TypeConverter
    public Artista convertirStringAartista(String s) {
        return gson.fromJson(s, Artista.class);
    }

    @TypeConverter
    public String convertirArtistaAstring(Artista artista) {
        return gson.toJson(artista);
    }
}
