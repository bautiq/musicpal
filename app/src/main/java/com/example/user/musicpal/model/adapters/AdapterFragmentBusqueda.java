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

public class AdapterFragmentBusqueda extends RecyclerView.Adapter {


    private List<Cancion> listaDeCanciones;
    private Context context;
    private Cancion cancion;
    private NotificadorSEARCHCancionesCelda notificadorSEARCHCancionesCelda;


    public AdapterFragmentBusqueda(Context context, NotificadorSEARCHCancionesCelda notificadorSEARCHCancionesCelda) {
        this.notificadorSEARCHCancionesCelda = notificadorSEARCHCancionesCelda;
        this.listaDeCanciones = new ArrayList<>();
        this.context = context;
        this.cancion = new Cancion();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_fragment_search_2, parent, false);
        ViewHolderCancionBusqueda viewHolderCancionBusqueda = new ViewHolderCancionBusqueda(view);
        return viewHolderCancionBusqueda;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Cancion cancion = listaDeCanciones.get(position);
        ViewHolderCancionBusqueda viewHolderCancionBusqueda = (ViewHolderCancionBusqueda) holder;
        viewHolderCancionBusqueda.armarCeldaCancion(cancion);
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
            notifyDataSetChanged();
        }
    }

    public void obtenerCanciones(List<Cancion> resultado) {
        if (resultado != null) {
            listaDeCanciones = resultado;
        }
        notifyDataSetChanged();
    }

    public class ViewHolderCancionBusqueda extends RecyclerView.ViewHolder {
        private ImageView imagenArtista;
        private ImageView imagenAlbum;
        private TextView textNombre;
        private TextView textArtista;
        private TextView textViewAlbum;


        public ViewHolderCancionBusqueda(View itemView) {
            super(itemView);
            imagenArtista = itemView.findViewById(R.id.imagen_album_celda_busqueda2);
            imagenAlbum = itemView.findViewById(R.id.imagen_album_celda_busqueda2);
            textNombre = itemView.findViewById(R.id.nombre_canciones_id_celda_recycler_search2);
            textArtista = itemView.findViewById(R.id.nombre_artista_id_celda_recycler_search2);
            textViewAlbum = itemView.findViewById(R.id.nombre_album_id_celda_recycler_search2);

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
                    notificadorSEARCHCancionesCelda.notificarCeldaClickeadaDeCancion(cancionAreproducir);
                }
            });
        }

        public void armarCeldaCancion(Cancion cancion) {
            textNombre.setText(cancion.getTitle());
            textArtista.setText(cancion.getArtista().getNombre());
            textViewAlbum.setText((cancion.getAlbum().getTitulo()));
            Picasso.get()
                    .load(cancion.getArtista().getImagenUrl())
                    .placeholder(R.drawable.placeholder)
                    .into(imagenArtista);
            Picasso.get()
                    .load(cancion.getAlbum().getImagenUrl())
                    .placeholder(R.drawable.placeholder)
                    .into(imagenAlbum);
        }
    }

    public interface NotificadorSEARCHCancionesCelda {
        public void notificarCeldaClickeadaDeCancion(Cancion cancion);
    }


}
