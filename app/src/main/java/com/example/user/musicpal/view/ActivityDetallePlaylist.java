package com.example.user.musicpal.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.ControllerGlobal;

import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.FragmentHelper;

import java.util.ArrayList;
import java.util.List;


public class ActivityDetallePlaylist extends AppCompatActivity implements FragmentDetallePlaylist.NotificadorCancion, FragmentReproductorChico.NotificadorReproductorChico {

    public static final String PLAYLIST_KEY = "clave_playlist";
    public static final String POSICION_KEY_PLAYLIST = "clave_posicion_playlist";

    private FragmentManager fragmentManager;
    //esta lista contiene los fragments que se van a mostrar en el viewpager
    private List<FragmentDetallePlaylist> listaFragments;
    private List<Playlist> listaPlaylistRecibida;
    private ViewPager viewPager;
    private ControllerGlobal controllerPlaylist;
    private FragmentReproductorChico fragmentReproductorChico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_playlist);
        fragmentReproductorChico = new FragmentReproductorChico();
        FragmentHelper.cargarFragment(fragmentReproductorChico, R.id.contenedor_reproductor_chico_detalle_activity_playlist, getSupportFragmentManager());
        controllerPlaylist = new ControllerGlobal(this);
        viewPager = findViewById(R.id.viewPager_playlist_id);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        listaPlaylistRecibida = (List<Playlist>) bundle.getSerializable(PLAYLIST_KEY);
        crearListaFragments();
        FragmentDetallePlaylistPagerAdapter fragmentDetallePlaylistPagerAdapter = new FragmentDetallePlaylistPagerAdapter(getSupportFragmentManager(), listaFragments);
        viewPager.setAdapter(fragmentDetallePlaylistPagerAdapter);
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
        FragmentHelper.cargarFragmentConBackStack(new FragmentReproductor(), R.id.contenedor_fragment_vista_previa_artista, fragmentManager);
    }

    @Override
    public void notificarCancion(Cancion cancion, Playlist playlist) {
        fragmentManager = getSupportFragmentManager();
        fragmentReproductorChico.setearDatos(cancion);
            /*FragmentReproductor fragmentReproductor = new FragmentReproductor();
            Bundle bundle = new Bundle();
            bundle.putSerializable(FragmentReproductor.ARTISTA_KEY, artista);
            bundle.putSerializable(FragmentReproductor.CANCION_KEY, cancion);
            fragmentReproductor.setArguments(bundle);
            FragmentHelper.cargarFragmentConBackStack(fragmentReproductor, R.id.container_detalle_activity_artista, fragmentManager);
         */
    }
}
