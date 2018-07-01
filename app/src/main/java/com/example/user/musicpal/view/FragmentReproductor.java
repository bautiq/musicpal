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
    private Cancion cancionQueContiene;
    private ImageView buttonForward;
    private ImageView buttonBack;
    private MediaPlayer mP;
    private Album albumRecibido;
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
        mP = MediaPlayerGlobal.getInstance().getMediaPlayer();
        cancionQueContiene = MediaPlayerGlobal.getInstance().getCancion();
        //albumRecibido = cancionQueContiene.getAlbum();

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

        mP.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                Cancion cancionSiguiente = MediaPlayerGlobal.getInstance().getCancion();
                setearDatos(cancionSiguiente);
            }
        });

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
                Toast.makeText(getContext(), "Click Back Track", Toast.LENGTH_SHORT).show();
                int temp = (int) startTime;

                if ((temp - backwardTime) > 0) {
                    startTime = startTime - backwardTime;
                    mP.seekTo((int) startTime);
                }
            }
        });

        buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Click Forward Track", Toast.LENGTH_SHORT).show();
                int temp = (int) startTime;

                if ((temp + forwardTime) <= finalTime) {
                    startTime = startTime + forwardTime;
                    mP.seekTo((int) startTime);
                }
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
       /* textViewArtista.setText(cancion.getArtista().getNombre());
        textViewTitulo.setText(cancion.getTitle());
        finalTime = cancion.getDuration();*/
        /*if(cancion.getArtista().getImagenUrl() == null){
            if(cancion.getAlbum() != null){
                Picasso.with(getContext()).load(cancion.getAlbum().getImagenUrl()).into(imagen);
            }
        }else if (cancion.getAlbum().getImagenUrl() == null){
            if(cancion.getArtista() != null){
                Picasso.with(getContext()).load(cancion.getArtista().getImagenUrl()).into(imagen);
            }
        }
        Picasso.with(getContext()).load(cancion.getArtista().getImagenUrl()).into(imagen);*/
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
}