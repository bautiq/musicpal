package com.example.user.musicpal.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.musicpal.controller.ControllerCancion;
import com.example.user.musicpal.model.adapters.CancionesAdapter;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.R;
import com.example.user.musicpal.utils.ResultListener;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalle extends Fragment {

    public static final String ALBUM_KEY = "album_key";


    private ImageView imagenGrande;
    private TextView textArtista;
    private TextView textAlbum;
    private RecyclerView recyclerViewCanciones;
    private CancionesAdapter cancionesAdapter;
    private List<Cancion> listaCanciones;
    private ControllerCancion controllerCancion;

    public static FragmentDetalle dameUnFragment(Album album) {
        FragmentDetalle fragmentCreado = new FragmentDetalle();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ALBUM_KEY, album);
        fragmentCreado.setArguments(bundle);
        return fragmentCreado;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        imagenGrande = view.findViewById(R.id.id_imagen_vista_previa);
        textArtista = view.findViewById(R.id.id_nombre_artista);
        textAlbum = view.findViewById(R.id.id_nombre_album);
        recyclerViewCanciones = view.findViewById(R.id.recycler_canciones_id);

        Bundle bundle = getArguments();
        Album album = (Album) bundle.getSerializable(ALBUM_KEY);


        controllerCancion = new ControllerCancion();

        listaCanciones = new ArrayList<>();
        cancionesAdapter = new CancionesAdapter(listaCanciones, getActivity().getSupportFragmentManager());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), linearLayoutManager.getOrientation());


        recyclerViewCanciones.setLayoutManager(linearLayoutManager);
        recyclerViewCanciones.addItemDecoration(dividerItemDecoration);
        recyclerViewCanciones.setHasFixedSize(true);
        recyclerViewCanciones.setAdapter(cancionesAdapter);

        obtenerCancionesPorAlbum(album);

        textArtista.setText("Artista: " + album.getArtista().getNombre());
        textAlbum.setText("Album: " + album.getTitulo());
        Picasso.with(getContext()).load(album.getImagenUrl()).placeholder(R.drawable.placeholder).into(imagenGrande);

        return view;
    }

    public void obtenerCancionesPorAlbum(Album album){
        controllerCancion.obtenerCancionesPorAlbum(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                cancionesAdapter.setListaDeCanciones(resultado);
            }
        }, album.getId());
    }

}
