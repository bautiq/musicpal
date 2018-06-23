package com.example.user.musicpal.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContenedorArtist {

    @SerializedName("data")
    private List<Artista> listaArtistas;

    public List<Artista> obtenerListaDeArtistas() {
        return listaArtistas;
    }
}
