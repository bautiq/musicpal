package com.example.user.musicpal.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContenedorDeCanciones {
    @SerializedName("data")
    private List<Cancion>canciones;

    public List<Cancion> getCanciones() {
        return canciones;
    }
}
