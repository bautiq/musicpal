package com.example.user.musicpal.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Playlist implements Serializable {

    @SerializedName("title")
    private String nombre;
    private int id;

    @SerializedName("picture_medium")
    private String imagenPlaylistUrl;

    public int getId() {
        return id;
    }

    public String getImagenPlaylistUrl() {
        return imagenPlaylistUrl;
    }

    private List<Cancion> listCanciones;

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
}
