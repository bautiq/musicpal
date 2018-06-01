package com.example.user.musicpal;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements FragmentPantallaInicio.NotificadorActivities {

    private ImageView imageHome;
    private ImageView imagePlaylist;
    private ImageView imageExplore;
    private ImageView imageMore;
    private boolean homeIsClicked;
    private boolean playlistIsClicked;
    private boolean exploreIsClicked;
    private boolean moreIsClicked;
    private ImageView imageProfile;
    private ImageView imageSearch;
    private Button botonRewind;
    private Button botonForward;
    private Button botonPlay;
    private FragmentPantallaInicio fragmentPantallaInicio;
    private FragmentManager fragmentManager;
    private boolean algunoFueClickeado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeIsClicked = true;
        playlistIsClicked = false;
        exploreIsClicked = false;
        moreIsClicked = false;
        algunoFueClickeado = false;
        imageHome = findViewById(R.id.home_button);
        imagePlaylist = findViewById(R.id.playlist_button);
        imageExplore = findViewById(R.id.explore_button);
        imageMore = findViewById(R.id.more_button);
        imageProfile = findViewById(R.id.profile_button);
        imageSearch = findViewById(R.id.search_button);
        botonPlay = findViewById(R.id.boton_play);
        botonForward = findViewById(R.id.boton_forward);
        botonRewind = findViewById(R.id.boton_rewind);

        fragmentPantallaInicio = new FragmentPantallaInicio();
        fragmentManager = getSupportFragmentManager();
        FragmentHelper.cargarFragmemt(fragmentPantallaInicio, R.id.container_fragment, fragmentManager);

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
                Toast.makeText(MainActivity.this, "Click Home", Toast.LENGTH_SHORT).show();
                clickBotonesInferiores(homeIsClicked, "home");
                if (homeIsClicked == true) {
                    imageHome.setImageResource(R.drawable.icono_home_naranja_28dp);
                } else {
                    imageHome.setImageResource(R.drawable.icono_home_blanco_24dp);
                }
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
                Toast.makeText(MainActivity.this, "Click Playlist", Toast.LENGTH_SHORT).show();
                clickBotonesInferiores(playlistIsClicked, "playlist");
                if (playlistIsClicked == true) {
                    imagePlaylist.setImageResource(R.drawable.icono_playlist_play_naranja_28dp);
                } else {
                    imagePlaylist.setImageResource(R.drawable.playlist_icon);
                }
            }
        });
        imageExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click explore", Toast.LENGTH_SHORT).show();
            }
        });

        imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click More", Toast.LENGTH_SHORT).show();
            }
        });
        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click Profile", Toast.LENGTH_SHORT).show();
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
    public void notificar(Album album) {
        Intent intent = new Intent(this, DetalleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetalleFragment.ALBUM_KEY, album);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void clickBotonesInferiores(boolean booleano, String clickeado) {

        switch (clickeado) {
            case "home":
                if (booleano == true) {
                    booleano = false;
                    homeIsClicked = false;
                } else {
                    homeIsClicked = true;
                    booleano = true;
                }
                break;
            case "playlist":
                if (booleano == true) {
                    booleano = false;
                    playlistIsClicked = false;
                } else {
                    playlistIsClicked = true;
                    booleano = true;
                }
                break;

            case "more":
                if (booleano == true) {
                    booleano = false;
                    moreIsClicked = false;
                } else {
                    moreIsClicked = true;
                    booleano = true;
                }
                break;
            case "explore":
                if (booleano == true) {
                    booleano = false;
                    exploreIsClicked = false;
                } else {
                    exploreIsClicked = true;
                    booleano = true;
                }
                break;
        }

    }
}
