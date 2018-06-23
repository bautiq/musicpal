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
import com.example.user.musicpal.model.adapters.AlbumAdapter;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.R;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPantallaInicio extends Fragment implements AlbumAdapter.NotificadorAlbumCelda {

    private NotificadorActivities notificadorActivities;

    private RecyclerView recyclerViewAlbumesTop;
    private RecyclerView recyclerViewPlaylistsTop;
    private RecyclerView recyclerViewArtistasTop;
    private RecyclerView recyclerViewTracksTop;
    private List<Album> albumListaRecomendaciones;
    private List<Album> albumListaTop;
    private List<Album> albumListaPopulares;
    private List<Album> albumListaClasicos;
    private AlbumAdapter albumAdapter;
    private ControllerAlbum controllerAlbum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_pantalla_inicio, container, false);

        albumAdapter = new AlbumAdapter(getActivity(), this);
        recyclerViewAlbumesTop = view.findViewById(R.id.recycler_albumes_top_id);
        recyclerViewPlaylistsTop = view.findViewById(R.id.recycler_playlist_top_id);
        recyclerViewArtistasTop = view.findViewById(R.id.recycler_artista_top_id);
        recyclerViewTracksTop = view.findViewById(R.id.recycler_tracks_top_id);

        setAdapterAlbums(recyclerViewAlbumesTop);
        setAdapterAlbums(recyclerViewArtistasTop);
        setAdapterAlbums(recyclerViewTracksTop);
        setAdapterAlbums(recyclerViewPlaylistsTop);
        controllerAlbum = new ControllerAlbum(getActivity());

       /* albumListaRecomendaciones = controllerAlbum.getListaAlbumes( "recomendaciones");
        albumListaPopulares = controllerAlbum.getListaAlbumes("populares");
        albumListaTop = controllerAlbum.getListaAlbumes("top");
        albumListaClasicos= controllerAlbum.getListaAlbumes("clasicos");

        recyclerViewAlbumesTop = view.findViewById(R.id.recycler_recomendaciones_id);
        setAdapterAlbums(albumListaRecomendaciones, recyclerViewAlbumesTop, "recomendaciones");

        recyclerViewPlaylistsTop = view.findViewById(R.id.recycler_populares_id);
        setAdapterAlbums(albumListaPopulares, recyclerViewPlaylistsTop, "populares");

        recyclerViewArtistasTop = view.findViewById(R.id.recycler_top_id);
        setAdapterAlbums(albumListaTop, recyclerViewArtistasTop, "top");

        recyclerViewTracksTop = view.findViewById(R.id.recycler_clasicos_id);
        setAdapterAlbums(albumListaClasicos, recyclerViewTracksTop, "clasicos");
*/

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
                    albumAdapter.agregarAlbumes(resultado);
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
        notificadorActivities.notificar(list, posicion, categoria);
    }

    public void setAdapterAlbums(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(albumAdapter);
    }

    public interface NotificadorActivities {
        public void notificar(List<Album> listaAlbums, int posicion, String categoria);
    }

}
