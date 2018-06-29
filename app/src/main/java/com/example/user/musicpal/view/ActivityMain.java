package com.example.user.musicpal.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.List;


public class ActivityMain extends AppCompatActivity implements FragmentPantallaInicio.NotificadorActivities, NavigationView.OnNavigationItemSelectedListener, FragmentReproductorChico.NotificadorReproductorChico {

    private ImageView imageHome;
    private ImageView imagePlaylist;
    private ImageView imageCompartir;
    private ImageView imageMore;
    private boolean homeIsClicked;
    private boolean playlistIsClicked;
    private boolean compartirIsClicked;
    private boolean moreIsClicked;
    private ImageView imageMenu;
    private ImageView imageSearch;

    private FragmentPantallaInicio fragmentPantallaInicio;
    private FragmentManager fragmentManager;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private FragmentPerfil fragmentPerfil;
    private FragmentPlaylist fragmentPlaylist;
    private FragmentCompartir fragmentCompartir;
    private MediaPlayerGlobal mediaPlayerGlobal;
    private FragmentReproductorChico fragmentReproductorChico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeIsClicked = true;
        playlistIsClicked = false;
        compartirIsClicked = false;
        moreIsClicked = false;
        imageHome = findViewById(R.id.home_button);
        imagePlaylist = findViewById(R.id.playlist_button);
        imageCompartir = findViewById(R.id.compartir_button);
        imageMore = findViewById(R.id.more_button);
        imageMenu = findViewById(R.id.menu_button);
        imageSearch = findViewById(R.id.search_button);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        mediaPlayerGlobal = MediaPlayerGlobal.getInstance();
        navigationView.setNavigationItemSelectedListener(this);

        fragmentPantallaInicio = new FragmentPantallaInicio();
        fragmentManager = getSupportFragmentManager();
        FragmentHelper.cargarFragment(fragmentPantallaInicio, R.id.container_fragment, fragmentManager);
        fragmentReproductorChico = new FragmentReproductorChico();
        FragmentHelper.cargarFragment(fragmentReproductorChico, R.id.contenedor_reproductor_chico, fragmentManager);

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
        imageCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBotonesInferiores("compartir");
            }
        });

        imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickBotonesInferiores("more");
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
                FragmentHelper.cargarFragmentConBackStack(new FragmentBusqueda(), R.id.container_fragment, fragmentManager);
            }
        });

    }

    public void notificarAlbum(List<Album> list, int posicion, String categoria) {
        Intent intent = new Intent(this, ActivityDetalleAlbum.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ActivityDetalleAlbum.ALBUM_KEY, (Serializable) list);
        bundle.putInt(ActivityDetalleAlbum.POSICION_KEY, posicion);
        bundle.putString(ActivityDetalleAlbum.CATEGORIA_CLICKEADA, categoria);
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

    }

    public void clickBotonesInferiores(String clickeado) {

        switch (clickeado) {
            case "home":
                if (homeIsClicked == true) {
                    homeIsClicked = false;
                } else {
                    homeIsClicked = true;
                    playlistIsClicked = false;
                    moreIsClicked = false;
                    compartirIsClicked = false;
                    imageHome.setImageResource(R.drawable.icono_home_naranja_28dp);
                    imagePlaylist.setImageResource(R.drawable.icono_playlist);
                    imageMore.setImageResource(R.drawable.icono_more_horiz_blanco_24dp);
                    imageCompartir.setImageResource(R.drawable.icono_compartir_blanco_24dp);

                    fragmentPantallaInicio = new FragmentPantallaInicio();
                    fragmentManager = getSupportFragmentManager();
                    FragmentHelper.cargarFragment(fragmentPantallaInicio, R.id.container_fragment, fragmentManager);

                }
                break;
            case "playlist":
                if (playlistIsClicked == true) {
                    playlistIsClicked = false;
                } else {
                    playlistIsClicked = true;
                    homeIsClicked = false;
                    moreIsClicked = false;
                    compartirIsClicked = false;
                    imagePlaylist.setImageResource(R.drawable.icono_playlist_play_naranja_28dp);
                    imageHome.setImageResource(R.drawable.icono_home_blanco_24dp);
                    imageCompartir.setImageResource(R.drawable.icono_compartir_blanco_24dp);
                    imageMore.setImageResource(R.drawable.icono_more_horiz_blanco_24dp);

                    fragmentPlaylist = new FragmentPlaylist();
                    fragmentManager = getSupportFragmentManager();
                    FragmentHelper.cargarFragmentConBackStack(fragmentPlaylist, R.id.container_fragment, fragmentManager);
                }
                break;

            case "more":
                if (moreIsClicked == true) {
                    moreIsClicked = false;
                } else {
                    moreIsClicked = true;
                    compartirIsClicked = false;
                    homeIsClicked = false;
                    playlistIsClicked = false;
                    imageMore.setImageResource(R.drawable.icono_more_horiz_naranja_28dp);
                    imageCompartir.setImageResource(R.drawable.icono_compartir_blanco_24dp);
                    imageHome.setImageResource(R.drawable.icono_home_blanco_24dp);
                    imagePlaylist.setImageResource(R.drawable.icono_playlist);
                }
                break;
            case "compartir":
                if (compartirIsClicked == true) {
                    compartirIsClicked = false;
                } else {
                    compartirIsClicked = true;
                    moreIsClicked = false;
                    homeIsClicked = false;
                    playlistIsClicked = false;
                    imageCompartir.setImageResource(R.drawable.icono_compartir_naranja_24dp);
                    imageHome.setImageResource(R.drawable.icono_home_blanco_24dp);
                    imagePlaylist.setImageResource(R.drawable.icono_playlist);
                    imageMore.setImageResource(R.drawable.icono_more_horiz_blanco_24dp);
                    fragmentCompartir = new FragmentCompartir();
                    fragmentManager = getSupportFragmentManager();
                    FragmentHelper.cargarFragmentConBackStack(fragmentCompartir, R.id.container_fragment, fragmentManager);
                }
                break;
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.perfil:
                fragmentPerfil = new FragmentPerfil();
                FragmentHelper.cargarFragmentConBackStack(fragmentPerfil, R.id.container_fragment, fragmentManager);
                break;
            case R.id.favoritos:
                Toast.makeText(this, "Click Favoritos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.configuracion:
                Toast.makeText(this, "Click Configuracion", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawers();
        return false;
    }

    @Override
    public void cargarReproductorGrande() {
        FragmentHelper.cargarFragmentConBackStack(new FragmentReproductor(), R.id.container_reproductor_grande_main, fragmentManager);
    }
}