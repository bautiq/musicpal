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
import com.example.user.musicpal.controller.ControllerGlobal;
import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.utils.ResultListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterArtista extends RecyclerView.Adapter {
    private Context context;
    private List<Artista> artistaList;
    private NotificadorArtistaCelda notificadorArtistaCelda;

    public AdapterArtista(Context context, NotificadorArtistaCelda notificadorArtistaCelda) {
        this.context = context;
        this.artistaList = new ArrayList<>();
        this.notificadorArtistaCelda = notificadorArtistaCelda;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.celda_recycler_scroll, parent, false);
        ViewHolderArtista viewHolderArtista = new ViewHolderArtista(view);
        return viewHolderArtista;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Artista artista = artistaList.get(position);
        obtenerCancionesPorArtista(artista);
        ViewHolderArtista viewHolderArtista = (ViewHolderArtista) holder;
        viewHolderArtista.armarCelda(artista);
    }

    @Override
    public int getItemCount() {
        if (artistaList != null) {
            return artistaList.size();
        } else {
            return 0;
        }
    }

    public void agregarArtistas(List<Artista> artistas) {
        for (Artista artistaAAgregar : artistas) {
            if (!artistaList.contains(artistaAAgregar)) {
                artistaList.add(artistaAAgregar);
            }
        }
        notifyDataSetChanged();
    }

    private class ViewHolderArtista extends RecyclerView.ViewHolder {
        private TextView textViewNombre;
        private ImageView imagen;

        public ViewHolderArtista(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.title_fragment_album);
            imagen = itemView.findViewById(R.id.imagen_fragment_album);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicionDeArtistaCliqueado = getAdapterPosition();
                    notificadorArtistaCelda.notificarCeldaCliqueadaArtista(
                            artistaList, posicionDeArtistaCliqueado);
                }
            });
        }

        public void armarCelda(Artista artista) {
            textViewNombre.setText(artista.getNombre());
            Picasso.get()
                    .load(artista.getImagenUrl())
                    .placeholder(R.drawable.placeholder)
                    .into(imagen);
        }
    }

    public interface NotificadorArtistaCelda {
        public void notificarCeldaCliqueadaArtista(List<Artista> artistas, int posicion);
    }

    private void obtenerCancionesPorArtista(final Artista artista) {
        ControllerGlobal controllerCancion = new ControllerGlobal(context);
        controllerCancion.obtenerCancionesPorArtista(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                artista.setCancionList(resultado);
            }
        }, artista.getId());
    }
}
