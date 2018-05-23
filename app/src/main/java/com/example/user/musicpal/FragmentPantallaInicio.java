package com.example.user.musicpal;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;
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
    private List<Cancion> cancionesUrbanHymns;
    private List<Cancion> cancionesLoyalToTheGame;
    private List<Cancion> cancionesRecovery;
    private List<Cancion> cancionesMeteora;
    private List<Cancion> cancionesUnderPressure;
    private List<Cancion> cancionesPostTraumatic;
    private AlbumAdapter albumAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_pantalla_inicio, container, false);
        recyclerViewRecomendaciones = view.findViewById(R.id.recycler_recomendaciones_id);
        armarListadoAlbum();
        armarListadoDeCanciones();
        setAdapterAlbums(albumListaRecomendaciones, recyclerViewRecomendaciones);


        recyclerViewPopulares = view.findViewById(R.id.recycler_populares_id);
        setAdapterAlbums(albumListaPopulares, recyclerViewPopulares);

        recyclerViewTop = view.findViewById(R.id.recycler_top_id);
        setAdapterAlbums(albumListaTop, recyclerViewTop);

        recyclerViewClasicos = view.findViewById(R.id.recycler_clasicos_id);
        setAdapterAlbums(albumListaClasicos, recyclerViewClasicos);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificadorActivities = (NotificadorActivities) context;
    }

    @Override
    public void notificarCeldaClickeada(Album album) {
        notificadorActivities.notificar(album);
    }

    private void armarListadoAlbum() {
        albumListaRecomendaciones = new ArrayList<>();
        albumListaRecomendaciones.add(new Album("Urban Hymns", new Artista("The Verve"), "2009", R.drawable.theverve, cancionesUrbanHymns));
        albumListaRecomendaciones.add(new Album("Loyal to the game", new Artista("Tupac Shakur"), "2000", R.drawable.tupac_loyal_album, cancionesLoyalToTheGame));
        albumListaRecomendaciones.add(new Album("Recovery", new Artista("Eminem"), "2010", R.drawable.eminem_single, cancionesRecovery));
        albumListaRecomendaciones.add(new Album("Under Pressure", new Artista("Logic"), "2005", R.drawable.underpressure_album, cancionesUnderPressure));
        albumListaRecomendaciones.add(new Album("Post Traumatic", new Artista("Mike Shinoda"), "2018", R.drawable.post_traumatic_album, cancionesPostTraumatic));
        albumListaRecomendaciones.add(new Album("Meteora", new Artista("Linkin Park"), "2003", R.drawable.meteora_album, cancionesMeteora));
        albumListaTop = new ArrayList<>();
        albumListaTop.add(new Album("Under Pressure", new Artista("Logic"), "2005", R.drawable.underpressure_album, cancionesUnderPressure));
        albumListaTop.add(new Album("Post Traumatic", new Artista("Mike Shinoda"), "2018", R.drawable.post_traumatic_album, cancionesPostTraumatic));
        albumListaTop.add(new Album("Meteora", new Artista("Linkin Park"), "2003", R.drawable.meteora_album, cancionesMeteora));
        albumListaTop.add(new Album("Urban Hymns", new Artista("The Verve"), "2009", R.drawable.theverve, cancionesUrbanHymns));
        albumListaTop.add(new Album("Loyal to the game", new Artista("Tupac Shakur"), "2000", R.drawable.tupac_loyal_album, cancionesLoyalToTheGame));
        albumListaTop.add(new Album("Recovery", new Artista("Eminem"), "2010", R.drawable.eminem_single, cancionesRecovery));
        albumListaPopulares = new ArrayList<>();
        albumListaPopulares.add(new Album("Meteora", new Artista("Linkin Park"), "2003", R.drawable.meteora_album, cancionesMeteora));
        albumListaPopulares.add(new Album("Urban Hymns", new Artista("The Verve"), "2009", R.drawable.theverve, cancionesUrbanHymns));
        albumListaPopulares.add(new Album("Under Pressure", new Artista("Logic"), "2005", R.drawable.underpressure_album, cancionesUnderPressure));
        albumListaPopulares.add(new Album("Post Traumatic", new Artista("Mike Shinoda"), "2018", R.drawable.post_traumatic_album, cancionesPostTraumatic));
        albumListaPopulares.add(new Album("Loyal to the game", new Artista("Tupac Shakur"), "2000", R.drawable.tupac_loyal_album, cancionesLoyalToTheGame));
        albumListaPopulares.add(new Album("Recovery", new Artista("Eminem"), "2010", R.drawable.eminem_single, cancionesRecovery));
        albumListaClasicos = new ArrayList<>();
        albumListaClasicos.add(new Album("Urban Hymns", new Artista("The Verve"), "2009", R.drawable.theverve, cancionesUrbanHymns));
        albumListaClasicos.add(new Album("Loyal to the game", new Artista("Tupac Shakur"), "2000", R.drawable.tupac_loyal_album, cancionesLoyalToTheGame));
        albumListaClasicos.add(new Album("Recovery", new Artista("Eminem"), "2010", R.drawable.eminem_single, cancionesRecovery));
        albumListaClasicos.add(new Album("Under Pressure", new Artista("Logic"), "2005", R.drawable.underpressure_album, cancionesUnderPressure));
        albumListaClasicos.add(new Album("Post Traumatic", new Artista("Mike Shinoda"), "2018", R.drawable.post_traumatic_album, cancionesPostTraumatic));
        albumListaClasicos.add(new Album("Meteora", new Artista("Linkin Park"), "2003", R.drawable.meteora_album, cancionesMeteora));
    }

    private void armarListadoDeCanciones() {
        cancionesUrbanHymns = new ArrayList<>();
        cancionesUrbanHymns.add(new Cancion("Bitter Sweet Symphony"));
        cancionesUrbanHymns.add(new Cancion("Sonnet"));
        cancionesUrbanHymns.add(new Cancion("The Rolling People"));
        cancionesUrbanHymns.add(new Cancion("The Drugs Don´t Work"));
        cancionesUrbanHymns.add(new Cancion("Catching the Butterfly"));
        cancionesUrbanHymns.add(new Cancion("Neon Wilderness"));
        cancionesUrbanHymns.add(new Cancion("Space and Time"));
        cancionesUrbanHymns.add(new Cancion("Weeping Willow"));
        cancionesUrbanHymns.add(new Cancion("Lucky Man"));
        cancionesUrbanHymns.add(new Cancion("One Day"));
        cancionesUrbanHymns.add(new Cancion("This Time"));
        cancionesUrbanHymns.add(new Cancion("Velvet Morning"));
        cancionesUrbanHymns.add(new Cancion("Come On / Deep Freeze"));

        cancionesLoyalToTheGame = new ArrayList<>();
        cancionesLoyalToTheGame.add(new Cancion("Soldier Like Me (Return of the Soulja)"));
        cancionesLoyalToTheGame.add(new Cancion("The Uppercut"));
        cancionesLoyalToTheGame.add(new Cancion("Out on Bail"));
        cancionesLoyalToTheGame.add(new Cancion("Ghetto Gospel"));
        cancionesLoyalToTheGame.add(new Cancion("Black Cotton"));
        cancionesLoyalToTheGame.add(new Cancion("Loyal to the Game"));
        cancionesLoyalToTheGame.add(new Cancion("Thugs Get Lonely Too"));
        cancionesLoyalToTheGame.add(new Cancion("N.I.G.G.A. (Never Ignorant about Getting Goals Accomplished)"));
        cancionesLoyalToTheGame.add(new Cancion("Who Do You Love?"));
        cancionesLoyalToTheGame.add(new Cancion("Crooked Nigga Too"));
        cancionesLoyalToTheGame.add(new Cancion("Don't You Trust Me?"));
        cancionesLoyalToTheGame.add(new Cancion("Hennessey"));
        cancionesLoyalToTheGame.add(new Cancion("Thug 4 Life"));
        cancionesLoyalToTheGame.add(new Cancion("Po Nigga Blues (Scott Storch Remix)"));
        cancionesLoyalToTheGame.add(new Cancion("Hennessy (Red Spyda Remix)"));
        cancionesLoyalToTheGame.add(new Cancion("Crooked Nigga Too (Raphael Saadiq Remix)"));
        cancionesLoyalToTheGame.add(new Cancion("Loyal to the Game (DJ Quik Remix)"));

        cancionesRecovery = new ArrayList<>();
        cancionesRecovery.add(new Cancion("Cold Wind Blows"));
        cancionesRecovery.add(new Cancion("Talkin' 2 Myself"));
        cancionesRecovery.add(new Cancion("On Fire"));
        cancionesRecovery.add(new Cancion("Won't Back Down"));
        cancionesRecovery.add(new Cancion("W.T.P."));
        cancionesRecovery.add(new Cancion("Going Through Changes"));
        cancionesRecovery.add(new Cancion("Not Afraid"));
        cancionesRecovery.add(new Cancion("Seduction"));
        cancionesRecovery.add(new Cancion("No Love"));
        cancionesRecovery.add(new Cancion("Space Bound"));
        cancionesRecovery.add(new Cancion("Cinderella Man"));
        cancionesRecovery.add(new Cancion("25 to Life"));
        cancionesRecovery.add(new Cancion("So Bad"));
        cancionesRecovery.add(new Cancion("Almost Famous"));
        cancionesRecovery.add(new Cancion("Love the Way You Lie"));
        cancionesRecovery.add(new Cancion("You're Never Over"));
        cancionesRecovery.add(new Cancion("Untitled"));

        cancionesMeteora = new ArrayList<>();
        cancionesMeteora.add(new Cancion("Foreword"));
        cancionesMeteora.add(new Cancion("Don't Stay"));
        cancionesMeteora.add(new Cancion("Somewhere I Belong"));
        cancionesMeteora.add(new Cancion("Lying from You"));
        cancionesMeteora.add(new Cancion("Hit the Floor"));
        cancionesMeteora.add(new Cancion("Easier to Run"));
        cancionesMeteora.add(new Cancion("Faint"));
        cancionesMeteora.add(new Cancion("Figure.09"));
        cancionesMeteora.add(new Cancion("Breaking the Habit"));
        cancionesMeteora.add(new Cancion("From the Inside"));
        cancionesMeteora.add(new Cancion("Nobody's Listening"));
        cancionesMeteora.add(new Cancion("Session"));
        cancionesMeteora.add(new Cancion("Numb"));

        cancionesUnderPressure = new ArrayList<>();
        cancionesUnderPressure.add(new Cancion("Intro"));
        cancionesUnderPressure.add(new Cancion("Soul Food"));
        cancionesUnderPressure.add(new Cancion("I'm Gone"));
        cancionesUnderPressure.add(new Cancion("Gang Related"));
        cancionesUnderPressure.add(new Cancion("Buried Alive"));
        cancionesUnderPressure.add(new Cancion("Bounce"));
        cancionesUnderPressure.add(new Cancion("Growing Pains III"));
        cancionesUnderPressure.add(new Cancion("Never Enough"));
        cancionesUnderPressure.add(new Cancion("Metropolis"));
        cancionesUnderPressure.add(new Cancion("Nikki"));
        cancionesUnderPressure.add(new Cancion("Under Pressure"));
        cancionesUnderPressure.add(new Cancion("Till the End"));

        cancionesPostTraumatic = new ArrayList<>();
        cancionesPostTraumatic.add(new Cancion("Place to Start"));
        cancionesPostTraumatic.add(new Cancion("Over Again"));
        cancionesPostTraumatic.add(new Cancion("Watching as I Fall"));
    }

    public void setAdapterAlbums(List<Album> listaAlbum, RecyclerView recyclerView) {
        albumAdapter = new AlbumAdapter(listaAlbum, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(albumAdapter);
    }

    public interface NotificadorActivities {
        public void notificar(Album album);
    }

}
