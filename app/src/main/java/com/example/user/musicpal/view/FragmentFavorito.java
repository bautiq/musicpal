package com.example.user.musicpal.view;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
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
        implements AdapterPlaylist.NotificadorPlaylistUser, AdapterCanciones.NotificadorCancionCelda {

    private TextView textAgregar;
    private ImageView buttonAgregar;
    private RecyclerView recyclerView;
    private FirebaseAuth firebaseAuth;
    private ControllerGlobal controllerGlobal;
    private Intent intent;
    private AdapterCanciones adapterCanciones;
    private NotificadorPlaylistUserClickeada notificadorPlaylistUserClickeada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        recyclerView = view.findViewById(R.id.recycler_fragment_favoritos);
        controllerGlobal = new ControllerGlobal(getContext());
        adapterCanciones = new AdapterCanciones(getFragmentManager(), this, getContext(), true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapterCanciones);
        FirebaseApp.initializeApp(getContext());
        firebaseAuth = FirebaseAuth.getInstance();
        intent = new Intent(getContext(), LoginActivity.class);
        chequearSiEstaLogueado();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificadorPlaylistUserClickeada = (NotificadorPlaylistUserClickeada) context;
    }

    public void obtenerIdsYarmarListaCanciones() {
        List<String> listIds = new ArrayList<>();
        for (Cancion cancion : adapterCanciones.getListaDeCanciones()) {
            listIds.add(cancion.getId());
        }
    }

    private void pushearListaIdsCanciones(List<String> listaIdsCanciones) {

        controllerGlobal.pushearListaIdsCanciones(listaIdsCanciones);
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

    public interface NotificadorPlaylistUserClickeada {
        public void notificarPlaylistUserClickeada(Playlist playlist);
    }
}
