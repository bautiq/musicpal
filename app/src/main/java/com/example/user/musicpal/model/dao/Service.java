package com.example.user.musicpal.model.dao;

import com.example.user.musicpal.model.pojo.ContenedorAlbum;
import com.example.user.musicpal.model.pojo.ContenedorArtist;
import com.example.user.musicpal.model.pojo.ContenedorDeCanciones;
import com.example.user.musicpal.model.pojo.ContenedorPlaylist;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {

    @GET("chart/0/albums")
    Call<ContenedorAlbum> obtenerAlbumes();

    @GET("chart/0/playlists")
    Call<ContenedorPlaylist> obtenerPlaylist();

    @GET("album/{id}/tracks")
    Call<ContenedorDeCanciones> obtenerCancionesPorAlbumConId(@Path("id")int id);

    @GET("album/0/artists")
    Call<ContenedorArtist> obtenerArtista ();
    
}
