package com.example.user.musicpal.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContenedorDeCanciones {
    @SerializedName("data")
    private List<Cancion>canciones;

    private Cancion cancion;

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public Cancion getCancion() {
        return cancion;
    }
}

