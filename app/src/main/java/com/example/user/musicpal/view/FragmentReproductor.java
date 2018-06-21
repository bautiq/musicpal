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

import com.example.user.musicpal.R;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Cancion;

import java.io.IOException;
import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentReproductor extends Fragment {
    public static final String CANCION_KEY = "key_cancion";
    private TextView textViewPlayFrom;
    private TextView textViewPlayType;
    private ImageView imagen;
    private ImageView buttonPlayPausa;
    private Cancion cancionQueContiene;
    private ImageView buttonForward;
    private ImageView buttonBack;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reproductor, container, false);
        Bundle bundle = getArguments();
        cancionQueContiene = (Cancion) bundle.getSerializable(CANCION_KEY);
        textViewPlayFrom = view.findViewById(R.id.text_reproductor_playfrom);
        textViewPlayType = view.findViewById(R.id.text_reproductor_playtype);
        imagen = view.findViewById(R.id.imagen_reproductor);
        buttonPlayPausa = view.findViewById(R.id.button_play_reproductorGrande);
        buttonForward = view.findViewById(R.id.button_forward_reproductorGrande);
        buttonBack = view.findViewById(R.id.button_back_reproductorGrande);

        final MediaPlayer mP = MediaPlayer.create(getActivity(), R.raw.bitter_sweet_symphony);
        try {
            agregarCancionClikeada(mP, cancionQueContiene);
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

        return view;
    }



    private void agregarCancionClikeada(MediaPlayer mediaPlayer, Cancion cancion) throws IOException {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.reset();
        mediaPlayer.setDataSource(cancion.getUrlPreview());
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

}
