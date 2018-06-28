package com.example.user.musicpal.model.dao;

import android.content.Context;

import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.model.pojo.ContenedorAlbum;
import com.example.user.musicpal.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaoAlbum {

    private Retrofit retrofit;
    private Service service;

    public DaoAlbum() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder retroBuilder = new Retrofit.Builder()
                .baseUrl("https://api.deezer.com/")
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = retroBuilder.client(httpClient.build()).build();
        service = retrofit.create(Service.class);
    }

    public void obtenerAlbumes(final ResultListener<List<Album>> resultListenerDelController, Integer offset, Integer limit) {

        Call<ContenedorAlbum> call = service.obtenerAlbumes(offset, limit);
        call.enqueue(new Callback<ContenedorAlbum>() {
            @Override
            public void onResponse(Call<ContenedorAlbum> call, Response<ContenedorAlbum> response) {
                ContenedorAlbum contenedorAlbumObtenido = response.body();

                if (contenedorAlbumObtenido != null && contenedorAlbumObtenido.obtenerListaDeAlbumes() != null) {
                    List<Album> albumLista = contenedorAlbumObtenido.obtenerListaDeAlbumes();
                    resultListenerDelController.finish(albumLista);
                } else {
                    resultListenerDelController.finish(new ArrayList<Album>());
                }
            }

            @Override
            public void onFailure(Call<ContenedorAlbum> call, Throwable t) {
                resultListenerDelController.finish(new ArrayList<Album>());
            }
        });
    }
}


