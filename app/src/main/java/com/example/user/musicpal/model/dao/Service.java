package com.example.user.musicpal.model.dao;

import com.example.user.musicpal.model.pojo.ContenedorAlbum;
import com.example.user.musicpal.model.pojo.ContenedorArtistas;
import com.example.user.musicpal.model.pojo.ContenedorDeCanciones;
import com.example.user.musicpal.model.pojo.ContenedorPlaylist;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("chart/0/albums")
    Call<ContenedorAlbum> obtenerAlbumes(@Query("index") Integer offset,
                                         @Query("limit") Integer limit);

    @GET("chart/0/playlists")
    Call<ContenedorPlaylist> obtenerPlaylist();

    @GET("album/{id}/tracks")
    Call<ContenedorDeCanciones> obtenerCancionesPorAlbumConId(@Path("id") int id);

    @GET("chart/0/artists")
    Call<ContenedorArtistas> obtenerArtista();

    @GET("chart/0/tracks")
    Call<ContenedorDeCanciones> obtenerListaCanciones();

}
