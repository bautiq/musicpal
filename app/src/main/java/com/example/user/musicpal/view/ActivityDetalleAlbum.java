package com.example.user.musicpal.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.ControllerAlbum;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.utils.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetalleAlbum extends AppCompatActivity implements FragmentDetalleAlbum.NotificadorCancion {

    public static final String POSICION_KEY = "clave_posicion";
    public static final String ALBUM_KEY = "clave_album";
    public static final String CATEGORIA_CLICKEADA = "clave_categoria";

    private FragmentDetalleAlbum fragmentDetalleAlbum;
    private FragmentManager fragmentManager;
    //esta lista contiene los fragments que se van a mostrar en el viewpager
    private List<FragmentDetalleAlbum> listaFragments;
    private List<Album> listaAlbumesRecibida;
    private String categoriaRecibida;
    private ViewPager viewPager;
    private ControllerAlbum controllerAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_album);
        controllerAlbum = new ControllerAlbum(this);
        viewPager = findViewById(R.id.viewPager_album_id);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        listaAlbumesRecibida = (List<Album>) bundle.getSerializable(ALBUM_KEY);
        categoriaRecibida = bundle.getString(CATEGORIA_CLICKEADA);
        crearListaFragments();
        FragmentDetalleAlbumPagerAdapter detallePagerAdapter = new FragmentDetalleAlbumPagerAdapter(getSupportFragmentManager(), listaFragments);
        viewPager.setAdapter(detallePagerAdapter);
        int posicionDelItem = bundle.getInt(POSICION_KEY);
        viewPager.setCurrentItem(posicionDelItem);

    }

    public void crearListaFragments() {
        listaFragments = new ArrayList<>();
        for (Album album : listaAlbumesRecibida) {
            listaFragments.add(FragmentDetalleAlbum.dameUnFragment(album));
        }
    }


    @Override
    public void notificarCancion(Cancion cancion, Album album) {
        fragmentManager = getSupportFragmentManager();
        FragmentReproductor fragmentReproductor = new FragmentReproductor();
        Bundle bundle = new Bundle();
        bundle.putSerializable(FragmentReproductor.ALBUM_KEY, album);
        bundle.putSerializable(FragmentReproductor.CANCION_KEY, cancion);
        fragmentReproductor.setArguments(bundle);
        FragmentHelper.cargarFragmentConBackStack(fragmentReproductor, R.id.container_detalle_activity_album, fragmentManager);
    }
}


