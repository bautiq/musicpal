package com.example.user.musicpal.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.musicpal.controller.ControllerGlobal;
import com.example.user.musicpal.controller.RoomControllerAlbum;
import com.example.user.musicpal.controller.RoomControllerArtista;
import com.example.user.musicpal.controller.RoomControllerCancion;
import com.example.user.musicpal.controller.RoomControllerPlaylist;
import com.example.user.musicpal.model.adapters.AdapterAlbum;
import com.example.user.musicpal.model.adapters.AdapterArtista;
import com.example.user.musicpal.model.adapters.AdapterPlaylist;
import com.example.user.musicpal.model.adapters.AdapterTopCanciones;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.R;
import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.NavBarUIupdater;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPantallaInicio
        extends Fragment
        implements AdapterAlbum.NotificadorAlbumCelda,
        AdapterArtista.NotificadorArtistaCelda,
        AdapterPlaylist.NotificadorPlaylistCelda,
        AdapterTopCanciones.NotificadorTopCancionesCelda {

    private NotificadorActivities notificadorActivities;

    private RecyclerView recyclerViewAlbumesTop;
    private RecyclerView recyclerViewPlaylistsTop;
    private RecyclerView recyclerViewArtistasTop;
    private RecyclerView recyclerViewCancionTop;

    private LinearLayoutManager linearLayoutManagerAlbum;
    private LinearLayoutManager linearLayoutManagerPlaylist;
    private LinearLayoutManager linearLayoutManagerArtista;
    private LinearLayoutManager linearLayoutManagerCanciones;

    private AdapterAlbum adapterAlbum;
    private AdapterArtista adapterArtista;
    private AdapterPlaylist adapterPlaylist;
    private AdapterTopCanciones adapterTopCanciones;

    private Boolean isLoading;

    private ControllerGlobal controllerAlbum;
    private ControllerGlobal controllerPlaylist;
    private ControllerGlobal controllerArtista;
    private ControllerGlobal controllerTopCancion;

    private static final int CANTIDAD_ELEMENTOS_PARA_NUEVO_PEDIDO = 8;
    private NavBarUIupdater navBarUIupdater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pantalla_inicio, container, false);
        isLoading = false;

        adapterAlbum = new AdapterAlbum(getActivity(), this);
        adapterArtista = new AdapterArtista(getActivity(), this);
        adapterPlaylist = new AdapterPlaylist(getActivity(), this);
        adapterTopCanciones = new AdapterTopCanciones(getActivity(), this);

        linearLayoutManagerAlbum = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
        linearLayoutManagerPlaylist = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
        linearLayoutManagerArtista = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
        linearLayoutManagerCanciones = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);

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
                int ultimaPosicion = linearLayoutManagerCanciones.getItemCount();
                int posicionActual = linearLayoutManagerCanciones.findLastVisibleItemPosition();

                if (ultimaPosicion - posicionActual <= CANTIDAD_ELEMENTOS_PARA_NUEVO_PEDIDO) {
                    obtenerCancionesTop();
                }
            }
        });


        setAdapterLinear(recyclerViewAlbumesTop, linearLayoutManagerAlbum, adapterAlbum);
        setAdapterLinear(recyclerViewArtistasTop, linearLayoutManagerArtista, adapterArtista);
        setAdapterLinear(recyclerViewCancionTop, linearLayoutManagerCanciones, adapterTopCanciones);
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
        final RoomControllerAlbum roomControllerAlbum = new RoomControllerAlbum(getContext());
        controllerAlbum.obtenerAlbumesOnline(new ResultListener<List<Album>>() {
            @Override
            public void finish(List<Album> resultado) {
                if (resultado != null) {
                    adapterAlbum.agregarAlbumes(resultado);
                    roomControllerAlbum.insertarAlbumes(resultado);
                } else {
                    roomControllerAlbum.obtenerAlbumes(new ResultListener<List<Album>>() {
                        @Override
                        public void finish(List<Album> resultado) {
                            if (resultado != null) {
                                adapterAlbum.agregarAlbumes(resultado);
                            }
                        }
                    });
                }
            }
        });
    }

    ///pido a la api la lista de artistas para el recycler del fragment Pantalla inicio
    private void obtenerArtistas() {
        final RoomControllerArtista roomControllerArtista = new RoomControllerArtista(getContext());
        controllerArtista.obtenerArtistasOnline(new ResultListener<List<Artista>>() {
            @Override
            public void finish(List<Artista> resultado) {
                if (resultado != null) {
                    adapterArtista.agregarArtistas(resultado);
                    roomControllerArtista.insertarArtistas(resultado);
                } else {
                    roomControllerArtista.obtenerArtistas(new ResultListener<List<Artista>>() {
                        @Override
                        public void finish(List<Artista> resultado) {
                            if (resultado != null) {
                                adapterArtista.agregarArtistas(resultado);
                            }
                        }
                    });
                }
            }
        });
    }

    private void obtenerPlaylists() {
        final RoomControllerPlaylist roomControllerPlaylist = new RoomControllerPlaylist(getContext());
        controllerPlaylist.obtenerPlaylistOnline(new ResultListener<List<Playlist>>() {
            @Override
            public void finish(List<Playlist> resultado) {
                if(resultado != null){
                    adapterPlaylist.agregarListaPlaylists(resultado);
                    roomControllerPlaylist.insertarPlaylists(resultado);
                }else{
                    roomControllerPlaylist.obtenerPlaylists(new ResultListener<List<Playlist>>() {
                        @Override
                        public void finish(List<Playlist> resultado) {
                            if(resultado != null){
                                adapterPlaylist.agregarListaPlaylists(resultado);
                            }

                        }
                    });
                }


            }
        });
    }

    private void obtenerCancionesTop() {
        final RoomControllerCancion roomControllerCancion = new RoomControllerCancion(getContext());
        controllerTopCancion.obtenerCancionesTopOnline(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                if (resultado != null) {
                    adapterTopCanciones.agregarCanciones(resultado);
                    roomControllerCancion.insertarCanciones(resultado);
                }else{
                    roomControllerCancion.obtenerCanciones(new ResultListener<List<Cancion>>() {
                        @Override
                        public void finish(List<Cancion> resultado) {
                            if(resultado != null){
                                adapterTopCanciones.agregarCanciones(resultado);
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificadorActivities = (NotificadorActivities) context;
        navBarUIupdater = (NavBarUIupdater) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        navBarUIupdater.updateUi("home");
    }

    @Override
    public void onPause() {
        super.onPause();
        navBarUIupdater.updateUiOnPause("home");

    }

    //notificar celda clickeada de la lista de albumes del adapterAlbum del recycler AlbumesTop
    @Override
    public void notificarCeldaClickeada(List<Album> list, int posicion) {
        notificadorActivities.notificarAlbum(list, posicion);
    }

    @Override
    public void notificarCeldaCliqueadaArtista(List<Artista> artistas, int posicion) {
        notificadorActivities.notificarArtista(artistas, posicion);
    }

    @Override
    public void notificarCeldaCliqueadaPlaylist(List<Playlist> playlists, int posicion) {
        notificadorActivities.notificarPlaylist(playlists, posicion);
    }

    ////notificar celda clickeada de la lista de albumes del adapterTopCanciones  del recycler AlbumesTop
    @Override
    public void notificarCeldaClickeadaDeCancion(Cancion cancion) {
        //este es el notificador de canciones top
        notificadorActivities.notificarCancion(cancion);
    }

    public void setAdapterLinear(RecyclerView recyclerView,
                                 LinearLayoutManager linearLayoutManager,
                                 RecyclerView.Adapter adapter) {

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public interface NotificadorActivities {
        public void notificarAlbum(List<Album> listaAlbums, int posicion);

        public void notificarArtista(List<Artista> listaArtistas, int posicion);

        public void notificarPlaylist(List<Playlist> listaPlaylist, int posicion);

        public void notificarCancion(Cancion cancion);
    }
}
