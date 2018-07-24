package com.example.user.musicpal.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.ControllerGlobal;

import com.example.user.musicpal.controller.MediaPlayerGlobal;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.FragmentHelper;

import java.util.ArrayList;
import java.util.List;


public class ActivityDetallePlaylist
        extends AppCompatActivity
        implements FragmentDetallePlaylist.NotificadorCancion,
        FragmentReproductorChico.NotificadorReproductorChico,
        FragmentReproductor.NotificadorReproductorGrande,
        MediaPlayerGlobal.NotificadorQueTermino,
        FragmentReproductor.NotificarCompartir{

    public static final String PLAYLIST_KEY = "clave_playlist";
    public static final String POSICION_KEY_PLAYLIST = "clave_posicion_playlist";

    private FragmentManager fragmentManager;
    private Cancion cancionACompartir;

    //esta lista contiene los fragments que se van a mostrar en el viewpager
    private List<FragmentDetallePlaylist> listaFragments;
    private List<Playlist> listaPlaylistRecibida;

    private ViewPager viewPager;

    private ControllerGlobal controllerPlaylist;
    private FragmentReproductorChico fragmentReproductorChico;
    private FragmentReproductor fragmentReproductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_playlist);

        fragmentReproductorChico = new FragmentReproductorChico();
        fragmentReproductor = new FragmentReproductor();
        FragmentHelper.cargarFragment(fragmentReproductorChico,
                R.id.contenedor_reproductor_chico_detalle_activity_playlist,
                getSupportFragmentManager());

        controllerPlaylist = new ControllerGlobal(this);
        viewPager = findViewById(R.id.viewPager_playlist_id);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        listaPlaylistRecibida = (List<Playlist>) bundle.getSerializable(PLAYLIST_KEY);
        crearListaFragments();
        FragDetallePlaylistPAdapter fragDetallePlaylistPAdapter = new FragDetallePlaylistPAdapter(
                getSupportFragmentManager(),
                listaFragments);

        viewPager.setAdapter(fragDetallePlaylistPAdapter);
        int posicionDelItem = bundle.getInt(POSICION_KEY_PLAYLIST);
        viewPager.setCurrentItem(posicionDelItem);
    }

    private void crearListaFragments() {
        listaFragments = new ArrayList<>();
        for (Playlist playlist : listaPlaylistRecibida) {
            listaFragments.add(FragmentDetallePlaylist.dameUnFragment(playlist));
        }
    }

    @Override
    public void cargarReproductorGrande() {
        FragmentHelper.cargarFragmentConBackStack(fragmentReproductor,
                R.id.contenedor_fragment_vista_previa_playlist,
                fragmentManager);
    }

    @Override
    public void notificarCancion(Cancion cancion, Playlist playlist) {
        fragmentManager = getSupportFragmentManager();
        fragmentReproductorChico.setearDatos(cancion);
    }

    @Override
    public void notificarPlayPausa() {
        Cancion cancion = MediaPlayerGlobal.getInstance().getCancion();
        fragmentReproductorChico.setearDatos(cancion);
    }

    @Override
    public void cambioCancion() {
        Cancion cancion = MediaPlayerGlobal.getInstance().getCancion();
        fragmentReproductorChico.setearDatos(cancion);
        fragmentReproductor.setearDatos(cancion);
    }

    @Override
    public void compartir(Cancion cancion) {
        cancionACompartir = cancion;
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT, "Compartir");
        share.putExtra(Intent.EXTRA_TEXT, "Estoy Escuchando - " + cancionACompartir.getTitle() + " - " + cancionACompartir.getArtista().getNombre() + " - En MusicPal");
        startActivity(Intent.createChooser(share, "Compartir en!"));
    }
}
