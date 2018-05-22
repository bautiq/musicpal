package com.example.user.musicpal;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPantallaInicio extends Fragment {
   /*private ImageView imagenUno;
    private ImageView imagenDos;
    private ImageView imagenTres;
    private ImageView imagenCuatro;
    private ImageView imagenCinco;
    private ImageView imagenSeis;*/

    private NotificadorActivities notificador;

    private RecyclerView recyclerViewRecomendaciones;
    private RecyclerView recyclerViewPopulares;
    private RecyclerView recyclerViewTop;
    private RecyclerView recyclerViewClasicos;
    private List<Album> albumListaRecomendaciones;
    private List<Album> albumListaTop;
    private List<Album> albumListaPopulares;
    private List<Album> albumListaClasicos;
    private AlbumAdapter albumAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_pantalla_inicio, container, false);
        recyclerViewRecomendaciones = view.findViewById(R.id.recycler_recomendaciones_id);
        armarListadoAlbum();
        setAdapterAlbums(albumListaRecomendaciones, recyclerViewRecomendaciones);


        recyclerViewPopulares= view.findViewById(R.id.recycler_populares_id);
        setAdapterAlbums(albumListaPopulares, recyclerViewPopulares);

        recyclerViewTop = view.findViewById(R.id.recycler_top_id);
        setAdapterAlbums(albumListaTop, recyclerViewTop);

        recyclerViewClasicos = view.findViewById(R.id.recycler_clasicos_id);
        setAdapterAlbums(albumListaClasicos, recyclerViewClasicos);

       /* imagenUno = view.findViewById(R.id.imagen_uno);
        imagenDos = view.findViewById(R.id.imagen_dos);
        imagenTres = view.findViewById(R.id.imagen_tres);
        imagenCuatro = view.findViewById(R.id.imagen_cuatro);
        imagenCinco = view.findViewById(R.id.imagen_cinco);
        imagenSeis = view.findViewById(R.id.imagen_seis);

        imagenUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificador.recibirData("Linkin Park", "Meteora", "2003", R.drawable.meteora_album);
            }
        });
        imagenDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificador.recibirData("Mike Shinoda", "Post Traumatic", "2018", R.drawable.post_traumatic_album);
            }
        });
        imagenTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificador.recibirData("Logic", "Under Pressure", "2005", R.drawable.underpressure_album);
            }
        });
        imagenCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificador.recibirData("Eminem", "Not Afraid", "2003", R.drawable.eminem_single);
            }
        });
        imagenCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificador.recibirData("Tupac Shakur", "Loyal to the game", "2000", R.drawable.tupac_loyal_album);
            }
        });
        imagenSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificador.recibirData("The Verve", "Urban Hymns", "2009", R.drawable.theverve);
            }
        });
        */
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.notificador = (NotificadorActivities) context;
    }

    public interface NotificadorActivities {
        public void recibirData(String artista, String album, String anio, int imagen);
    }

    private void armarListadoAlbum() {
        albumListaRecomendaciones = new ArrayList<>();
        albumListaRecomendaciones.add(new Album("Urban Hymns", new Artista("The Verve"), "2009", R.drawable.theverve));
        albumListaRecomendaciones.add(new Album("Loyal to the game", new Artista("Tupac Shakur"), "2000", R.drawable.tupac_loyal_album));
        albumListaRecomendaciones.add(new Album("Not Afraid", new Artista("Eminem"), "2003", R.drawable.eminem_single));
        albumListaRecomendaciones.add(new Album("Under Pressure", new Artista("logic"), "2005", R.drawable.underpressure_album));
        albumListaRecomendaciones.add(new Album("Post Traumatic", new Artista("Mike Shinoda"), "2018", R.drawable.post_traumatic_album));
        albumListaRecomendaciones.add(new Album("Meteora", new Artista("Linkin Park"), "2003", R.drawable.meteora_album));
        albumListaTop = new ArrayList<>();
        albumListaTop.add(new Album("Under Pressure", new Artista("logic"), "2005", R.drawable.underpressure_album));
        albumListaTop.add(new Album("Post Traumatic", new Artista("Mike Shinoda"), "2018", R.drawable.post_traumatic_album));
        albumListaTop.add(new Album("Meteora", new Artista("Linkin Park"), "2003", R.drawable.meteora_album));
        albumListaTop.add(new Album("Urban Hymns", new Artista("The Verve"), "2009", R.drawable.theverve));
        albumListaTop.add(new Album("Loyal to the game", new Artista("Tupac Shakur"), "2000", R.drawable.tupac_loyal_album));
        albumListaTop.add(new Album("Not Afraid", new Artista("Eminem"), "2003", R.drawable.eminem_single));
        albumListaPopulares = new ArrayList<>();
        albumListaPopulares.add(new Album("Meteora", new Artista("Linkin Park"), "2003", R.drawable.meteora_album));
        albumListaPopulares.add(new Album("Urban Hymns", new Artista("The Verve"), "2009", R.drawable.theverve));
        albumListaPopulares.add(new Album("Under Pressure", new Artista("logic"), "2005", R.drawable.underpressure_album));
        albumListaPopulares.add(new Album("Post Traumatic", new Artista("Mike Shinoda"), "2018", R.drawable.post_traumatic_album));
        albumListaPopulares.add(new Album("Loyal to the game", new Artista("Tupac Shakur"), "2000", R.drawable.tupac_loyal_album));
        albumListaPopulares.add(new Album("Not Afraid", new Artista("Eminem"), "2003", R.drawable.eminem_single));
        albumListaClasicos = new ArrayList<>();
        albumListaClasicos.add(new Album("Urban Hymns", new Artista("The Verve"), "2009", R.drawable.theverve));
        albumListaClasicos.add(new Album("Loyal to the game", new Artista("Tupac Shakur"), "2000", R.drawable.tupac_loyal_album));
        albumListaClasicos.add(new Album("Not Afraid", new Artista("Eminem"), "2003", R.drawable.eminem_single));
        albumListaClasicos.add(new Album("Under Pressure", new Artista("logic"), "2005", R.drawable.underpressure_album));
        albumListaClasicos.add(new Album("Post Traumatic", new Artista("Mike Shinoda"), "2018", R.drawable.post_traumatic_album));
        albumListaClasicos.add(new Album("Meteora", new Artista("Linkin Park"), "2003", R.drawable.meteora_album));
    }
    public void setAdapterAlbums(List<Album> listaAlbum, RecyclerView recyclerView){
        albumAdapter = new AlbumAdapter(listaAlbum);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(albumAdapter);
    }

}
