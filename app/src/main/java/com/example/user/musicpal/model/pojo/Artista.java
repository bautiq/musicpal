package com.example.user.musicpal.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DH on 15/5/2018.
 */

public class Artista implements Serializable {
    @SerializedName("name")
    private String nombre;
    private String imagenUrl;

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
}
