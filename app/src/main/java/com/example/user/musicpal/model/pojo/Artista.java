package com.example.user.musicpal.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DH on 15/5/2018.
 */

public class Artista implements Serializable {

    @SerializedName("name")
    private String nombre;

    @SerializedName("picture_medium")
    private String imagenUrl;

    @SerializedName("tracklist")
    private String cancionesDeArtistaUrl;

    private List<Cancion> cancionList;

    public List<Cancion> getCancionList() {
        return cancionList;
    }

    public void setCancionList(List<Cancion> cancionList) {
        this.cancionList = cancionList;
    }

    private String id;

    public String getId() {
        return id;
    }

    public String getCancionesDeArtistaUrl() {
        return cancionesDeArtistaUrl;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public Artista(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Artista )){
            return false;
        }else{
            Artista artistaAcomparar = (Artista) obj;
            return artistaAcomparar.getId().equals(this.id);
        }
    }
}
