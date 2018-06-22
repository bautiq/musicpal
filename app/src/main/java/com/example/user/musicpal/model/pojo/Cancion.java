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
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Cancion(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
