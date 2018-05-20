package com.example.user.musicpal;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentPantallaInicio extends Fragment {
    private ImageView imagenUno;
    private ImageView imagenDos;
    private ImageView imagenTres;
    private ImageView imagenCuatro;
    private ImageView imagenCinco;
    private ImageView imagenSeis;
    private NotificadorActivities notificador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_pantalla_inicio, container, false);
        imagenUno = view.findViewById(R.id.imagen_uno);
        imagenDos = view.findViewById(R.id.imagen_dos);
        imagenTres = view.findViewById(R.id.imagen_tres);
        imagenCuatro = view.findViewById(R.id.imagen_cuatro);
        imagenCinco = view.findViewById(R.id.imagen_cinco);
        imagenSeis = view.findViewById(R.id.imagen_seis);

        imagenUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificador.recibirData("Linkin Park", "Meteora", "2003", R.drawable.meteora_album);
            }
        });
        imagenDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificador.recibirData("Mike Shinoda", "Post Traumatic", "2018", R.drawable.post_traumatic_album);
            }
        });
        imagenTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificador.recibirData("Logic", "Under Pressure", "2005", R.drawable.underpressure_album);
            }
        });
        imagenCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificador.recibirData("Eminem", "Not Afraid", "2003", R.drawable.eminem_single);
            }
        });
        imagenCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificador.recibirData("Tupac Shakur", "Loyal to the game", "2000", R.drawable.tupac_loyal_album);
            }
        });
        imagenSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificador.recibirData("The Verve", "Urban Hymns", "2009", R.drawable.theverve);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.notificador = (NotificadorActivities) context;
    }

    public interface NotificadorActivities {
        public void recibirData(String artista, String album, String anio, int imagen);
    }
}
