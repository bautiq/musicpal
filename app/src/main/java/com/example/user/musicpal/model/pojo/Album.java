package com.example.user.musicpal.model.pojo;

import android.content.Context;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class Album implements Serializable {

    @SerializedName("title")
    private String titulo;

    @SerializedName("artist")
    private Artista artista;

    @SerializedName("cover_medium")
    private String ImagenUrl;

    private String id;

    private List<Cancion> listaCanciones;

    public Album(String titulo, Artista artista, String imagenUrl) {
        this.titulo = titulo;
        this.artista = artista;
        this.ImagenUrl = imagenUrl;
    }

    public Album(String titulo, String imagenUrl) {
        this.titulo = titulo;
        this.ImagenUrl = imagenUrl;
    }

    public void setListaCanciones(List<Cancion> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }

    public List<Cancion> getListaCanciones() {
        return listaCanciones;
    }

    public String getId() {
        return id;
    }

    public String getImagenUrl() {
        return ImagenUrl;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Album)) {
            return false;
        }
        Album albumAComparar = (Album) obj;
        return albumAComparar.getId().equals(this.id);
    }

    @Override
    public String toString() {
        return getTitulo();
    }
}
