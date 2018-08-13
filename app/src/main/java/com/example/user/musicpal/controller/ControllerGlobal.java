package com.example.user.musicpal.controller;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.user.musicpal.model.dao.ArtistaDao;
import com.example.user.musicpal.model.dao.DaoAlbum;
import com.example.user.musicpal.model.dao.DaoCancion;
import com.example.user.musicpal.model.dao.DaoPlaylist;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

public class ControllerGlobal {
    private Context context;
    private Integer offset;
    private Boolean hayPaginas;
    private DaoCancion daoCancion;
    private static final Integer LIST_SIZE = 10;


    public ControllerGlobal(Context context) {
        this.context = context;
        hayPaginas = true;
        offset = 0;
        daoCancion = new DaoCancion();
    }


    public void obtenerAlbumesOnline(final ResultListener<List<Album>> resultListenerDeLaVista) {
        if (hayInternet()) {
            DaoAlbum daoRetroFit = new DaoAlbum();
            daoRetroFit.obtenerAlbumes(new ResultListener<List<Album>>() {
                @Override
                public void finish(List<Album> resultado) {
                    if (resultado.size() < LIST_SIZE) {
                        hayPaginas = false;
                    }
                    offset += resultado.size();
                    resultListenerDeLaVista.finish(resultado);
                }
            }, offset, LIST_SIZE);
        }else{
            resultListenerDeLaVista.finish(null);
        }
    }

    public void obtenerCancionesPorAlbum(final ResultListener<List<Cancion>> listener, String id) {

        daoCancion.obtenerCancionesPorAlbum(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                listener.finish(resultado);
            }
        }, id);
    }

    public void obtenerArtistasOnline(final ResultListener<List<Artista>> resultListenerDeLaVista) {
        if (hayInternet()) {
            ArtistaDao artistaDao = new ArtistaDao();
            artistaDao.obtenerArtistas(new ResultListener<List<Artista>>() {
                @Override
                public void finish(List<Artista> resultado) {
                    if (resultado.size() < LIST_SIZE) {
                        hayPaginas = false;
                    }
                    offset += resultado.size();
                    resultListenerDeLaVista.finish(resultado);
                }
            }, offset, LIST_SIZE);
        }else{
            resultListenerDeLaVista.finish(null);
        }
    }

    public void obtenerCancionesPorArtista(final ResultListener<List<Cancion>> listener, String id) {
        daoCancion.obtenerCancionesPorArtista(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                listener.finish(resultado);
            }
        }, id);
    }

    public void obtenerPlaylistOnline(final ResultListener<List<Playlist>> resultListenerDeLaVista) {
        if (hayInternet()) {
            DaoPlaylist daoPlaylist = new DaoPlaylist();
            daoPlaylist.obtenerPlaylist(new ResultListener<List<Playlist>>() {
                @Override
                public void finish(List<Playlist> resultado) {

                    if (resultado.size() < LIST_SIZE) {
                        hayPaginas = false;
                    }
                    offset += resultado.size();
                    resultListenerDeLaVista.finish(resultado);
                }
            }, offset, LIST_SIZE);
        }else{
            resultListenerDeLaVista.finish(null);
        }
    }

    public void obtenerCancionesPorPlaylist(final ResultListener<List<Cancion>> listener, String id) {
        daoCancion.obtenerCancionesPorPlaylist(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                listener.finish(resultado);
            }
        }, id);
    }

    //pide lista de canciones Top
    public void obtenerCancionesTopOnline(final ResultListener<List<Cancion>> resultListenerDeLaVista) {
        if (hayInternet()) {
            daoCancion.obtenerCancionesTop(new ResultListener<List<Cancion>>() {
                @Override
                public void finish(List<Cancion> resultado) {
                    if (resultado.size() < LIST_SIZE) {
                        hayPaginas = false;
                    }
                    offset += resultado.size();
                    resultListenerDeLaVista.finish(resultado);
                }
            }, offset, LIST_SIZE);
        }else{
            resultListenerDeLaVista.finish(null);
        }

    }

    //pide lista de canciones por Id
    public void obtenerCancionOnline(final ResultListener<Cancion> listener, String id) {
        daoCancion.obtenerCancion(new ResultListener<Cancion>() {
            @Override
            public void finish(Cancion resultado) {
                listener.finish(resultado);
            }
        }, id);

    }

    public void obtenerBusquedaCancionesEditText(String stringEditText, final ResultListener<List<Cancion>> resultListenerDeLFragmentBusqueda) {
        if (hayInternet()) {
            daoCancion.obtenerBusquedaCancionesEditText(stringEditText, new ResultListener<List<Cancion>>() {

                @Override
                public void finish(List<Cancion> resultado) {
                    resultListenerDeLFragmentBusqueda.finish(resultado);

                    if (resultado.size() < LIST_SIZE) {
                        hayPaginas = false;
                    }
                    offset += resultado.size();
                    resultListenerDeLFragmentBusqueda.finish(resultado);

                }
            }, offset, LIST_SIZE);
        }

    }

    //se pide en metodo distinto la 1er vez, con el offset en 0 porque si ya se busco previamente fue cambiando el valor de offset y no muestra
    //los resultados menores a ese offset.Podria no pasarsele offset y listsiza pero habria que crear otro metodo en dao y otro get en service.
    public void obtenerBusquedaCancionesEditTextPrimerPedido(String stringEditText, final ResultListener<List<Cancion>> resultListenerDeLFragmentBusqueda) {
        if (hayInternet()) {
            daoCancion.obtenerBusquedaCancionesEditText(stringEditText, new ResultListener<List<Cancion>>() {
                @Override
                public void finish(List<Cancion> resultado) {
                    resultListenerDeLFragmentBusqueda.finish(resultado);
                    offset = 0;
                }
            }, offset, LIST_SIZE);
        }
    }

    private boolean hayInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public Boolean getHayPaginas() {
        return hayPaginas;
    }


    public void obtenerFavoritosFDB(final ResultListener<Cancion> listenerDeLaVista, final ResultListener<Cancion> listenerDeLaVistaCambio) {
        daoCancion.obtenerFavoritosFDB(new ResultListener<Cancion>() {

            @Override
            public void finish(Cancion resultado) {
                if (resultado != null) {
                    listenerDeLaVista.finish(resultado);
                }
            }
        }, new ResultListener<Cancion>() {
            @Override
            public void finish(Cancion resultado) {
                listenerDeLaVistaCambio.finish(resultado);
            }
        });
    }

    public void obtenerFavPorID(Cancion cancion, final ResultListener<Cancion> listenerDeLaVista) {
        daoCancion.obtenerFavPorID(cancion, new ResultListener<Cancion>() {
            @Override
            public void finish(Cancion resultado) {
                listenerDeLaVista.finish(resultado);

            }
        });
    }

    public void pushearOeliminarCancion(final Cancion cancion) {
        daoCancion.obtenerFavPorID(cancion, new ResultListener<Cancion>() {
            @Override
            public void finish(Cancion resultado) {
                //si clickeo fav y no recibe nada de la db, significa que no esta guardada
                // y devuelve null, en ese caso se pushea, en caso contrario, la elimina
                if (resultado == null) {
                    daoCancion.pushearCancion(cancion);
                } else {
                    daoCancion.eliminarFavFDB(cancion);
                }
            }
        });
    }

    public void eliminarFavFDB(Cancion cancion) {
        daoCancion.eliminarFavFDB(cancion);
    }
}
