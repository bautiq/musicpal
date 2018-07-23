package com.example.user.musicpal.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.musicpal.controller.ControllerGlobal;
import com.example.user.musicpal.model.adapters.AdapterCanciones;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.R;
import com.example.user.musicpal.utils.ResultListener;
import com.example.user.musicpal.utils.SimpleDividerItemDecoration;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalleAlbum
        extends Fragment
        implements AdapterCanciones.NotificadorCancionCelda {

    public static final String ALBUM_KEY = "album_key";

    private ImageView imagenGrande;
    private TextView textArtista;
    private TextView textAlbum;

    private RecyclerView recyclerViewCanciones;
    private AdapterCanciones adapterCanciones;
    private List<Cancion> listaCanciones;

    private ControllerGlobal controller;
    private NotificadorCancion notificadorCancion;


    private Album album;

    public static FragmentDetalleAlbum dameUnFragment(Album album) {
        FragmentDetalleAlbum fragmentCreado = new FragmentDetalleAlbum();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ALBUM_KEY, album);
        fragmentCreado.setArguments(bundle);
        return fragmentCreado;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle_album, container, false);
        imagenGrande = view.findViewById(R.id.id_imagen_vista_previa);
        textArtista = view.findViewById(R.id.id_nombre_artista);
        textAlbum = view.findViewById(R.id.id_nombre_album);
        recyclerViewCanciones = view.findViewById(R.id.recycler_canciones_id);

        Bundle bundle = getArguments();
        album = (Album) bundle.getSerializable(ALBUM_KEY);

        controller = new ControllerGlobal(getContext());

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
        adapterCanciones.setListaDeCanciones(album.getListaCanciones());
        chequearListaCanciones();

        textArtista.setText("Artista: " + album.getArtista().getNombre());
        textAlbum.setText("Album: " + album.getTitulo());
        Picasso.get()
                .load(album.getImagenUrl())
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
        notificadorCancion.notificarCancion(cancion, album);
    }

    public interface NotificadorCancion {
        public void notificarCancion(Cancion cancion, Album album);
    }

    public void chequearListaCanciones() {
        if (adapterCanciones.getListaDeCanciones() == null) {
            obtenerCancionesPorAlbum(album);
        }
    }

    public void obtenerCancionesPorAlbum(final Album album) {
        controller.obtenerCancionesPorAlbum(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                adapterCanciones.setListaDeCanciones(resultado);
            }
        }, album.getId());
    }
}
