package com.example.user.musicpal.model.dao;

import com.example.user.musicpal.model.pojo.ContenedorAlbum;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceAlbum {

    @GET("album")
    Call<ContenedorAlbum> getAlbum(@Query("album") String albumAencontrar);

}
