package com.example.user.musicpal.model.dao;

import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.model.pojo.ContCanciones;
import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.ResultListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaoCancion {
    private Retrofit retrofit;
    private Service service;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    public DaoCancion() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder retroBuilder = new Retrofit.Builder()
                .baseUrl("https://api.deezer.com/")
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = retroBuilder.client(httpClient.build()).build();
        service = retrofit.create(Service.class);
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = database.getReference().child("Users").child(firebaseAuth.getUid()).child("Favoritos");
    }

    public void obtenerCancionesPorAlbum(final ResultListener<List<Cancion>> resultListenerDelController,
                                         String id) {

        Call<ContCanciones> call = service.obtenerCancionesPorAlbumConId(id);
        call.enqueue(new Callback<ContCanciones>() {
            @Override
            public void onResponse(Call<ContCanciones> call, Response<ContCanciones> response) {
                ContCanciones contenedorObtenido = response.body();

                if (contenedorObtenido != null && contenedorObtenido.getCanciones() != null) {
                    List<Cancion> listaCancion = contenedorObtenido.getCanciones();
                    resultListenerDelController.finish(listaCancion);
                } else {
                    resultListenerDelController.finish(new ArrayList<Cancion>());
                }
            }

            @Override
            public void onFailure(Call<ContCanciones> call, Throwable t) {
                resultListenerDelController.finish(new ArrayList<Cancion>());
            }
        });
    }

    public void obtenerCancionesPorArtista(final ResultListener<List<Cancion>> resultListenerDelController,
                                           String id) {

        Call<ContCanciones> call = service.obtenerCancionesPorArtistaConId(id);
        call.enqueue(new Callback<ContCanciones>() {
            @Override
            public void onResponse(Call<ContCanciones> call, Response<ContCanciones> response) {
                ContCanciones contenedorObtenido = response.body();

                if (contenedorObtenido != null && contenedorObtenido.getCanciones() != null) {
                    List<Cancion> listaCancion = contenedorObtenido.getCanciones();
                    resultListenerDelController.finish(listaCancion);
                } else {
                    resultListenerDelController.finish(new ArrayList<Cancion>());
                }
            }

            @Override
            public void onFailure(Call<ContCanciones> call, Throwable t) {
                resultListenerDelController.finish(new ArrayList<Cancion>());
            }
        });
    }

    public void obtenerCancionesPorPlaylist(final ResultListener<List<Cancion>> resultListenerDelController,
                                            String id) {

        Call<ContCanciones> call = service.obtenerCancionesPorPlaylistConId(id);
        call.enqueue(new Callback<ContCanciones>() {
            @Override
            public void onResponse(Call<ContCanciones> call, Response<ContCanciones> response) {
                ContCanciones contenedorObtenido = response.body();

                if (contenedorObtenido != null && contenedorObtenido.getCanciones() != null) {
                    List<Cancion> listaCancion = contenedorObtenido.getCanciones();
                    resultListenerDelController.finish(listaCancion);
                } else {
                    resultListenerDelController.finish(new ArrayList<Cancion>());
                }
            }

            @Override
            public void onFailure(Call<ContCanciones> call, Throwable t) {
                resultListenerDelController.finish(new ArrayList<Cancion>());
            }
        });
    }


    public void obtenerCancionesTop(final ResultListener<List<Cancion>> resultListenerDelController,
                                    Integer offset, Integer limit) {

        Call<ContCanciones> call = service.obtenerCancionesTop(offset, limit);
        call.enqueue(new Callback<ContCanciones>() {
            @Override
            public void onResponse(Call<ContCanciones> call, Response<ContCanciones> response) {
                ContCanciones contCancionesObtenido = response.body();

                if (contCancionesObtenido != null && contCancionesObtenido.getCanciones() != null) {
                    List<Cancion> cancionesLista = contCancionesObtenido.getCanciones();
                    resultListenerDelController.finish(cancionesLista);
                } else {
                    resultListenerDelController.finish(new ArrayList<Cancion>());
                }
            }

            @Override
            public void onFailure(Call<ContCanciones> call, Throwable t) {
                resultListenerDelController.finish(new ArrayList<Cancion>());
            }
        });
    }

    public void obtenerCancion(final ResultListener<Cancion> resultListenerDelController,
                               String id) {

        Call<Cancion> call = service.obtenerCancion(id);
        call.enqueue(new Callback<Cancion>() {
            @Override
            public void onResponse(Call<Cancion> call, Response<Cancion> response) {
                Cancion cancionObtenidaOnline = response.body();
                if (cancionObtenidaOnline != null) {
                    Cancion cancionObtenida = cancionObtenidaOnline;
                    resultListenerDelController.finish(cancionObtenida);
                } else {
                    resultListenerDelController.finish(new Cancion());
                }
            }

            @Override
            public void onFailure(Call<Cancion> call, Throwable t) {
                resultListenerDelController.finish(new Cancion());
            }
        });
    }

    public void obtenerBusquedaCancionesEditText(String stringEditText, final ResultListener<List<Cancion>> resultListenerDelController,
                                                 Integer offset, Integer limit) {

        Call<ContCanciones> call = service.obtenerCancionesBusquedaEditText(stringEditText, offset, limit);
        call.enqueue(new Callback<ContCanciones>() {
            @Override
            public void onResponse(Call<ContCanciones> call, Response<ContCanciones> response) {
                ContCanciones contCancionesObtenido = response.body();

                if (contCancionesObtenido != null && contCancionesObtenido.getCanciones() != null) {
                    List<Cancion> cancionesLista = contCancionesObtenido.getCanciones();
                    resultListenerDelController.finish(cancionesLista);
                } else {
                    resultListenerDelController.finish(new ArrayList<Cancion>());
                }
            }

            @Override
            public void onFailure(Call<ContCanciones> call, Throwable t) {
                resultListenerDelController.finish(new ArrayList<Cancion>());
            }
        });
    }

    public void obtenerFavoritosFDB(final ResultListener<Cancion> resultListenerController) {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Cancion cancion  = dataSnapshot.getValue(Cancion.class);
                resultListenerController.finish(cancion);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void pushearListaIdsCanciones(List<String> listaIdsCanciones) {
       databaseReference.setValue(listaIdsCanciones);
    }
}
