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
import com.google.firebase.database.ValueEventListener;*/

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
 //   private FirebaseDatabase database;
  //  private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    public DaoPlaylist() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.deezer.com/")
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(httpClient.build()).build();
        service = retrofit.create(Service.class);
 //       database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
  //      databaseReference = database.getReference().child("Users").child(firebaseAuth.getUid()).child("playlists");
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

 /*   public void pushearPlaylistAFDB(Playlist playlistAsubir) {
        DatabaseReference databaseReferenceFinal = databaseReference.push();
        playlistAsubir.setId(databaseReferenceFinal.getKey());
        databaseReferenceFinal.setValue(playlistAsubir);
    }

    public void obtenerPlaylistFDB(final ResultListener<Playlist> resultListenerController) {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Playlist playlist = dataSnapshot.getValue(Playlist.class);
                resultListenerController.finish(playlist);
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

    public void pushearListaIdsCanciones(List<String> listaIdsCanciones, Playlist playlistNueva) {
        DatabaseReference databaseReferenceFinal = databaseReference
                .child(playlistNueva.getId())
                .child("IdList");
        databaseReferenceFinal.setValue(listaIdsCanciones);
    }*/
}
