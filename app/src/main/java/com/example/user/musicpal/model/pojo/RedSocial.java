package com.example.user.musicpal.model.pojo;

public class RedSocial {
    private String nombre;
    private int imagenRedSocial;

    public RedSocial(String nombre, int imagenRedSocial) {
        this.nombre = nombre;
        this.imagenRedSocial = imagenRedSocial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagenRedSocial() {
        return imagenRedSocial;
    }

    public void setImagenRedSocial(int imagenRedSocial) {
        this.imagenRedSocial = imagenRedSocial;
    }
}
