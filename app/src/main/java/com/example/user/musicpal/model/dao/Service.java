package com.example.user.musicpal.model.dao;

import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.model.pojo.ContAlbum;
import com.example.user.musicpal.model.pojo.ContArtistas;
import com.example.user.musicpal.model.pojo.ContCanciones;
import com.example.user.musicpal.model.pojo.ContPlaylist;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("chart/0/albums")
    Call<ContAlbum> obtenerAlbumes(@Query("index") Integer offset,
                                   @Query("limit") Integer limit);

    @GET("chart/0/playlists")
    Call<ContPlaylist> obtenerPlaylist(@Query("index") Integer offset,
                                       @Query("limit") Integer limit);

    @GET("album/{id}/tracks")
    Call<ContCanciones> obtenerCancionesPorAlbumConId(@Path("id") String id);

    @GET("chart/0/artists")
    Call<ContArtistas> obtenerArtista(@Query("index") Integer offset,
                                      @Query("limit") Integer limit);

    @GET("chart/0/tracks")
    Call<ContCanciones> obtenerCancionesTop(@Query("index") Integer offset,
                                            @Query("limit") Integer limit);

    @GET("artist/{id}/top")
    Call<ContCanciones> obtenerCancionesPorArtistaConId(@Path("id") String id);

    @GET("playlist/{id}/tracks")
    Call<ContCanciones> obtenerCancionesPorPlaylistConId(@Path("id") String id);

    @GET("track/{id}")
    Call<Cancion> obtenerCancion(@Path("id") String id);
}
