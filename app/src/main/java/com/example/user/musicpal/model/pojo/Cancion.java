package com.example.user.musicpal.model.pojo;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by DH on 15/5/2018.
 */

public class Cancion implements Serializable {

    private String title;
    @SerializedName("preview")
    private String urlPreview;

    private int imagenCancion;
    @SerializedName("artist")
    private Artista artista;

    @SerializedName("album")
    private Album album;

    public Album getAlbum() {
        return album;
    }

    private String id;

    private int duration;

    public int getDuration() {
        return duration;
    }

    public Cancion() {
    }


    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrlPreview(String urlPreview) {
        this.urlPreview = urlPreview;
    }

    public int getImagenCancion() {
        return imagenCancion;
    }

    public Artista getArtista() {
        return artista;
    }

    public String getUrlPreview() {
        return urlPreview;
    }

    public Cancion(String title, String urlPreview, Artista artista, Album album, String id) {
        this.title = title;
        this.urlPreview = urlPreview;
        this.artista = artista;
        this.album = album;
        this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cancion)) {
            return false;
        }
        Cancion cancionAComparar = (Cancion) obj;
        return cancionAComparar.getId().equals(this.id) ;
    }
}
