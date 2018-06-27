package com.example.user.musicpal.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.ControllerGlobal;
import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.utils.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetalleArtista extends AppCompatActivity implements FragmentDetalleArtista.NotificadorCancion, FragmentReproductorChico.NotificadorReproductorChico {
    public static final String ARTISTA_KEY = "clave_artista";
    public static final String POSICION_KEY_ARTISTA = "clave_posicion_artista";

    private FragmentManager fragmentManager;
    //esta lista contiene los fragments que se van a mostrar en el viewpager
    private List<FragmentDetalleArtista> listaFragments;
    private List<Artista> listaArtistasRecibida;
    private ViewPager viewPager;
    private ControllerGlobal controllerArtista;
    private FragmentReproductorChico fragmentReproductorChico;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_artista);
        fragmentReproductorChico = new FragmentReproductorChico();
        FragmentHelper.cargarFragment(fragmentReproductorChico, R.id.contenedor_reproductor_chico_detalle_activity_artista, getSupportFragmentManager());
        controllerArtista = new ControllerGlobal(this);
        viewPager = findViewById(R.id.viewPager_artista_id);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        listaArtistasRecibida = (List<Artista>) bundle.getSerializable(ARTISTA_KEY);
        crearListaFragments();
        FragmentDetalleArtistaPagerAdapter fragmentDetalleArtistaPagerAdapter = new FragmentDetalleArtistaPagerAdapter(getSupportFragmentManager(), listaFragments);
        viewPager.setAdapter(fragmentDetalleArtistaPagerAdapter);
        int posicionDelItem = bundle.getInt(POSICION_KEY_ARTISTA);
        viewPager.setCurrentItem(posicionDelItem);
    }

    public void crearListaFragments() {
        listaFragments = new ArrayList<>();
        for (Artista artista : listaArtistasRecibida) {
            listaFragments.add(FragmentDetalleArtista.dameUnFragment(artista));
        }
    }

    @Override
    public void notificarCancion(Cancion cancion, Artista artista) {
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

    @Override
    public void cargarReproductorGrande() {
        FragmentHelper.cargarFragment(new FragmentReproductor(), R.layout.activity_detalle_artista, fragmentManager);
    }
}
