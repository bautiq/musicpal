package com.example.user.musicpal;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements fragmentPantallaInicio.NotificadorActivities {
    private ImageView imageHome;
    private ImageView imagePlaylist;
    private ImageView imageDownload;
    private ImageView imageMore;
    private ImageView imageProfile;
    private ImageView imageSearch;
    private ImageView botonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarFragment(new fragmentPantallaInicio());
        imageHome = findViewById(R.id.home_button);
        imagePlaylist = findViewById(R.id.playlist_button);
        imageDownload = findViewById(R.id.download_button);
        imageMore = findViewById(R.id.more_button);
        imageProfile = findViewById(R.id.profile_button);
        imageSearch = findViewById(R.id.search_button);
        botonPlay = findViewById(R.id.boton_play);


        botonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click Play", Toast.LENGTH_SHORT).show();
            }
        });

        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click Home", Toast.LENGTH_SHORT).show();
            }
        });

        imagePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Click Playlist", Toast.LENGTH_SHORT).show();
            }
        });
        imageDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click Download", Toast.LENGTH_SHORT).show();
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

    public void cargarFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void recibirData(String artista, String album, String anio) {
        Intent intent = new Intent(this, ActivitySecundariaPaginaPrincipal.class);
        Bundle bundle = new Bundle();
        bundle.putString(ActivitySecundariaPaginaPrincipal.CLAVE_ARTISTA, artista);
        bundle.putString(ActivitySecundariaPaginaPrincipal.CLAVE_ALBUM, album);
        bundle.putString(ActivitySecundariaPaginaPrincipal.CLAVE_ANIO, anio);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
