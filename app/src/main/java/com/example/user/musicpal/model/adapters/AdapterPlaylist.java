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
import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.ResultListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterPlaylist extends RecyclerView.Adapter {

    private Context context;
    private List<Playlist> listaPlaylist;
    private NotificadorPlaylistCelda notificadorPlaylistCelda;

    public AdapterPlaylist(Context context, NotificadorPlaylistCelda notificadorPlaylistCelda) {
        this.context = context;
        this.listaPlaylist = new ArrayList<>();
        this.notificadorPlaylistCelda = notificadorPlaylistCelda;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_recycler_scroll, parent, false);
        ViewHolderPlaylist viewHolderPlaylist = new ViewHolderPlaylist(view);
        return viewHolderPlaylist;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Playlist playlist = listaPlaylist.get(position);
        obtenerCancionesPorPlaylist(playlist);
        ViewHolderPlaylist viewHolderPlaylist = (ViewHolderPlaylist) holder;
        viewHolderPlaylist.armarCelda(playlist);
    }

    @Override
    public int getItemCount() {
        if (listaPlaylist != null) {
            return listaPlaylist.size();
        } else {
            return 0;
        }
    }


    public void agregarPlaylist(List<Playlist> playlists) {
        for (Playlist playlistAAgregar : playlists) {
            if (!listaPlaylist.contains(playlistAAgregar)) {
                listaPlaylist.add(playlistAAgregar);
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolderPlaylist extends RecyclerView.ViewHolder {
        private TextView textViewNombre;
        private ImageView imageView;

        public ViewHolderPlaylist(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.title_fragment_album);
            imageView = itemView.findViewById(R.id.imagen_fragment_album);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicionDeArtistaCliqueado = getAdapterPosition();
                    notificadorPlaylistCelda.notificarCeldaCliqueadaPlaylist(listaPlaylist, posicionDeArtistaCliqueado);
                }
            });
        }

        public void armarCelda(Playlist playlist) {
            textViewNombre.setText(playlist.getNombre());
            Picasso.with(context).load(playlist.getImagenPlaylistUrl()).placeholder(R.drawable.placeholder).into(imageView);
        }

    }

    public interface NotificadorPlaylistCelda {
        public void notificarCeldaCliqueadaPlaylist(List<Playlist> playlists, int posicion);
    }

    private void obtenerCancionesPorPlaylist(final Playlist playlist) {
        ControllerGlobal controllerCancion = new ControllerGlobal(context);
        controllerCancion.obtenerCancionesPorPlaylist(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                playlist.setListCanciones(resultado);
            }
        }, playlist.getId());
    }
}
