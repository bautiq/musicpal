package com.example.user.musicpal.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.musicpal.controller.ControllerAlbum;
import com.example.user.musicpal.controller.ControllerArtista;
import com.example.user.musicpal.model.adapters.AdapterAlbum;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.R;
import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPantallaInicio extends Fragment implements AdapterAlbum.NotificadorAlbumCelda {

    private NotificadorActivities notificadorActivities;

    private RecyclerView recyclerViewAlbumesTop;
    private RecyclerView recyclerViewPlaylistsTop;
    private RecyclerView recyclerViewArtistasTop;
    private RecyclerView recyclerViewTracksTop;
    private AdapterAlbum adapterAlbum;
    private ControllerAlbum controllerAlbum;
    private ControllerArtista controllerArtista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pantalla_inicio, container, false);

        adapterAlbum = new AdapterAlbum(getActivity(), this);
        recyclerViewAlbumesTop = view.findViewById(R.id.recycler_albumes_top_id);
        recyclerViewPlaylistsTop = view.findViewById(R.id.recycler_playlist_top_id);
        recyclerViewArtistasTop = view.findViewById(R.id.recycler_artista_top_id);
        recyclerViewTracksTop = view.findViewById(R.id.recycler_tracks_top_id);

        setAdapterAlbums(recyclerViewAlbumesTop);
        setAdapterAlbums(recyclerViewArtistasTop);
        setAdapterAlbums(recyclerViewTracksTop);
        setAdapterAlbums(recyclerViewPlaylistsTop);

        controllerAlbum = new ControllerAlbum(getActivity());
        controllerArtista = new ControllerArtista();

        obtenerAlbumes();
        return view;
    }

    private void obtenerAlbumes() {
        controllerAlbum.obtenerAlbumes(new ResultListener<List<Album>>() {
            @Override
            public void finish(List<Album> resultado) {
                if (resultado.size() == 0) {
                    Toast.makeText(getContext(), "No se pudo recibir las listas", Toast.LENGTH_SHORT).show();
                } else {
                    adapterAlbum.agregarAlbumes(resultado);
                }

            }
        });
    }
    

    private void obtenerArtistas(){
        controllerArtista.obtenerArtistas(new ResultListener<List<Artista>>() {
            @Override
            public void finish(List<Artista> resultado) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificadorActivities = (NotificadorActivities) context;
    }

    @Override
    public void notificarCeldaClickeada(List<Album> list, int posicion, String categoria) {
        notificadorActivities.notificar(list, posicion, categoria);
    }

    public void setAdapterAlbums(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterAlbum);
    }



    public interface NotificadorActivities {
        public void notificar(List<Album> listaAlbums, int posicion, String categoria);
        public void notificarArtista(Artista artista);
    }

}
