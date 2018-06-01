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
                clickBotonesInferiores("home");
                if (homeIsClicked == true) {
                    imageHome.setImageResource(R.drawable.icono_home_naranja_28dp);
                    imagePlaylist.setImageResource(R.drawable.playlist_icon);
                    imageMore.setImageResource(R.drawable.icono_more_horiz_blanco_24dp);
                    imageExplore.setImageResource(R.drawable.icono_explore_blanco_24dp);
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
                clickBotonesInferiores("playlist");
                if (playlistIsClicked == true) {
                    imagePlaylist.setImageResource(R.drawable.icono_playlist_play_naranja_28dp);
                    imageHome.setImageResource(R.drawable.icono_home_blanco_24dp);
                    imageExplore.setImageResource(R.drawable.icono_explore_blanco_24dp);
                    imageMore.setImageResource(R.drawable.icono_more_horiz_blanco_24dp);
                }
            }
        });
        imageExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBotonesInferiores("explore");
                if (exploreIsClicked == true) {
                    imageExplore.setImageResource(R.drawable.icono_explore_naranja_28dp);
                    imageHome.setImageResource(R.drawable.icono_home_blanco_24dp);
                    imagePlaylist.setImageResource(R.drawable.playlist_icon);
                }
            }
        });

        imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBotonesInferiores("more");
                if(moreIsClicked == true){
                    imageMore.setImageResource(R.drawable.icono_more_horiz_naranja_28dp);
                    imageExplore.setImageResource(R.drawable.icono_explore_blanco_24dp);
                    imageHome.setImageResource(R.drawable.icono_home_blanco_24dp);
                    imagePlaylist.setImageResource(R.drawable.playlist_icon);
                }
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

    public void clickBotonesInferiores(String clickeado) {

        switch (clickeado) {
            case "home":
                if (homeIsClicked == true) {
                    homeIsClicked = false;
                } else {
                    homeIsClicked = true;
                    playlistIsClicked = false;
                    moreIsClicked = false;
                    exploreIsClicked = false;

                }
                break;
            case "playlist":
                if (playlistIsClicked == true) {
                    playlistIsClicked = false;
                } else {
                    playlistIsClicked = true;
                    homeIsClicked = false;
                    moreIsClicked = false;
                    exploreIsClicked = false;
                }
                break;

            case "more":
                if (moreIsClicked == true) {
                    moreIsClicked = false;
                } else {
                    moreIsClicked = true;
                    exploreIsClicked = false;
                    homeIsClicked = false;
                    playlistIsClicked = false;
                }
                break;
            case "explore":
                if (exploreIsClicked == true) {
                    exploreIsClicked = false;
                } else {
                    exploreIsClicked = true;
                    moreIsClicked = false;
                    homeIsClicked = false;
                    playlistIsClicked = false;
                }
                break;
        }

    }
}
