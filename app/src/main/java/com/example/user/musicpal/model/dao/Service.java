package com.example.user.musicpal.model.dao;

import com.example.user.musicpal.model.pojo.ContenedorAlbum;
import com.example.user.musicpal.model.pojo.ContenedorDeCanciones;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("chart/0/albums")
    Call<ContenedorAlbum> obtenerAlbumes();

    @GET("album/{id}/tracks")
    Call<ContenedorDeCanciones>obtenerCancionesPorAlbum  (@Path("id")int id);
    
}
