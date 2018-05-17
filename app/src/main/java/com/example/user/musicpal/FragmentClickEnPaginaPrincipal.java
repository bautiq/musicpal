package com.example.user.musicpal;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentClickEnPaginaPrincipal extends Fragment {
    private ImageView imagenGrande;
    private TextView textArtista;
    private TextView textAnio;
    private TextView textAlbum;
    private static final String CLAVE_ARTISTA = "clave_artista";
    private static final String CLAVE_ALBUM = "clave_album";
    private static final String CLAVE_ANIO = "clave_anio";
    private static final String CLAVE_ID_IMAGEN = "clave_id";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_click_en_pagina_principal, container, false);
        imagenGrande = view.findViewById(R.id.id_imagen_vista_previa);
        textArtista = view.findViewById(R.id.id_nombre_artista);
        textAnio = view.findViewById(R.id.id_anio_album);
        Bundle bundle = getArguments();
        textArtista.setText("Artista: " + bundle.getString(CLAVE_ARTISTA));
        textAnio.setText("Año: " + bundle.getString(CLAVE_ANIO));
        imagenGrande.setImageResource(bundle.getInt(CLAVE_ID_IMAGEN));

        return view;
    }


    public static FragmentClickEnPaginaPrincipal fabricaClickPrincipal(String artista, String album, String anio, Integer idImagen) {
        Bundle bundle = new Bundle();
        bundle.putString(CLAVE_ARTISTA, artista);
        bundle.putString(CLAVE_ALBUM, album);
        bundle.putString(CLAVE_ANIO, anio);
        bundle.putInt(CLAVE_ID_IMAGEN, idImagen);
        FragmentClickEnPaginaPrincipal fragmentPrincipal = new FragmentClickEnPaginaPrincipal();
        fragmentPrincipal.setArguments(bundle);
        return fragmentPrincipal;
    }

}
