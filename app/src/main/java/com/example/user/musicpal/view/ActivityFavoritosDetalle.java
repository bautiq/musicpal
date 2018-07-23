package com.example.user.musicpal.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.MediaPlayerGlobal;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.FragmentHelper;

public class ActivityFavoritosDetalle extends AppCompatActivity implements FragmentDetallePlaylist.NotificadorCancion,
        FragmentReproductorChico.NotificadorReproductorChico,
        FragmentReproductor.NotificadorReproductorGrande,
        MediaPlayerGlobal.NotificadorQueTermino  {


    private FragmentReproductor fragmentReproductor;
    private FragmentReproductorChico fragmentReproductorChico;
    private FragmentManager fragmentManager;
    private FragmentDetallePlaylistUser fragmentDetallePlaylistUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_user_detalle);
        fragmentReproductorChico = new FragmentReproductorChico();
        fragmentReproductor = new FragmentReproductor();
        fragmentDetallePlaylistUser = new FragmentDetallePlaylistUser();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        fragmentDetallePlaylistUser.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();

        cargarReproductorChico();
        cargarDetalleFragment();

    }

    private void cargarDetalleFragment() {
        FragmentHelper.cargarFragment(fragmentDetallePlaylistUser, R.id.contenedor_fragment_user_playlist, fragmentManager);

    }

    private void cargarReproductorChico() {
        FragmentHelper.cargarFragment(fragmentReproductorChico,
                R.id.contenedor_reproductor_chico_detalle_activity_user_playlist,
                fragmentManager);
    }

    @Override
    public void cambioCancion() {
        Cancion cancion = MediaPlayerGlobal.getInstance().getCancion();
        fragmentReproductorChico.setearDatos(cancion);
        fragmentReproductor.setearDatos(cancion);
    }

    @Override
    public void notificarCancion(Cancion cancion, Playlist playlist) {
        fragmentManager = getSupportFragmentManager();
        fragmentReproductorChico.setearDatos(cancion);
    }

    @Override
    public void cargarReproductorGrande() {
        FragmentHelper.cargarFragmentConBackStack(fragmentReproductor,
                R.id.contenedor_fragment_user_playlist,
                fragmentManager);
    }

    @Override
    public void notificarPlayPausa() {
        Cancion cancion = MediaPlayerGlobal.getInstance().getCancion();
        fragmentReproductorChico.setearDatos(cancion);
    }
}
