package com.example.user.musicpal;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
                Integer integer = imagenUno.getId();
                notificador.recibirData(integer);
            }
        });
        imagenDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Click imagen", Toast.LENGTH_SHORT).show();
            }
        });
        imagenTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Click imagen", Toast.LENGTH_SHORT).show();
            }
        });
        imagenCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Click imagen", Toast.LENGTH_SHORT).show();
            }
        });
        imagenCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Click imagen", Toast.LENGTH_SHORT).show();
            }
        });
        imagenSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Click imagen", Toast.LENGTH_SHORT).show();
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
        public void recibirData(Integer id);
    }
}
