package com.example.user.musicpal.model.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.MediaPlayerGlobal;
import com.example.user.musicpal.model.pojo.Cancion;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdapterTopCanciones extends RecyclerView.Adapter {
    private NotificadorTopCancionesCelda notificadorTopCancionesCelda;
    private List<Cancion> listaDeCanciones;
    private Context context;
    private Cancion cancion;

    public AdapterTopCanciones(Context context, NotificadorTopCancionesCelda notificadorTopCancionesCelda) {
        this.notificadorTopCancionesCelda = notificadorTopCancionesCelda;
        this.listaDeCanciones = new ArrayList<>();
        this.context = context;
        this.cancion = new Cancion();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_recycler_cancion_top, parent, false);
        ViewHolderCancion viewHolderCancion = new ViewHolderCancion(view);
        return viewHolderCancion;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Cancion cancion = listaDeCanciones.get(position);
        ViewHolderCancion viewHolderCancion = (ViewHolderCancion) holder;
        viewHolderCancion.armarCeldaCancion(cancion);
    }

    @Override
    public int getItemCount() {
        if (listaDeCanciones != null) {
            return listaDeCanciones.size();
        } else {
            return 0;
        }

    }

    public void agregarCanciones(List<Cancion> resultado) {
        for (Cancion cancionAgregar : resultado) {
            if (!this.listaDeCanciones.contains(cancionAgregar)) {
                this.listaDeCanciones.add(cancionAgregar);
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolderCancion extends RecyclerView.ViewHolder {
        private ImageView imagen;
        private TextView textNombre;
        private TextView textArtista;

        public ViewHolderCancion(View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imagen_fragment_cancion_top);
            textNombre = itemView.findViewById(R.id.title_fragment_cancion_top);
            textArtista = itemView.findViewById(R.id.title_fragment_artista_cancion_top);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cancion cancionAreproducir = listaDeCanciones.get(getAdapterPosition());
                    try {
                        MediaPlayerGlobal mediaPlayerGlobal = MediaPlayerGlobal.getInstance();
                        mediaPlayerGlobal.setearPlaylist(listaDeCanciones, true, getAdapterPosition());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    notificadorTopCancionesCelda.notificarCeldaClickeadaDeCancion(cancionAreproducir);
                }
            });
        }

        public void armarCeldaCancion(Cancion cancion) {
            textNombre.setText(cancion.getTitle());
            textArtista.setText(cancion.getArtista().getNombre());
            Picasso.get()
                    .load(cancion.getArtista().getImagenUrl())
                    .placeholder(R.drawable.placemusic2)
                    .into(imagen);
        }
    }

    public interface NotificadorTopCancionesCelda {
        public void notificarCeldaClickeadaDeCancion(Cancion cancion);
    }
}
