package com.example.user.musicpal.view;


import android.content.Context;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.MediaPlayerGlobal;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Cancion;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentReproductor extends Fragment {
    public static final String CANCION_KEY = "key_cancion";
    public static final String ALBUM_KEY = "album_key";
    public static final String ARTISTA_KEY = "artista_key";
    private TextView textViewArtista;
    private TextView textViewTitulo;
    private ImageView imagen;
    private ImageView buttonPlayPausa;
    private Cancion cancionQueContiene;
    private ImageView buttonForward;
    private ImageView buttonBack;
    private MediaPlayer mP;
    private Album albumRecibido;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reproductor, container, false);
        Bundle bundle = getArguments();
        cancionQueContiene = (Cancion) bundle.getSerializable(CANCION_KEY);
        albumRecibido = (Album) bundle.getSerializable(ALBUM_KEY);
        textViewArtista = view.findViewById(R.id.text_reproductor_artista);
        textViewTitulo = view.findViewById(R.id.text_reproductor_cancion);
        imagen = view.findViewById(R.id.imagen_reproductor);
        buttonPlayPausa = view.findViewById(R.id.button_play_reproductorGrande);
        buttonForward = view.findViewById(R.id.button_forward_reproductorGrande);
        buttonBack = view.findViewById(R.id.button_back_reproductorGrande);

        textViewTitulo.setText(cancionQueContiene.getTitle());
        textViewArtista.setText(cancionQueContiene.getArtista().getNombre());
        try {
            Picasso.with(getContext()).load(cancionQueContiene.getAlbum().getImagenUrl()).into(imagen);
        } catch (NullPointerException e) {
            Picasso.with(getContext()).load(albumRecibido.getImagenUrl()).into(imagen);
        }

        mP = MediaPlayer.create(getActivity(), R.raw.bitter_sweet_symphony);
        try {
            //esta linea siempre es false :/
            if (mP.isPlaying()) {
                mP.pause();
                agregarCancionClikeada(mP, cancionQueContiene);
            } else {
                agregarCancionClikeada(mP, cancionQueContiene);
            }

        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
        }
        buttonPlayPausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mP.isPlaying()) {
                    mP.pause();
                    buttonPlayPausa.setBackgroundResource(R.drawable.ic_play_circle);
                } else {
                    mP.start();
                    buttonPlayPausa.setBackgroundResource(R.drawable.ic_pause_circle_outline);
                }
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Click Back Track", Toast.LENGTH_SHORT).show();
            }
        });

        buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Click Forward Track", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mP != null) {
            mP.release();
        }
    }


    private void agregarCancionClikeada(MediaPlayer mediaPlayer, Cancion cancion) throws IOException {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.reset();
        mediaPlayer.setDataSource(cancion.getUrlPreview());
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

}