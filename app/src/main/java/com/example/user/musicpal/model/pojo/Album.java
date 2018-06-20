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
    private int id;
    private int imagenAlbum;


    public Album(String titulo, Artista artista) {
        this.titulo = titulo;
        this.artista = artista;

    }

    public int getId() {
        return id;
    }

    public String getImagenUrl() {
        return ImagenUrl;
    }

    public int getImagenAlbum() {
        return imagenAlbum;
    }

    public void setImagenAlbum(int imagenAlbum) {
        this.imagenAlbum = imagenAlbum;
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
        return (albumAComparar.getTitulo().equals(this.titulo));
    }
}
