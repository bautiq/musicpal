package com.example.user.musicpal.view;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.ControllerGlobal;
import com.example.user.musicpal.controller.MediaPlayerGlobal;
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.utils.ResultListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentReproductor extends Fragment {

    public static final String ALBUM_KEY = "album_key";

    private TextView textViewArtista;
    private TextView textViewTitulo;
    private ImageView imagen;
    private ImageView buttonPlayPausa;
    private ImageView buttonForward;
    private ImageView buttonBack;

    private Cancion cancionQueContiene;
    private MediaPlayer mP;
    private List<Cancion> playList;
    private NotificadorReproductorGrande notificadorReproductorGrande;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();
    private SeekBar seekbar;
    private Runnable runnable;
    private TextView tiempoTranscurrido;
    private TextView tiempoDeDuracion;
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private Integer posicionPlaylist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reproductor, container, false);

        textViewArtista = view.findViewById(R.id.text_reproductor_artista);
        textViewTitulo = view.findViewById(R.id.text_reproductor_cancion);
        imagen = view.findViewById(R.id.imagen_reproductor);
        buttonPlayPausa = view.findViewById(R.id.button_play_reproductorGrande);
        buttonForward = view.findViewById(R.id.button_forward_reproductorGrande);
        buttonBack = view.findViewById(R.id.button_back_reproductorGrande);

        final MediaPlayerGlobal mediaPlayerGlobal = MediaPlayerGlobal.getInstance();
        posicionPlaylist = mediaPlayerGlobal.getPosicionPlaylist();
        mP = mediaPlayerGlobal.getMediaPlayer();
        cancionQueContiene = mediaPlayerGlobal.getCancion();
        playList = mediaPlayerGlobal.getPlayList();
        final Integer[] posicionNueva = {posicionPlaylist};
        seekbar = view.findViewById(R.id.seekBar);
        seekbar.setClickable(false);
        tiempoTranscurrido = view.findViewById(R.id.text_tiempo_transcurrido);
        tiempoDeDuracion = view.findViewById(R.id.text_tiempo_restante);

        setearDatos(cancionQueContiene);

        finalTime = mP.getDuration();
        startTime = mP.getCurrentPosition();

        seekbar.setMax((int) finalTime);
        seekbar.setProgress((int) startTime);
        myHandler.postDelayed(updateSongTime, 100);

        mP.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekbar.setMax(mp.getDuration());
                playCycle();
                mp.start();
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean imput) {
                if (imput) {
                    mP.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        tiempoTranscurrido.setText(getHRM(mP.getCurrentPosition()));
        tiempoDeDuracion.setText(getHRM(mP.getDuration()));

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
                posicionNueva[0] = mediaPlayerGlobal.getPosicionPlaylist();
                cambioCancionAnterior(posicionNueva, mediaPlayerGlobal);

            }
        });
        buttonBack.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //este listener va a ir hacia atras 5 segundos en la cancion
                int temp = (int) startTime;

                if ((temp - backwardTime) > 0) {
                    startTime = startTime - backwardTime;
                    mP.seekTo((int) startTime);
                }
                return false;
            }
        });

        buttonForward.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //este listener va a ir hacia adelante 5 segundos en la cancion
                int temp = (int) startTime;

                if ((temp + forwardTime) <= finalTime) {
                    startTime = startTime + forwardTime;
                    mP.seekTo((int) startTime);
                }
                return false;
            }
        });

        buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionNueva[0] = mediaPlayerGlobal.getPosicionPlaylist();
                cambioCancionSiguiente(posicionNueva, mediaPlayerGlobal);
            }
        });

        mP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                cambioCancionSiguiente(posicionNueva, mediaPlayerGlobal);
            }
        });

        return view;
    }

    public void playCycle() {
        seekbar.setProgress(mP.getCurrentPosition());
        if (mP.isPlaying()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            };
            myHandler.postDelayed(runnable, 100);
        }
    }

    protected Runnable updateSongTime = new Runnable() {
        public void run() {
            startTime = mP.getCurrentPosition();
            tiempoTranscurrido.setText(getHRM(mP.getCurrentPosition()));
            tiempoDeDuracion.setText(getHRM(mP.getDuration()));
            seekbar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };

    private String getHRM(int milliseconds) {
        int seconds = (milliseconds / 1000) % 60;
        int minutes = ((milliseconds / (1000 * 60)) % 60);
        int hours = ((milliseconds / (1000 * 60 * 60)) % 24);
        return ((hours < 10) ? "0" + hours : hours) + ":" +
                ((minutes < 10) ? "0" + minutes : minutes) + ":" +
                ((seconds < 10) ? "0" + seconds : seconds);
    }

    @Override
    public void onPause() {
        super.onPause();
        notificadorReproductorGrande.notificarPlayPausa();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificadorReproductorGrande = (NotificadorReproductorGrande) context;
    }

    @Override
    public void onResume() {

        super.onResume();
        cancionQueContiene = MediaPlayerGlobal.getInstance().getCancion();
        setearDatos(cancionQueContiene);
    }

    public void setearDatos(final Cancion cancion) {
        obtenerCancion(cancion);
        if (mP.isPlaying()) {
            buttonPlayPausa.setBackgroundResource(R.drawable.ic_pause_circle_outline);
        } else {
            buttonPlayPausa.setBackgroundResource(R.drawable.ic_play_circle);
        }
    }

    public void obtenerCancion(final Cancion cancion) {
        ControllerGlobal controllerGlobal = new ControllerGlobal(getContext());
        controllerGlobal.obtenerCancionOnline(new ResultListener<Cancion>() {
            @Override
            public void finish(Cancion resultado) {
                Picasso.with(getContext()).load(resultado.getAlbum().getImagenUrl()).into(imagen);
                textViewArtista.setText(resultado.getArtista().getNombre());
                textViewTitulo.setText(resultado.getTitle());
            }
        }, cancion.getId());
    }


    protected interface NotificadorReproductorGrande {
        public void notificarPlayPausa();
    }

    public void cambioCancionSiguiente(Integer[] posicionNueva, MediaPlayerGlobal mediaPlayerGlobal) {
        if (!(posicionNueva[0] + 1 > playList.size() - 1)) {
            posicionNueva[0] += 1;
            posicionPlaylist = posicionNueva[0];
            Cancion cancionSiguiente = playList.get(posicionNueva[0]);
            cancionQueContiene = cancionSiguiente;
            setearDatos(cancionQueContiene);
            try {
                mediaPlayerGlobal.setearPlaylist(playList, true, posicionPlaylist);
                notificadorReproductorGrande.notificarPlayPausa();
                buttonPlayPausa.setBackgroundResource(R.drawable.ic_pause_circle_outline);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void cambioCancionAnterior(Integer[] posicionNueva, MediaPlayerGlobal mediaPlayerGlobal) {
        if (!(posicionNueva[0] <= 0)) {
            posicionNueva[0] -= 1;
            posicionPlaylist = posicionNueva[0];
            Cancion cancionSiguiente = playList.get(posicionNueva[0]);
            cancionQueContiene = cancionSiguiente;
            setearDatos(cancionQueContiene);
            try {
                mediaPlayerGlobal.setearPlaylist(playList, true, posicionPlaylist);
                notificadorReproductorGrande.notificarPlayPausa();
                buttonPlayPausa.setBackgroundResource(R.drawable.ic_pause_circle_outline);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}