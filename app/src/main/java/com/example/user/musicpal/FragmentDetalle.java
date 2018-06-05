package com.example.user.musicpal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalle extends Fragment {

    public static final String ALBUM_KEY = "album_key";
    public static final String POSICION_KEY = "posicion_key";
    public static final String ID_RECYCLER_CLICKEADO = "id_recycler_clickeado";
    private ImageView imagenGrande;
    private TextView textArtista;
    private TextView textAnio;
    private TextView textAlbum;
    private RecyclerView recyclerViewCanciones;
    private CancionesAdapter cancionesAdapter;
    private List<Cancion> listaCanciones;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        imagenGrande = view.findViewById(R.id.id_imagen_vista_previa);
        textArtista = view.findViewById(R.id.id_nombre_artista);
        textAlbum = view.findViewById(R.id.id_nombre_album);
        textAnio = view.findViewById(R.id.id_anio_album);
        recyclerViewCanciones = view.findViewById(R.id.recycler_canciones_id);

        Bundle bundle = getArguments();
        Album album = (Album) bundle.getSerializable(ALBUM_KEY);

        listaCanciones = album.getListaCanciones();
        cancionesAdapter = new CancionesAdapter(listaCanciones);
        recyclerViewCanciones.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerViewCanciones.setHasFixedSize(true);
        recyclerViewCanciones.setAdapter(cancionesAdapter);

        textArtista.setText("Artista: " + album.getArtista().getNombre());
        textAnio.setText("Año: " + album.getAnio());
        textAlbum.setText("Album: " + album.getTitulo());
        imagenGrande.setImageResource(album.getImagenAlbum());

        return view;
    }

}
