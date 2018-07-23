package com.example.user.musicpal.model.dao;


import com.example.user.musicpal.model.pojo.ContPlaylist;
import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.ResultListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaoPlaylist {

    private Retrofit retrofit;
    private Service service;


    public DaoPlaylist() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.deezer.com/")
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(httpClient.build()).build();
        service = retrofit.create(Service.class);

    }

    public void obtenerPlaylist(final ResultListener<List<Playlist>> resultListenerDelController,
                                Integer offset, Integer limit) {

        Call<ContPlaylist> call = service.obtenerPlaylist(offset, limit);
        call.enqueue(new Callback<ContPlaylist>() {
            @Override
            public void onResponse(Call<ContPlaylist> call, Response<ContPlaylist> response) {
                ContPlaylist contPlaylistObtenido = response.body();

                if (contPlaylistObtenido != null && contPlaylistObtenido.getPlaylists() != null) {
                    List<Playlist> playlistsLista = contPlaylistObtenido.getPlaylists();
                    resultListenerDelController.finish(playlistsLista);
                } else {
                    resultListenerDelController.finish(new ArrayList<Playlist>());
                }
            }

            @Override
            public void onFailure(Call<ContPlaylist> call, Throwable t) {
                resultListenerDelController.finish(new ArrayList<Playlist>());
            }
        });
    }






}
