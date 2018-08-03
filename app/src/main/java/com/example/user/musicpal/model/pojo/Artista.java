package com.example.user.musicpal.model.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DH on 15/5/2018.
 */
@Entity
public class Artista implements Serializable {

    @ColumnInfo(name = "nombre")
    @SerializedName("name")
    private String nombre;

    @ColumnInfo(name = "imagenUrl")
    @SerializedName("picture_medium")
    private String imagenUrl;

    @ColumnInfo(name = "urlCanciones")
    @SerializedName("tracklist")
    private String cancionesDeArtistaUrl;

    @Ignore
    private List<Cancion> cancionList;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_artista")
    private String id;

    public Artista(){
    }

    @Ignore
    public Artista(String nombre) {
        this.nombre = nombre;
    }


    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public void setCancionesDeArtistaUrl(String cancionesDeArtistaUrl) {
        this.cancionesDeArtistaUrl = cancionesDeArtistaUrl;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public List<Cancion> getCancionList() {
        return cancionList;
    }

    public void setCancionList(List<Cancion> cancionList) {
        this.cancionList = cancionList;
    }

    public String getId() {
        return id;
    }

    public String getCancionesDeArtistaUrl() {
        return cancionesDeArtistaUrl;
    }

    public String getImagenUrl() {
        return imagenUrl;
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
