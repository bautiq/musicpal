package com.example.user.musicpal.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DH on 15/5/2018.
 */

public class Album implements Serializable {
    @SerializedName("title")
    private String titulo;
    private List<Cancion> listaCanciones;
    private Artista artista;
    private int imagenAlbum;
    private String anio;

    public Album(String titulo, Artista artista, String anio, int imagenAlbum, List<Cancion> listaCanciones) {
        this.titulo = titulo;
        this.anio = anio;
        this.artista = artista;
        this.imagenAlbum = imagenAlbum;
        this.listaCanciones = listaCanciones;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getAnio() {
        return anio;
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
