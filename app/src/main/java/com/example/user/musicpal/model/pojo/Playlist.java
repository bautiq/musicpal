package com.example.user.musicpal.model.pojo;

import java.util.List;

public class Playlist {
private String nombre;
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
