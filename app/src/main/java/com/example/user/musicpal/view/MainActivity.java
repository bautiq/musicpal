package com.example.user.musicpal.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.FragmentHelper;
import com.example.user.musicpal.R;


public class MainActivity extends AppCompatActivity implements FragmentPantallaInicio.NotificadorActivities, NavigationView.OnNavigationItemSelectedListener {

    private ImageView imageHome;
    private ImageView imagePlaylist;
    private ImageView imageCompartir;
    private ImageView imageMore;
    private boolean homeIsClicked;
    private boolean playlistIsClicked;
    private boolean compartirIsClicked;
    private boolean moreIsClicked;
    private ImageView imageProfile;
    private ImageView imageSearch;
    private Button botonRewind;
    private Button botonForward;
    private Button botonPlay;
    private FragmentPantallaInicio fragmentPantallaInicio;
    private FragmentManager fragmentManager;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private FragmentPerfil fragmentPerfil;
    private FragmentPlaylist fragmentPlaylist;
    private FragmentCompartir fragmentCompartir;


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
        imageProfile = findViewById(R.id.menu_button);
        imageSearch = findViewById(R.id.search_button);
        botonPlay = findViewById(R.id.boton_play);
        botonForward = findViewById(R.id.boton_forward);
        botonRewind = findViewById(R.id.boton_rewind);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView.setNavigationItemSelectedListener(this);

        fragmentPantallaInicio = new FragmentPantallaInicio();
        fragmentManager = getSupportFragmentManager();
        FragmentHelper.cargarFragment(fragmentPantallaInicio, R.id.container_fragment, fragmentManager);

        final MediaPlayer mP = MediaPlayer.create(MainActivity.this, R.raw.bitter_sweet_symphony);
        botonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mP.isPlaying()) {
                    mP.pause();
                    botonPlay.setBackgroundResource(R.drawable.ic_play_circle);
                } else {
                    mP.start();
                    botonPlay.setBackgroundResource(R.drawable.ic_pause_circle_outline);
                }
            }
        });

        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBotonesInferiores("home");
            }
        });

        botonRewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click Rewind", Toast.LENGTH_SHORT).show();
            }
        });

        botonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click Forward", Toast.LENGTH_SHORT).show();
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
        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click Menu", Toast.LENGTH_SHORT).show();
            }
        });
        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click Search", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void notificar(Album album, int posicion, int idRecycler) {
        Intent intent = new Intent(this, DetalleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetalleActivity.ALBUM_KEY, album);
        bundle.putInt(DetalleActivity.POSICION_KEY, posicion);
        bundle.putInt(DetalleActivity.ID_RECYCLER_CLICKEADO, idRecycler);
        intent.putExtras(bundle);
        startActivity(intent);
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
                    imagePlaylist.setImageResource(R.drawable.playlist_icon);
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
                    imagePlaylist.setImageResource(R.drawable.playlist_icon);
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
                    imagePlaylist.setImageResource(R.drawable.playlist_icon);
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
}