package com.example.user.musicpal.model.dao;


import com.example.user.musicpal.model.pojo.ContenedorPlaylist;
import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlaylistDao {
    private Retrofit retrofit;
    private Service service;

    public PlaylistDao() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.deezer.com/")
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(httpClient.build()).build();
        service = retrofit.create(Service.class);
    }

    public void obtenerPlaylist(final ResultListener<List<Playlist>> resultListenerDelController, Integer offset, Integer limit) {

        Call<ContenedorPlaylist> call = service.obtenerPlaylist(offset, limit);
        call.enqueue(new Callback<ContenedorPlaylist>() {
            @Override
            public void onResponse(Call<ContenedorPlaylist> call, Response<ContenedorPlaylist> response) {
                ContenedorPlaylist contenedorPlaylistObtenido = response.body();

                if (contenedorPlaylistObtenido != null && contenedorPlaylistObtenido.getPlaylists() != null) {
                    List<Playlist> playlistsLista = contenedorPlaylistObtenido.getPlaylists();
                    resultListenerDelController.finish(playlistsLista);
                } else {
                    resultListenerDelController.finish(new ArrayList<Playlist>());
                }
            }

            @Override
            public void onFailure(Call<ContenedorPlaylist> call, Throwable t) {
                resultListenerDelController.finish(new ArrayList<Playlist>());
            }
        });
    }
}
