package com.example.user.musicpal.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Playlist implements Serializable {

    @SerializedName("title")
    private String nombre;

    private String id;

    @SerializedName("picture_medium")
    private String imagenPlaylistUrl;

    private List<Cancion> listCanciones;
    private List<String> listaCancionesIDS;


    public Playlist(String nombre, List<Cancion> listCanciones){
        this.nombre = nombre;
        this.listCanciones = listCanciones;
    }

    public Playlist(){
    }

    public Playlist(List<String> listaCancionesIDS, String nombre) {
        this.nombre = nombre;
        this.listaCancionesIDS = listaCancionesIDS;
    }

    public List<String> getListaCancionesIDS() {
        return listaCancionesIDS;
    }

    public void setListaCancionesIDS(List<String> listaCancionesIDS) {
        this.listaCancionesIDS = listaCancionesIDS;
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

    public void setId(String id) {
        this.id = id;
    }
}
