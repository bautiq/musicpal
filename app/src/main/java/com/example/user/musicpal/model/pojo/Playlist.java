package com.example.user.musicpal.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Playlist implements Serializable {

    @SerializedName("title")
    private String nombre;

    private String id;

    @SerializedName("picture_medium")
    private String imagenPlaylistUrl;

    private List<Cancion> listCanciones;

    public Playlist(String nombre, List<Cancion> listCanciones, String id){
        this.nombre = nombre;
        this.listCanciones = listCanciones;
        this.id = id;
    }

    public Playlist(){
    }

    public String getId() {
        return id;
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
        if(!(obj instanceof Playlist)){
            return false;
        }else{
            Playlist playlistAcomparar = (Playlist) obj;
            return playlistAcomparar.getId().equals(this.id);
        }
    }
}
