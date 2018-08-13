package com.example.user.musicpal.model.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.support.annotation.NonNull;

import com.example.user.musicpal.model.dao.room.AlbumConverter;
import com.example.user.musicpal.model.dao.room.ArtistaConverter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Cancion implements Serializable {

    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("preview")
    private String urlPreview;

    @TypeConverters(ArtistaConverter.class)
    @SerializedName("artist")
    private Artista artista;

    @TypeConverters(AlbumConverter.class)
    @SerializedName("album")
    private Album album;

    public Album getAlbum() {
        return album;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    private String idFirebase;

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getIdFirebase() {
        return idFirebase;
    }

    public void setIdFirebase(String idFirebase) {
        this.idFirebase = idFirebase;
    }

    public Cancion(String title, String urlPreview, Artista artista, Album album, String id) {
        this.title = title;
        this.urlPreview = urlPreview;
        this.artista = artista;
        this.album = album;
        this.id = id;
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

    public Artista getArtista() {
        return artista;
    }

    public String getUrlPreview() {
        return urlPreview;
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
        return cancionAComparar.getId().equals(this.id);
    }
}
