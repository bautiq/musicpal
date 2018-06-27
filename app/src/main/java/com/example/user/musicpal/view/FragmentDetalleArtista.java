package com.example.user.musicpal.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.ControllerGlobal;
import com.example.user.musicpal.model.adapters.AdapterCanciones;
import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.utils.ResultListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalleArtista extends Fragment implements AdapterCanciones.NotificadorCancionCelda {

    private static final String ARTISTA_RECIBIDO_KEY = "clave_artista";
    private ImageView imagenGrande;
    private TextView textArtista;
    private RecyclerView recyclerViewCanciones;
    private AdapterCanciones adapterCanciones;
    private List<Cancion> listaCanciones;
    private ControllerGlobal controllerCancion;
    private NotificadorCancion notificadorCancion;
    private Artista artista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_artista, container, false);
        imagenGrande = view.findViewById(R.id.id_imagen_vista_previa_artista);
        textArtista = view.findViewById(R.id.id_nombre_artista_artista);
        recyclerViewCanciones = view.findViewById(R.id.recycler_canciones_de_artista_id);

        Bundle bundle = getArguments();
        artista = (Artista) bundle.getSerializable(ARTISTA_RECIBIDO_KEY);

        controllerCancion = new ControllerGlobal(getContext());

        listaCanciones = new ArrayList<>();
        adapterCanciones = new AdapterCanciones(listaCanciones, getActivity().getSupportFragmentManager(), this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), linearLayoutManager.getOrientation());

        recyclerViewCanciones.setLayoutManager(linearLayoutManager);
        recyclerViewCanciones.addItemDecoration(dividerItemDecoration);
        recyclerViewCanciones.setHasFixedSize(true);
        recyclerViewCanciones.setAdapter(adapterCanciones);

        obtenerCancionesPorArtista(artista);

        textArtista.setText("Artista: " + artista.getNombre());
        Picasso.with(getContext()).load(artista.getImagenUrl()).placeholder(R.drawable.placeholder).into(imagenGrande);

        return view;
    }

    private void obtenerCancionesPorArtista(Artista artista) {
        controllerCancion.obtenerCancionesPorArtista(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                adapterCanciones.setListaDeCanciones(resultado);
            }
        },artista.getId());
    }

    public static FragmentDetalleArtista dameUnFragment(Artista artista) {
        FragmentDetalleArtista fragmentCreado = new FragmentDetalleArtista();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARTISTA_RECIBIDO_KEY, artista);
        fragmentCreado.setArguments(bundle);
        return fragmentCreado;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificadorCancion = (NotificadorCancion) context;
    }

    @Override
    public void notificarCeldaClikeada(Cancion cancion) {
        notificadorCancion.notificarCancion(cancion, artista);
    }

    public interface NotificadorCancion {
        public void notificarCancion(Cancion cancion, Artista artista);
    }
}