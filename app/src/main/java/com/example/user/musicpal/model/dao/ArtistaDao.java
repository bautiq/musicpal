package com.example.user.musicpal.model.dao;

import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.model.pojo.ContenedorArtistas;
import com.example.user.musicpal.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistaDao {
    private Retrofit retrofit;
    private Service service;

    public ArtistaDao(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.deezer.com/")
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(httpClient.build()).build();
        service = retrofit.create(Service.class);
    }

    public void obtenerArtistas(final ResultListener<List<Artista>> resultListenerController){

        Call<ContenedorArtistas> call = service.obtenerArtista();

        call.enqueue(new Callback<ContenedorArtistas>() {

            @Override
            public void onResponse(Call<ContenedorArtistas> call, Response<ContenedorArtistas> response) {
                ContenedorArtistas contenedorArtista = response.body();
                if (contenedorArtista != null && contenedorArtista.obtenerListaDeArtistas() != null){
                    resultListenerController.finish(contenedorArtista.obtenerListaDeArtistas());
                }else{
                    resultListenerController.finish(new ArrayList<Artista>());
                }
            }

            @Override
            public void onFailure(Call<ContenedorArtistas> call, Throwable t) {
                resultListenerController.finish(new ArrayList<Artista>());
            }
        });

    }
}
