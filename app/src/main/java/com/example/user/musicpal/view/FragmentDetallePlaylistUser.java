package com.example.user.musicpal.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;


public class FragmentDetallePlaylistUser extends Fragment implements AdapterCanciones.NotificadorCancionCelda {

    public static final String CLAVE_PLAYLIST = "clave_playlist";
    private ImageView imagenGrande;
    private TextView textArtista;

    private RecyclerView recyclerViewCanciones;
    private AdapterCanciones adapterCanciones;
    private List<Cancion> listaCanciones;

    private ControllerGlobal controllerCancion;
    private FragmentDetallePlaylist.NotificadorCancion notificadorCancion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_detalle_playlist_user, container, false);
        imagenGrande = view.findViewById(R.id.id_imagen_vista_previa_user_Playlist);
        textArtista = view.findViewById(R.id.id_nombre_artista_user_playlist);
        recyclerViewCanciones = view.findViewById(R.id.recycler_canciones_playlist_user_id);
        adapterCanciones = new AdapterCanciones(getFragmentManager(), this, getContext());
        controllerCancion = new ControllerGlobal(getContext());
setearDatos();
        obtenerCanciones();
        return view;
    }

    private void setearDatos() {
        Bundle bundle = getArguments();
        Playlist playlist = (Playlist) bundle.getSerializable(CLAVE_PLAYLIST);

textArtista.setText(playlist.getNombre());
    }

    private void obtenerCanciones() {

    }

    @Override
    public void notificarCeldaClikeada(Cancion cancion) {

    }
}
