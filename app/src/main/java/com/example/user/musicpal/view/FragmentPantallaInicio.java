package com.example.user.musicpal.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.musicpal.controller.ControllerGlobal;
import com.example.user.musicpal.model.adapters.AdapterAlbum;
import com.example.user.musicpal.model.adapters.AdapterArtista;
import com.example.user.musicpal.model.adapters.AdapterPlaylist;
import com.example.user.musicpal.model.adapters.AdapterTopCanciones;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.R;
import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPantallaInicio extends Fragment implements AdapterAlbum.NotificadorAlbumCelda, AdapterArtista.NotificadorArtistaCelda, AdapterPlaylist.NotificadorPlaylistCelda, AdapterTopCanciones.NotificadorTopCancionesCelda {

    private NotificadorActivities notificadorActivities;

    private RecyclerView recyclerViewAlbumesTop;
    private RecyclerView recyclerViewPlaylistsTop;
    private RecyclerView recyclerViewArtistasTop;
    private RecyclerView recyclerViewCancionTop;
    private LinearLayoutManager linearLayoutManagerAlbum;
    private LinearLayoutManager linearLayoutManagerPlaylist;
    private LinearLayoutManager linearLayoutManagerArtista;
    private GridLayoutManager gridLayoutManagerCanciones;
    private AdapterAlbum adapterAlbum;
    private AdapterArtista adapterArtista;
    private AdapterPlaylist adapterPlaylist;
    private AdapterTopCanciones adapterTopCanciones;
    private Boolean isLoading;
    private ControllerGlobal controllerAlbum;
    private ControllerGlobal controllerPlaylist;
    private ControllerGlobal controllerArtista;
    private ControllerGlobal controllerTopCancion;
    private static final int CANTIDAD_ELEMENTOS_PARA_NUEVO_PEDIDO = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pantalla_inicio, container, false);
        isLoading = false;

        adapterAlbum = new AdapterAlbum(getActivity(), this);
        adapterArtista = new AdapterArtista(getActivity(), this);
        adapterPlaylist = new AdapterPlaylist(getActivity(), this);
        adapterTopCanciones = new AdapterTopCanciones(getActivity(), this);

        linearLayoutManagerAlbum = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManagerPlaylist = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManagerArtista = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        gridLayoutManagerCanciones = new GridLayoutManager(getActivity(), 3);

        recyclerViewAlbumesTop = view.findViewById(R.id.recycler_albumes_top_id);
        recyclerViewPlaylistsTop = view.findViewById(R.id.recycler_playlist_top_id);
        recyclerViewArtistasTop = view.findViewById(R.id.recycler_artista_top_id);
        recyclerViewCancionTop = view.findViewById(R.id.recycler_tracks_top_id);

        recyclerViewAlbumesTop.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoading) {
                    return;
                }
                int ultimaPosicion = linearLayoutManagerAlbum.getItemCount();
                int posicionActual = linearLayoutManagerAlbum.findLastVisibleItemPosition();

                if (ultimaPosicion - posicionActual <= CANTIDAD_ELEMENTOS_PARA_NUEVO_PEDIDO) {
                    obtenerAlbumes();
                }

            }
        });

        recyclerViewArtistasTop.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoading) {
                    return;
                }
                int ultimaPosicion = linearLayoutManagerArtista.getItemCount();
                int posicionActual = linearLayoutManagerArtista.findLastVisibleItemPosition();

                if (ultimaPosicion - posicionActual <= CANTIDAD_ELEMENTOS_PARA_NUEVO_PEDIDO) {
                    obtenerArtistas();
                }

            }
        });

        recyclerViewPlaylistsTop.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoading) {
                    return;
                }
                int ultimaPosicion = linearLayoutManagerPlaylist.getItemCount();
                int posicionActual = linearLayoutManagerPlaylist.findLastVisibleItemPosition();

                if (ultimaPosicion - posicionActual <= CANTIDAD_ELEMENTOS_PARA_NUEVO_PEDIDO) {
                    obtenerPlaylists();
                }
            }
        });

        recyclerViewCancionTop.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoading) {
                    return;
                }
                int ultimaPosicion = gridLayoutManagerCanciones.getItemCount();
                int posicionActual = gridLayoutManagerCanciones.findLastVisibleItemPosition();

                if (ultimaPosicion - posicionActual <= CANTIDAD_ELEMENTOS_PARA_NUEVO_PEDIDO) {
                    obtenerCancionesTop();
                }
            }
        });


        setAdapterLinear(recyclerViewAlbumesTop, linearLayoutManagerAlbum, adapterAlbum);
        setAdapterLinear(recyclerViewArtistasTop, linearLayoutManagerArtista, adapterArtista);
        setAdapterTopCanciones(recyclerViewCancionTop, gridLayoutManagerCanciones);
        setAdapterLinear(recyclerViewPlaylistsTop, linearLayoutManagerPlaylist, adapterPlaylist);

        controllerAlbum = new ControllerGlobal(getActivity());
        controllerArtista = new ControllerGlobal(getActivity());
        controllerPlaylist = new ControllerGlobal(getActivity());
        controllerTopCancion = new ControllerGlobal(getActivity());

        obtenerArtistas();
        obtenerAlbumes();
        obtenerPlaylists();
        obtenerCancionesTop();

        return view;
    }

    private void obtenerAlbumes() {
        controllerAlbum.obtenerAlbumesOnline(new ResultListener<List<Album>>() {
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

    private void obtenerArtistas() {
        controllerArtista.obtenerArtistasOnline(new ResultListener<List<Artista>>() {
            @Override
            public void finish(List<Artista> resultado) {
                if (resultado.size() == 0) {
                    Toast.makeText(getContext(), "No se pudo recibir las listas", Toast.LENGTH_SHORT).show();
                } else {
                    adapterArtista.agregarArtistas(resultado);
                }
            }
        });
    }

    private void obtenerPlaylists() {
        controllerPlaylist.obtenerPlaylistOnline(new ResultListener<List<Playlist>>() {
            @Override
            public void finish(List<Playlist> resultado) {
                if (resultado.size() == 0) {
                    Toast.makeText(getContext(), "No se pudo recibir las listas", Toast.LENGTH_SHORT).show();
                } else {
                    adapterPlaylist.agregarPlaylist(resultado);
                }
            }
        });
    }

    private void obtenerCancionesTop() {
        controllerTopCancion.obtenerCancionesTopOnline(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                if (resultado.size() == 0) {
                    Toast.makeText(getContext(), "No se pudo recibir las listas", Toast.LENGTH_SHORT).show();
                } else {
                    adapterTopCanciones.agregarCanciones(resultado);
                }
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
        notificadorActivities.notificarAlbum(list, posicion, categoria);
    }

    @Override
    public void notificarCeldaCliqueadaArtista(List<Artista> artistas, int posicion) {
        notificadorActivities.notificarArtista(artistas, posicion);
    }

    @Override
    public void notificarCeldaCliqueadaPlaylist(List<Playlist> playlists, int posicion) {
        notificadorActivities.notificarPlaylist(playlists, posicion);
    }

    @Override
    public void notificarCeldaClickeadaDeCancion(Cancion cancion, int posicion) {
        notificadorActivities.notificarCancion(cancion, posicion);
    }

    public void setAdapterLinear(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, RecyclerView.Adapter adapter) {
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public void setAdapterTopCanciones(RecyclerView recyclerView, GridLayoutManager gridLayoutManager) {
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterTopCanciones);
    }

    public interface NotificadorActivities {
        public void notificarAlbum(List<Album> listaAlbums, int posicion, String categoria);

        public void notificarArtista(List<Artista> listaArtistas, int posicion);

        public void notificarPlaylist(List<Playlist> listaPlaylist, int posicion);

        public void notificarCancion(Cancion cancion, int posicion);
    }
}
