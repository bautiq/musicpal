package com.example.user.musicpal.model.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Playlist implements Serializable {

    @ColumnInfo(name = "nombre")
    @SerializedName("title")
    private String nombre;

    @ColumnInfo(name = "id_playlist")
    @NonNull
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "imagen_url_playlist")
    @SerializedName("picture_medium")
    private String imagenPlaylistUrl;

    @Ignore
    private List<Cancion> listCanciones;

    @Ignore
    public Playlist(String nombre, List<Cancion> listCanciones) {
        this.nombre = nombre;
        this.listCanciones = listCanciones;
    }

    public Playlist() {
    }

    @Ignore
    public Playlist(String nombre) {
        this.nombre = nombre;
    }

    public void setImagenPlaylistUrl(String imagenPlaylistUrl) {
        this.imagenPlaylistUrl = imagenPlaylistUrl;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getImagenPlaylistUrl() {
        return imagenPlaylistUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cancion> getListCanciones() {
        return listCanciones;
    }

    public void setListCanciones(List<Cancion> listCanciones) {
        this.listCanciones = listCanciones;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Playlist)) {
            return false;
        } else {
            Playlist playlistAcomparar = (Playlist) obj;
            return playlistAcomparar.getId().equals(this.id);
        }
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", imagenPlaylistUrl='" + imagenPlaylistUrl + '\'' +
                ", listCanciones=" + listCanciones +
                '}';
    }

}
