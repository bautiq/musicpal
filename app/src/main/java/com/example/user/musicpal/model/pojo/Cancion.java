package com.example.user.musicpal.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by DH on 15/5/2018.
 */

public class Cancion implements Serializable {
    private String titulo;

    public Cancion(String titulo) {
        this.titulo = titulo;
    }

    protected Cancion(Parcel in) {
        titulo = in.readString();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


}
