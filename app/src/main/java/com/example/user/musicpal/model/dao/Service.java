package com.example.user.musicpal.model.dao;

import com.example.user.musicpal.model.pojo.ContenedorAlbum;
import com.example.user.musicpal.model.pojo.ContenedorCanciones;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("chart/0/albums")
    Call<ContenedorAlbum> obtenerAlbumes();


}
