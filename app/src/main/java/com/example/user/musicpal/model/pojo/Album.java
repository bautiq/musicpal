package com.example.user.musicpal.model.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.example.user.musicpal.model.dao.room.ArtistaConverter;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

@Entity
public class Album implements Serializable {

    @ColumnInfo(name = "titulo")
    @SerializedName("title")
    private String titulo;

    @TypeConverters(ArtistaConverter.class)
    @Embedded
    @SerializedName("artist")
    private Artista artista;

    @ColumnInfo(name = "cover_medium")
    @SerializedName("cover_medium")
    private String ImagenUrl;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_album")
    private String id;

    @Ignore
    private List<Cancion> listaCanciones;

    public Album(String titulo, Artista artista, String imagenUrl) {
        this.titulo = titulo;
        this.artista = artista;
        this.ImagenUrl = imagenUrl;
    }

    public Album(String titulo, String imagenUrl) {
        this.titulo = titulo;
        this.ImagenUrl = imagenUrl;
    }

    public Album() {
    }

    public void setImagenUrl(String imagenUrl) {
        ImagenUrl = imagenUrl;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setListaCanciones(List<Cancion> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }

    public List<Cancion> getListaCanciones() {
        return listaCanciones;
    }

    public String getId() {
        return id;
    }

    public String getImagenUrl() {
        return ImagenUrl;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Album)) {
            return false;
        }
        Album albumAComparar = (Album) obj;
        return albumAComparar.getId().equals(this.id);
    }

    @Override
    public String toString() {
        return getTitulo();
    }
}
