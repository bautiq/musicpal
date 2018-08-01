package com.example.user.musicpal.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.ControllerGlobal;
import com.example.user.musicpal.model.adapters.AdapterCanciones;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.ResultListener;
import com.example.user.musicpal.utils.SimpleDividerItemDecoration;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetallePlaylist
        extends Fragment
        implements AdapterCanciones.NotificadorCancionCelda {

    public static final String PLAYLIST_KEY_PAGER = "playlist_key";

    private ImageView imagenGrande;
    private TextView textNombrePlaylist;

    private RecyclerView recyclerViewCanciones;
    private AdapterCanciones adapterCanciones;
    private List<Cancion> listaCanciones;

    private ControllerGlobal controllerCancion;
    private NotificadorCancion notificadorCancion;
    private Playlist playlist;

    public static FragmentDetallePlaylist dameUnFragment(Playlist playlist) {
        FragmentDetallePlaylist fragmentCreado = new FragmentDetallePlaylist();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PLAYLIST_KEY_PAGER, playlist);
        fragmentCreado.setArguments(bundle);
        return fragmentCreado;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_playlist, container, false);
        imagenGrande = view.findViewById(R.id.id_imagen_vista_previa_Playlist);
        textNombrePlaylist = view.findViewById(R.id.id_nombre_artista_playlist);
        textNombrePlaylist.setSelected(true);
        recyclerViewCanciones = view.findViewById(R.id.recycler_canciones_playlist_id);

        Bundle bundle = getArguments();
        playlist = (Playlist) bundle.getSerializable(PLAYLIST_KEY_PAGER);

        controllerCancion = new ControllerGlobal(getContext());

        listaCanciones = new ArrayList<>();
        adapterCanciones = new AdapterCanciones(listaCanciones,
                getActivity().getSupportFragmentManager(),
                this, getContext(), false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false);

        recyclerViewCanciones.setLayoutManager(linearLayoutManager);
        recyclerViewCanciones.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recyclerViewCanciones.setHasFixedSize(true);
        recyclerViewCanciones.setAdapter(adapterCanciones);

        adapterCanciones.setListaDeCanciones(playlist.getListCanciones());
        chequearListaCanciones();
        textNombrePlaylist.setText(playlist.getNombre());
        Picasso.get()
                .load(playlist.getImagenPlaylistUrl())
                .placeholder(R.drawable.placeholder)
                .into(imagenGrande);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificadorCancion = (NotificadorCancion) context;
    }

    @Override
    public void notificarCeldaClikeada(Cancion cancion) {
        notificadorCancion.notificarCancion(cancion, playlist);
    }

    @Override
    public void notificarFavorito(Cancion cancion) {
        controllerCancion.pushearOeliminarCancion(cancion);
    }

    public interface NotificadorCancion {
        public void notificarCancion(Cancion cancion, Playlist playlist);
    }

    public void chequearListaCanciones() {
        if (adapterCanciones.getListaDeCanciones() == null) {
            obtenerCancionesPorPlaylist(playlist);
        }
    }

    private void obtenerCancionesPorPlaylist(final Playlist playlist) {
        controllerCancion.obtenerCancionesPorPlaylist(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                adapterCanciones.setListaDeCanciones(resultado);
            }
        }, playlist.getId());
    }
}