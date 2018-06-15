package com.example.user.musicpal.model.dao;

import android.content.Context;

import com.example.user.musicpal.R;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.model.pojo.Cancion;

import java.util.ArrayList;
import java.util.List;


public class AlbumDao {
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

    public List<Album> obtenerAlbumes(Context context, String categoria) {
        armarListadoDeCanciones();
        armarListadoAlbum();


        List<Album> listaAlbums = new ArrayList<>();
        switch (categoria) {
            case "top":
                listaAlbums = albumListaTop;
                break;

            case "recomendaciones":
                listaAlbums = albumListaRecomendaciones;
                break;

            case "populares":
                listaAlbums = albumListaPopulares;
                break;

            case "clasicos":
                listaAlbums = albumListaClasicos;
                break;

        }
        return listaAlbums;
    }


    private void armarListadoAlbum() {
        albumListaRecomendaciones = new ArrayList<>();
        albumListaRecomendaciones.add(new Album("Loyal to the game", new Artista("Tupac Shakur"), "2000", R.drawable.tupac_loyal_album, cancionesLoyalToTheGame));
        albumListaRecomendaciones.add(new Album("Urban Hymns", new Artista("The Verve"), "2009", R.drawable.urbanhymns, cancionesUrbanHymns));
        albumListaRecomendaciones.add(new Album("Recovery", new Artista("Eminem"), "2010", R.drawable.recoveryeminem, cancionesRecovery));
        albumListaRecomendaciones.add(new Album("Under Pressure", new Artista("Logic"), "2005", R.drawable.underpressure_album, cancionesUnderPressure));
        albumListaRecomendaciones.add(new Album("Post Traumatic", new Artista("Mike Shinoda"), "2018", R.drawable.post_traumatic_album, cancionesPostTraumatic));
        albumListaRecomendaciones.add(new Album("Meteora", new Artista("Linkin Park"), "2003", R.drawable.meteora2, cancionesMeteora));

        albumListaTop = new ArrayList<>();
        albumListaTop.add(new Album("Under Pressure", new Artista("Logic"), "2005", R.drawable.underpressure_album, cancionesUnderPressure));
        albumListaTop.add(new Album("Post Traumatic", new Artista("Mike Shinoda"), "2018", R.drawable.post_traumatic_album, cancionesPostTraumatic));
        albumListaTop.add(new Album("Meteora", new Artista("Linkin Park"), "2003", R.drawable.meteora2, cancionesMeteora));
        albumListaTop.add(new Album("Urban Hymns", new Artista("The Verve"), "2009", R.drawable.urbanhymns, cancionesUrbanHymns));
        albumListaTop.add(new Album("Loyal to the game", new Artista("Tupac Shakur"), "2000", R.drawable.tupac_loyal_album, cancionesLoyalToTheGame));
        albumListaTop.add(new Album("Recovery", new Artista("Eminem"), "2010", R.drawable.recoveryeminem, cancionesRecovery));

        albumListaPopulares = new ArrayList<>();
        albumListaPopulares.add(new Album("Meteora", new Artista("Linkin Park"), "2003", R.drawable.meteora2, cancionesMeteora));
        albumListaPopulares.add(new Album("Urban Hymns", new Artista("The Verve"), "2009", R.drawable.urbanhymns, cancionesUrbanHymns));
        albumListaPopulares.add(new Album("Under Pressure", new Artista("Logic"), "2005", R.drawable.underpressure_album, cancionesUnderPressure));
        albumListaPopulares.add(new Album("Post Traumatic", new Artista("Mike Shinoda"), "2018", R.drawable.post_traumatic_album, cancionesPostTraumatic));
        albumListaPopulares.add(new Album("Loyal to the game", new Artista("Tupac Shakur"), "2000", R.drawable.tupac_loyal_album, cancionesLoyalToTheGame));
        albumListaPopulares.add(new Album("Recovery", new Artista("Eminem"), "2010", R.drawable.recoveryeminem, cancionesRecovery));

        albumListaClasicos = new ArrayList<>();
        albumListaClasicos.add(new Album("Urban Hymns", new Artista("The Verve"), "2009", R.drawable.urbanhymns, cancionesUrbanHymns));
        albumListaClasicos.add(new Album("Loyal to the game", new Artista("Tupac Shakur"), "2000", R.drawable.tupac_loyal_album, cancionesLoyalToTheGame));
        albumListaClasicos.add(new Album("Recovery", new Artista("Eminem"), "2010", R.drawable.recoveryeminem, cancionesRecovery));
        albumListaClasicos.add(new Album("Under Pressure", new Artista("Logic"), "2005", R.drawable.underpressure_album, cancionesUnderPressure));
        albumListaClasicos.add(new Album("Post Traumatic", new Artista("Mike Shinoda"), "2018", R.drawable.post_traumatic_album, cancionesPostTraumatic));
        albumListaClasicos.add(new Album("Meteora", new Artista("Linkin Park"), "2003", R.drawable.meteora2, cancionesMeteora));
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
}