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
    public static final String CLAVE_ARTISTA = "clave_artista";
    public static final String CLAVE_ALBUM = "clave_album";
    public static final String CLAVE_ANIO = "clave_anio";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_click_en_pagina_principal, container, false);
        imagenGrande = view.findViewById(R.id.id_imagen_vista_previa);
        textArtista = view.findViewById(R.id.id_nombre_artista);
        textAlbum = view.findViewById(R.id.id_nombre_album);
        textAnio = view.findViewById(R.id.id_anio_album);
        Bundle bundle = getArguments();
        textArtista.setText("Artista: " + bundle.getString(CLAVE_ARTISTA));
        textAnio.setText("Año: " + bundle.getString(CLAVE_ANIO));
        textAlbum.setText("Album: " + bundle.getString(CLAVE_ALBUM));
        imagenGrande.setImageResource(R.drawable.meteora_album);

        return view;
    }
}
