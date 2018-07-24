package com.example.user.musicpal.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.ControllerGlobal;
import com.example.user.musicpal.model.adapters.AdapterCanciones;
import com.example.user.musicpal.model.adapters.AdapterPlaylist;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.ResultListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavorito
        extends Fragment
        implements AdapterPlaylist.NotificadorPlaylistUser,
        AdapterCanciones.NotificadorCancionCelda {

    private TextView textAgregar;
    private RecyclerView recyclerView;
    private FirebaseAuth firebaseAuth;
    private ControllerGlobal controllerGlobal;
    private Intent intent;
    private AdapterCanciones adapterCanciones;
    private NotificadorPlaylistUserClickeada notificadorPlaylistUserClickeada;
    private ImageView imagenFav;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);
        recyclerView = view.findViewById(R.id.recycler_fragment_favoritos);
        textAgregar = view.findViewById(R.id.text_favoritos_fragment);
        controllerGlobal = new ControllerGlobal(getContext());
        adapterCanciones = new AdapterCanciones(getFragmentManager(),
                this,
                getContext(),
                true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,
                false));

        recyclerView.setAdapter(adapterCanciones);
        FirebaseApp.initializeApp(getContext());
        firebaseAuth = FirebaseAuth.getInstance();
        intent = new Intent(getContext(), LoginActivity.class);
        obtenerCancionesFDB();
        chequearSiEstaLogueado();
        chequearSiHayCanciones();
        return view;
    }

    private void chequearSiHayCanciones() {
        //updatea ui si hay canciones
        if (adapterCanciones.getListaDeCanciones() != null && adapterCanciones.getListaDeCanciones().size() > 0) {
            textAgregar.setVisibility(View.INVISIBLE);
        }
    }

    private void obtenerCancionesFDB() {
        controllerGlobal.obtenerFavoritosFDB(new ResultListener<Cancion>() {
            @Override
            public void finish(Cancion resultado) {
                if (resultado != null) {
                    adapterCanciones.agregarPlaylist(resultado);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificadorPlaylistUserClickeada = (NotificadorPlaylistUserClickeada) context;
    }


    public void chequearSiEstaLogueado() {
        if (firebaseAuth.getCurrentUser() == null) {
            startActivity(intent);
        }
    }


    @Override
    public void notificarPlaylistUser(Playlist playlist) {
        notificadorPlaylistUserClickeada.notificarPlaylistUserClickeada(playlist);
    }

    @Override
    public void notificarCeldaClikeada(Cancion cancion) {

    }

    @Override
    public void notificarFavorito(Cancion cancion) {
        controllerGlobal.eliminarFavFDB(cancion);
    }

    public interface NotificadorPlaylistUserClickeada {
        public void notificarPlaylistUserClickeada(Playlist playlist);
    }
}
