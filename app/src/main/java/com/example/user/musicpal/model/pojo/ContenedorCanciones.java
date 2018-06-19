package com.example.user.musicpal.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class ContenedorCanciones {
    @SerializedName("data")
    private List<Cancion> cancionList;

    public List<Cancion> getCancionList() {
        return cancionList;
    }
}
