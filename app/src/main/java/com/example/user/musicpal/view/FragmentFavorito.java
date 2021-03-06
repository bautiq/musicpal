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
import android.widget.TextView;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.ControllerGlobal;
import com.example.user.musicpal.model.adapters.AdapterCanciones;
import com.example.user.musicpal.model.adapters.AdapterPlaylist;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.NavBarUIupdater;
import com.example.user.musicpal.utils.ResultListener;
import com.example.user.musicpal.utils.SimpleDividerItemDecoration;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavorito
        extends Fragment
        implements AdapterCanciones.NotificadorCancionCelda {

    private TextView textAgregar;
    private RecyclerView recyclerView;
    private FirebaseAuth firebaseAuth;
    private ControllerGlobal controllerGlobal;
    private Intent intent;
    private AdapterCanciones adapterCanciones;
    private NotificadorCancionFavoritaClickeada notificadorCancionFavoritaClickeada;
    private NavBarUIupdater navBarUIupdater;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);
        recyclerView = view.findViewById(R.id.recycler_fragment_favoritos);
        textAgregar = view.findViewById(R.id.text_favoritos_fragment);
        controllerGlobal = new ControllerGlobal(getContext());
        adapterCanciones = new AdapterCanciones(getFragmentManager(),
                this, getContext(), true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        recyclerView.setAdapter(adapterCanciones);
        FirebaseApp.initializeApp(getContext());
        firebaseAuth = FirebaseAuth.getInstance();
        intent = new Intent(getContext(), LoginActivity.class);
        obtenerCancionesFDB();
        chequearSiEstaLogueado();
        return view;
    }

    private void chequearSiHayCanciones() {
        //updatea ui si hay canciones
        if (adapterCanciones.getListaDeCanciones() == null || adapterCanciones.getListaDeCanciones().size() == 0) {
            textAgregar.setVisibility(View.VISIBLE);
        } else {
            textAgregar.setVisibility(View.GONE);
        }
    }

    private void obtenerCancionesFDB() {
        controllerGlobal.obtenerFavoritosFDB(new ResultListener<Cancion>() {
            @Override
            public void finish(Cancion resultado) {
                if (resultado != null) {
                    adapterCanciones.agregarPlaylist(resultado);
                    chequearSiHayCanciones();
                }
            }
        }, new ResultListener<Cancion>() {
            @Override
            public void finish(Cancion resultado) {
                adapterCanciones.eliminarPorCambioEnlaFDB(resultado);
            }
        });
    }

    private void notificarCambiosFav() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificadorCancionFavoritaClickeada = (NotificadorCancionFavoritaClickeada) context;
        navBarUIupdater = (NavBarUIupdater) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        navBarUIupdater.updateUi("favoritos");
        chequearSiHayCanciones();
    }

    @Override
    public void onPause() {
        super.onPause();
        navBarUIupdater.updateUiOnPause("favoritos");
    }

    public void chequearSiEstaLogueado() {
        if (firebaseAuth.getCurrentUser() == null) {
            startActivity(intent);
        }
    }


    @Override
    public void notificarCeldaClikeada(Cancion cancion) {
        notificadorCancionFavoritaClickeada.notificarCancionFavoritaClickeada(cancion);
    }

    @Override
    public void notificarFavorito(Cancion cancion) {
        controllerGlobal.eliminarFavFDB(cancion);
    }

    public interface NotificadorCancionFavoritaClickeada {
        public void notificarCancionFavoritaClickeada(Cancion cancion);
    }
}
