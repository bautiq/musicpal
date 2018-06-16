package com.example.user.musicpal.model.pojo;

import android.content.Context;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DH on 15/5/2018.
 */

public class Album implements Serializable {
    @SerializedName("title")
    private String titulo;
    @SerializedName("tracks")
    private List<Cancion> listaCanciones;
    @SerializedName("artist")
    private Artista artista;
    @SerializedName("cover_medium")
    private String url;
    private int imagenAlbum;


    public Album(String titulo, Artista artista, List<Cancion> listaCanciones) {
        this.titulo = titulo;
        this.artista = artista;
        this.listaCanciones = listaCanciones;
    }

    public String getUrl() {
        return url;
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

    public List<Cancion> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(List<Cancion> listaCanciones) {
        this.listaCanciones = listaCanciones;
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
