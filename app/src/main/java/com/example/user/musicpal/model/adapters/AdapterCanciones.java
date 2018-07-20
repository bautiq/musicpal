package com.example.user.musicpal.model.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.MediaPlayerGlobal;
import com.example.user.musicpal.model.pojo.Cancion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdapterCanciones extends RecyclerView.Adapter {

    private List<Cancion> listaDeCanciones;
    private FragmentManager fragmentManager;
    private Cancion cancion;
    private NotificadorCancionCelda notificadorCancionCelda;

    public AdapterCanciones(List<Cancion> listaDeCanciones, FragmentManager fragmentManager,
                            NotificadorCancionCelda notificadorCancionCelda) {

        this.listaDeCanciones = listaDeCanciones;
        this.fragmentManager = fragmentManager;
        this.notificadorCancionCelda = notificadorCancionCelda;
    }

    public AdapterCanciones(FragmentManager fragmentManager, NotificadorCancionCelda notificadorCancionCelda) {
        listaDeCanciones = new ArrayList<>();
        this.fragmentManager = fragmentManager;
        this.notificadorCancionCelda = notificadorCancionCelda;
    }

    public List<Cancion> getListaDeCanciones() {
        return listaDeCanciones;
    }

    public void setListaDeCanciones(List<Cancion> listaDeCanciones) {
        this.listaDeCanciones = listaDeCanciones;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_recycler_cancion, parent, false);
        ViewHolderCancion viewHolderCancion = new ViewHolderCancion(view);
        return viewHolderCancion;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        cancion = listaDeCanciones.get(position);
        ViewHolderCancion viewHolderCancion = (ViewHolderCancion) holder;
        viewHolderCancion.cargarCancion(cancion);
    }

    @Override
    public int getItemCount() {
        if (listaDeCanciones != null) {
            return listaDeCanciones.size();
        } else {
            return 0;
        }
    }

    public class ViewHolderCancion extends RecyclerView.ViewHolder {

        private TextView textViewNombre;
        private ImageView imagenPlay;
        private ImageView imagenCompartir;


        public ViewHolderCancion(final View itemView) {
            super(itemView);
            this.textViewNombre = itemView.findViewById(R.id.nombre_canciones_id);
            this.imagenPlay = itemView.findViewById(R.id.play_chico);
            this.imagenCompartir = itemView.findViewById(R.id.compartir_chico);

            textViewNombre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cancion cancionAreproducir = listaDeCanciones.get(getAdapterPosition());
                    try {
                        MediaPlayerGlobal mediaPlayerGlobal = MediaPlayerGlobal.getInstance();
                        mediaPlayerGlobal.setearPlaylist(listaDeCanciones, true, getAdapterPosition());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    notificadorCancionCelda.notificarCeldaClikeada(cancionAreproducir);
                }
            });
            imagenPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cancion cancionAreproducir = listaDeCanciones.get(getAdapterPosition());
                    try {
                        MediaPlayerGlobal mediaPlayerGlobal = MediaPlayerGlobal.getInstance();
                        mediaPlayerGlobal.setearPlaylist(listaDeCanciones, true, getAdapterPosition());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    notificadorCancionCelda.notificarCeldaClikeada(cancionAreproducir);
                }
            });
            imagenCompartir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Click Compartir", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void cargarCancion(Cancion cancion) {
            textViewNombre.setText(cancion.getTitle());
        }
    }

    public interface NotificadorCancionCelda {
        public void notificarCeldaClikeada(Cancion cancion);
    }
}
