package com.example.user.musicpal.view;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.MediaPlayerGlobal;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.utils.FragmentHelper;


public class FragmentReproductorChico extends Fragment {

    private Button botonRewind;
    private Button botonForward;
    private Button botonPlay;
    private LinearLayout linearLayout;
    private NotificadorReproductorChico notificadorReproductorChico;
    private LinearLayout linearLayoutReproductor;
    private TextView textCancion;
    private TextView textArtista;
    private TextView textAlbum;
    private FragmentReproductor fragmentReproductor;
    private FragmentManager fragmentManager;
    private static Cancion cancion;
    private MediaPlayer mP;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reproductor_chico, container, false);


        linearLayout = view.findViewById(R.id.linear_layout_reproductor_chico);
        botonPlay = view.findViewById(R.id.boton_play);
        botonForward = view.findViewById(R.id.boton_forward);
        botonRewind = view.findViewById(R.id.boton_rewind);
        fragmentManager = getActivity().getSupportFragmentManager();
        textCancion = view.findViewById(R.id.id_text_cancion_reprochico);
        textArtista = view.findViewById(R.id.id_text_artista_reprochico);
        textAlbum = view.findViewById(R.id.id_text_album_reprochico);
        textArtista.setSelected(true);
        textCancion.setSelected(true);
        textAlbum.setSelected(true);

        MediaPlayerGlobal mediaPlayerGlobal = MediaPlayerGlobal.getInstance();
        cancion = mediaPlayerGlobal.getCancion();
        mP = mediaPlayerGlobal.getMediaPlayer();
        setearDatos(cancion);
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

        botonRewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Click Rewind", Toast.LENGTH_SHORT).show();
            }
        });

        botonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Click Forward", Toast.LENGTH_SHORT).show();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(getContext(), "Click Abrir fragment Reproductor", Toast.LENGTH_SHORT).show();

                notificadorReproductorChico.cargarReproductorGrande();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificadorReproductorChico = (NotificadorReproductorChico) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        cancion = MediaPlayerGlobal.getInstance().getCancion();
        setearDatos(cancion);
    }

    public void setearDatos(Cancion cancion) {
        textArtista.setText(cancion.getArtista().getNombre());
        textCancion.setText(cancion.getTitle());
        try {
            textAlbum.setText(cancion.getAlbum().getTitulo());
        } catch (NullPointerException e) {
            textAlbum.setText(" ");
        }
        if (mP.isPlaying()) {
            botonPlay.setBackgroundResource(R.drawable.ic_pause_circle_outline);
        }
    }

    public interface NotificadorReproductorChico {
        public void cargarReproductorGrande();
    }

}
