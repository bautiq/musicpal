package com.example.user.musicpal.model.dao;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaoRetroFit {
    private Retrofit retrofit;
    private ServiceAlbum serviceAlbum;

    public DaoRetroFit(Class clase) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder retroBuilder = new Retrofit.Builder()
                .baseUrl("https://api.deezer.com/")
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = retroBuilder.client(httpClient.build()).build();
        clase = retrofit.create(Class.class);
    }
}
