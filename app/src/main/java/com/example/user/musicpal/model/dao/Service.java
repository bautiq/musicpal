package com.example.user.musicpal.model.dao;

import com.example.user.musicpal.model.pojo.Cancion;
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
    Call<ContenedorPlaylist> obtenerPlaylist(@Query("index") Integer offset,
                                             @Query("limit") Integer limit);

    @GET("album/{id}/tracks")
    Call<ContenedorDeCanciones> obtenerCancionesPorAlbumConId(@Path("id") String id);

    @GET("chart/0/artists")
    Call<ContenedorArtistas> obtenerArtista(@Query("index") Integer offset,
                                            @Query("limit") Integer limit);

    @GET("chart/0/tracks")
    Call<ContenedorDeCanciones> obtenerCancionesTop(@Query("index") Integer offset,
                                                    @Query("limit") Integer limit);

    @GET("artist/{id}/top")
    Call<ContenedorDeCanciones> obtenerCancionesPorArtistaConId(@Path("id") String id);

    @GET("playlist/{id}/tracks")
    Call<ContenedorDeCanciones> obtenerCancionesPorPlaylistConId(@Path("id") String id);

    @GET("track/{id}")
    Call<Cancion> obtenerCancion(@Path("id") String id);
}
