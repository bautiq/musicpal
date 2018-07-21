package com.example.user.musicpal.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.ControllerGlobal;
import com.example.user.musicpal.model.adapters.AdapterCanciones;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Cancion;

import java.util.List;

public class FragmentBusqueda extends Fragment {

    private EditText editText;

    private ImageView imagenGrande;
    private TextView textArtista;
    private TextView textAlbum;

    private RecyclerView recyclerViewCanciones;
    private AdapterCanciones adapterCanciones;
    private List<Cancion> listaCanciones;

    private ControllerGlobal controller;
    private FragmentDetalleAlbum.NotificadorCancion notificadorCancion;

    private Album album;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_busqueda, container, false);

        imagenGrande = view.findViewById(R.id.imagen_fragment_busqueda);
        textArtista = view.findViewById(R.id.id_textview_nombre_artista_fragment_search);

        recyclerViewCanciones = view.findViewById(R.id.id_recycler_busqueda);





        return view;
    }

}
