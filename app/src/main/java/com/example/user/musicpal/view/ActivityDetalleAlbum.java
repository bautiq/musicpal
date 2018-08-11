package com.example.user.musicpal.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.ControllerGlobal;
import com.example.user.musicpal.controller.MediaPlayerGlobal;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.utils.DepthPageTransformer;
import com.example.user.musicpal.utils.FadeTransformer;
import com.example.user.musicpal.utils.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetalleAlbum
        extends AppCompatActivity
        implements FragmentDetalleAlbum.NotificadorCancion,
        FragmentReproductorChico.NotificadorReproductorChico,
        FragmentReproductor.NotificadorReproductorGrande,
        MediaPlayerGlobal.NotificadorQueTermino,
        FragmentReproductor.NotificarCompartir {

    public static final String POSICION_KEY = "clave_posicion";
    public static final String ALBUM_KEY = "clave_album";

    private FragmentManager fragmentManager;
    private Cancion cancionACompartir;

    //esta lista contiene los fragments que se van a mostrar en el viewpager
    private List<FragmentDetalleAlbum> listaFragments;
    private List<Album> listaAlbumesRecibida;

    private FragmentReproductorChico fragmentReproductorChico;
    private ViewPager viewPager;

    private ControllerGlobal controllerGlobal;
    private FragmentReproductor fragmentReproductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_album);
        fragmentReproductor = new FragmentReproductor();
        fragmentReproductorChico = new FragmentReproductorChico();

        FragmentHelper.cargarFragment(fragmentReproductorChico,
                R.id.contenedor_reproductor_chico_detalle_activity,
                getSupportFragmentManager());

        controllerGlobal = new ControllerGlobal(this);
        viewPager = findViewById(R.id.viewPager_id);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        listaAlbumesRecibida = (List<Album>) bundle.getSerializable(ALBUM_KEY);
        crearListaFragments();
        FragDetalleAlbumPAdapter detallePagerAdapter = new FragDetalleAlbumPAdapter(
                getSupportFragmentManager(),
                listaFragments);

        viewPager.setAdapter(detallePagerAdapter);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
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
        fragmentReproductorChico.setearDatos(cancion);
    }

    @Override
    public void cargarReproductorGrande() {
        FragmentHelper.cargarFragmentConBackStack(fragmentReproductor,
                R.id.contenedor_fragment_vista_previa, getSupportFragmentManager());
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
        share.putExtra(Intent.EXTRA_TEXT, "Estoy Escuchando << " + cancionACompartir.getTitle()
                + " - " + cancionACompartir.getArtista().getNombre() + " >> En MusicPal");
        startActivity(Intent.createChooser(share, "Compartir en!"));
    }
}


