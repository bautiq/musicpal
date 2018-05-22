package com.example.user.musicpal;

import java.io.Serializable;

/**
 * Created by DH on 15/5/2018.
 */

public class Artista implements Serializable {
    private String nombre;

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
