package com.example.user.musicpal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleFragment extends Fragment {

    public static final String ALBUM_KEY = "album_key";
    private ImageView imagenGrande;
    private TextView textArtista;
    private TextView textAnio;
    private TextView textAlbum;
    private List<Cancion> listaDeCanciones;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        imagenGrande = view.findViewById(R.id.id_imagen_vista_previa);
        textArtista = view.findViewById(R.id.id_nombre_artista);
        textAlbum = view.findViewById(R.id.id_nombre_album);
        textAnio = view.findViewById(R.id.id_anio_album);

        Bundle bundle = getArguments();
        Album album = (Album) bundle.getSerializable(ALBUM_KEY);

        textArtista.setText(album.getArtista().getNombre());
        textAnio.setText(album.getAnio());
        textAlbum.setText(album.getTitulo());
        imagenGrande.setImageResource(album.getImagenAlbum());
        return view;
    }
}
