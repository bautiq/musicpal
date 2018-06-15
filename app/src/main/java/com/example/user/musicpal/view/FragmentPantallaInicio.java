package com.example.user.musicpal.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.musicpal.controller.ControllerAlbum;
import com.example.user.musicpal.model.adapters.AlbumAdapter;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPantallaInicio extends Fragment implements AlbumAdapter.NotificadorAlbumCelda {

    private NotificadorActivities notificadorActivities;

    private RecyclerView recyclerViewRecomendaciones;
    private RecyclerView recyclerViewPopulares;
    private RecyclerView recyclerViewTop;
    private RecyclerView recyclerViewClasicos;
    private List<Album> albumListaRecomendaciones;
    private List<Album> albumListaTop;
    private List<Album> albumListaPopulares;
    private List<Album> albumListaClasicos;
    protected List<Cancion> cancionesUrbanHymns;
    protected List<Cancion> cancionesLoyalToTheGame;
    protected List<Cancion> cancionesRecovery;
    protected List<Cancion> cancionesMeteora;
    protected List<Cancion> cancionesUnderPressure;
    protected List<Cancion> cancionesPostTraumatic;
    private AlbumAdapter albumAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_pantalla_inicio, container, false);
        recyclerViewRecomendaciones = view.findViewById(R.id.recycler_recomendaciones_id);

        ControllerAlbum controllerAlbum = new ControllerAlbum();
        albumListaRecomendaciones = controllerAlbum.getListaAlbumes(getActivity(), "recomendaciones");
        albumListaPopulares = controllerAlbum.getListaAlbumes(getActivity(), "populares");
        albumListaTop = controllerAlbum.getListaAlbumes(getActivity(), "top");
        albumListaClasicos= controllerAlbum.getListaAlbumes(getActivity(), "clasicos");
        setAdapterAlbums(albumListaRecomendaciones, recyclerViewRecomendaciones, "recomendaciones");

        recyclerViewPopulares = view.findViewById(R.id.recycler_populares_id);
        setAdapterAlbums(albumListaPopulares, recyclerViewPopulares, "populares");

        recyclerViewTop = view.findViewById(R.id.recycler_top_id);
        setAdapterAlbums(albumListaTop, recyclerViewTop, "top");

        recyclerViewClasicos = view.findViewById(R.id.recycler_clasicos_id);
        setAdapterAlbums(albumListaClasicos, recyclerViewClasicos, "clasicos");

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificadorActivities = (NotificadorActivities) context;
    }

    @Override
    public void notificarCeldaClickeada(Album album, int posicion, String categoria) {
        notificadorActivities.notificar(album, posicion, categoria);
    }

    public void setAdapterAlbums(List<Album> listaAlbum, RecyclerView recyclerView, String categoria) {
        albumAdapter = new AlbumAdapter(listaAlbum, this, categoria);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(albumAdapter);
    }

    public interface NotificadorActivities {
        public void notificar(Album album, int posicion, String categoria);
    }

}
