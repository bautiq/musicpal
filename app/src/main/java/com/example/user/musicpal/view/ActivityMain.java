package com.example.user.musicpal.view;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.musicpal.controller.MediaPlayerGlobal;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.FragmentHelper;
import com.example.user.musicpal.R;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public class ActivityMain
        extends AppCompatActivity
        implements FragmentPantallaInicio.NotificadorActivities,
        NavigationView.OnNavigationItemSelectedListener,
        FragmentReproductorChico.NotificadorReproductorChico,
        FragmentReproductor.NotificadorReproductorGrande,
        MediaPlayerGlobal.NotificadorQueTermino {

    private ImageView imageHome;
    private ImageView imagePlaylist;
    private boolean homeIsClicked;
    private boolean playlistIsClicked;
    private ImageView imageMenu;
    private ImageView imageSearch;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private FragmentPerfil fragmentPerfil;

    private FragmentPantallaInicio fragmentPantallaInicio;
    private FragmentManager fragmentManager;
    private FragmentPlaylist fragmentPlaylist;

    private MediaPlayerGlobal mediaPlayerGlobal;
    private FragmentReproductor fragmentReproductor;
    private FragmentReproductorChico fragmentReproductorChico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeIsClicked = true;
        playlistIsClicked = false;
        imageHome = findViewById(R.id.home_button);
        imagePlaylist = findViewById(R.id.playlist_button);
        imageMenu = findViewById(R.id.menu_button);
        imageSearch = findViewById(R.id.search_button);

        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);

        mediaPlayerGlobal = MediaPlayerGlobal.getInstance();
        fragmentReproductor = new FragmentReproductor();
        fragmentPantallaInicio = new FragmentPantallaInicio();
        fragmentManager = getSupportFragmentManager();
        
        FragmentHelper.cargarFragment(fragmentPantallaInicio,
                R.id.container_fragment,
                fragmentManager);

        fragmentReproductorChico = new FragmentReproductorChico();
        FragmentHelper.cargarFragment(fragmentReproductorChico,
                R.id.contenedor_reproductor_chico,
                fragmentManager);

        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBotonesInferiores("home");
            }
        });

        imagePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBotonesInferiores("playlist");
            }
        });

        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navigationView);
            }
        });
        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentHelper.cargarFragmentConBackStack(new FragmentBusqueda(),
                        R.id.container_fragment,
                        fragmentManager);
            }
        });

    }

    public void notificarAlbum(List<Album> list, int posicion) {
        Intent intent = new Intent(this, ActivityDetalleAlbum.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ActivityDetalleAlbum.ALBUM_KEY, (Serializable) list);
        bundle.putInt(ActivityDetalleAlbum.POSICION_KEY, posicion);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void notificarArtista(List<Artista> listaArtistas, int posicion) {
        Intent intent = new Intent(this, ActivityDetalleArtista.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ActivityDetalleArtista.ARTISTA_KEY, (Serializable) listaArtistas);
        bundle.putInt(ActivityDetalleArtista.POSICION_KEY_ARTISTA, posicion);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void notificarPlaylist(List<Playlist> listaPlaylist, int posicion) {
        Intent intent = new Intent(this, ActivityDetallePlaylist.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ActivityDetallePlaylist.PLAYLIST_KEY, (Serializable) listaPlaylist);
        bundle.putInt(ActivityDetallePlaylist.POSICION_KEY_PLAYLIST, posicion);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void notificarCancion(Cancion cancion) {
        fragmentReproductorChico.setearDatos(cancion);
    }

    public void clickBotonesInferiores(String clickeado) {

        switch (clickeado) {
            case "home":
                if (homeIsClicked == true) {
                    homeIsClicked = false;
                } else {
                    homeIsClicked = true;
                    playlistIsClicked = false;
                    imageHome.setImageResource(R.drawable.icono_home_naranja_28dp);
                    imagePlaylist.setImageResource(R.drawable.icono_playlist);

                    fragmentPantallaInicio = new FragmentPantallaInicio();
                    fragmentManager = getSupportFragmentManager();
                    FragmentHelper.cargarFragment(fragmentPantallaInicio,
                            R.id.container_fragment,
                            fragmentManager);

                }
                break;
            case "playlist":
                if (playlistIsClicked == true) {
                    playlistIsClicked = false;
                } else {
                    playlistIsClicked = true;
                    homeIsClicked = false;
                    imagePlaylist.setImageResource(R.drawable.icono_playlist_play_naranja_28dp);
                    imageHome.setImageResource(R.drawable.icono_home_blanco_24dp);

                    fragmentPlaylist = new FragmentPlaylist();
                    fragmentManager = getSupportFragmentManager();
                    FragmentHelper.cargarFragmentConBackStack(fragmentPlaylist,
                            R.id.container_fragment,
                            fragmentManager);
                }
                break;


        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.perfil:
                fragmentPerfil = new FragmentPerfil();
                FragmentHelper.cargarFragmentConBackStack(fragmentPerfil,
                        R.id.container_fragment,
                        fragmentManager);
                break;
         /*   case R.id.favoritos:
                Toast.makeText(this, "Click Favoritos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.configuracion:
                Toast.makeText(this, "Click Configuracion", Toast.LENGTH_SHORT).show();
                break;*/
        }
        drawerLayout.closeDrawers();
        return false;
    }

    @Override
    public void cargarReproductorGrande() {
        FragmentHelper.cargarFragmentConBackStack(fragmentReproductor,
                R.id.drawer_layout,
                fragmentManager);
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
}