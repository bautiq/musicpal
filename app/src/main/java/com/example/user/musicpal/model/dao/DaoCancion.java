package com.example.user.musicpal.model.dao;

import android.util.Log;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        chequearSiEstaLogueado();
    }

    private void chequearSiEstaLogueado() {
        if (firebaseAuth.getCurrentUser() != null) {
            databaseReference = database.getReference().child("Users").child(firebaseAuth.getUid()).child("Favoritos");
        }
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

    public void obtenerFavoritosFDB(final ResultListener<Cancion> resultListenerController, final ResultListener<Cancion> listenerCambioController) {
        if (databaseReference != null) {
            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Cancion cancion = dataSnapshot.getValue(Cancion.class);
                    resultListenerController.finish(cancion);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Cancion cancion = dataSnapshot.getValue(Cancion.class);
                    listenerCambioController.finish(cancion);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    resultListenerController.finish(null);
                }
            });
        }


    }

    public void pushearCancion(Cancion cancion) {
        DatabaseReference dataFinal = databaseReference.push();
        cancion.setIdFirebase(dataFinal.getKey());
        dataFinal.setValue(cancion);
    }

    public void eliminarFavFDB(Cancion cancion) {
        if (cancion.getIdFirebase() != null) {
            DatabaseReference dataRefFinal = databaseReference.child(cancion.getIdFirebase());
            dataRefFinal.removeValue(new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    //podria hacer un toast atraves de un notificador
                }
            });
        } else {
            Query query = databaseReference.orderByChild("id").equalTo(cancion.getId());

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot dataSnap : dataSnapshot.getChildren()) {
                            dataSnap.getRef().removeValue();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


    }

    public void obtenerFavPorID(Cancion cancion, final ResultListener<Cancion> resultListener) {

        Query query = databaseReference.orderByChild("id").equalTo(cancion.getId());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.exists())) {
                    resultListener.finish(null);
                    return;
                }
                Cancion cancionRecibida = dataSnapshot.getValue(Cancion.class);
                resultListener.finish(cancionRecibida);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                String TAG = "";
                Log.e(TAG, "onCancelled", databaseError.toException());
                resultListener.finish(null);
            }
        });
    }
}
