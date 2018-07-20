package com.example.user.musicpal.model.adapters;


import android.content.Context;
import android.media.Image;
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
    private NotificadorPlaylistUser notificadorPlaylistUser;
    private String tipoPlaylist;

    public AdapterPlaylist(Context context, NotificadorPlaylistCelda notificadorPlaylistCelda, String tipoPlaylist) {
        this.context = context;
        this.listaPlaylist = new ArrayList<>();
        this.notificadorPlaylistCelda = notificadorPlaylistCelda;
        this.tipoPlaylist = tipoPlaylist;
    }

    public AdapterPlaylist(Context context, NotificadorPlaylistUser notificadorPlaylistUser, String tipoPlaylist) {
        this.context = context;
        this.listaPlaylist = new ArrayList<>();
        this.notificadorPlaylistUser = notificadorPlaylistUser;
        this.tipoPlaylist = tipoPlaylist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (tipoPlaylist.equals("user")) {
            View view = inflater.inflate(R.layout.celda_recycler_playlist_user, parent, false);
            ViewHolderPlaylistUser viewHolderPlaylistUser = new ViewHolderPlaylistUser(view);
            return viewHolderPlaylistUser;
        } else {
            View view = inflater.inflate(R.layout.celda_recycler_scroll, parent, false);
            ViewHolderPlaylist viewHolderPlaylist = new ViewHolderPlaylist(view);
            return viewHolderPlaylist;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Playlist playlist = listaPlaylist.get(position);
        obtenerCancionesPorPlaylist(playlist);
        if(tipoPlaylist.equals("user")){
            ViewHolderPlaylistUser viewHolderPlaylistUser = (ViewHolderPlaylistUser) holder;
            viewHolderPlaylistUser.armarCeldaPU(playlist);
        }else{
            ViewHolderPlaylist viewHolderPlaylist = (ViewHolderPlaylist) holder;
            viewHolderPlaylist.armarCelda(playlist);}
    }

    @Override
    public int getItemCount() {
        if (listaPlaylist != null) {
            return listaPlaylist.size();
        } else {
            return 0;
        }
    }

    public void agregarListaPlaylists(List<Playlist> playlists) {
        for (Playlist playlistAAgregar : playlists) {
            if (!listaPlaylist.contains(playlistAAgregar)) {
                listaPlaylist.add(playlistAAgregar);
            }
        }
        notifyDataSetChanged();
    }

    public void agregarPlaylist(Playlist playlistNueva) {
        if(listaPlaylist.contains(playlistNueva)){
            return;
        }
        listaPlaylist.add(playlistNueva);
        notifyDataSetChanged();
    }


    private class ViewHolderPlaylist extends RecyclerView.ViewHolder {
        private TextView textViewNombre;
        private ImageView imageView;

        public ViewHolderPlaylist(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.title_fragment_album);
            imageView = itemView.findViewById(R.id.imagen_fragment_album);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicionDePlaylistCliqueado = getAdapterPosition();
                    notificadorPlaylistCelda.notificarCeldaCliqueadaPlaylist(
                            listaPlaylist, posicionDePlaylistCliqueado);
                }
            });
        }

        public void armarCelda(Playlist playlist) {
            textViewNombre.setText(playlist.getNombre());
            Picasso.get()
                    .load(playlist.getImagenPlaylistUrl())
                    .placeholder(R.drawable.placeholder)
                    .into(imageView);
        }
    }

    private class ViewHolderPlaylistUser extends RecyclerView.ViewHolder {
        private TextView nombrePlaylist;
        private TextView numeroCanciones;
        private ImageView imagenPreview;

        public ViewHolderPlaylistUser(View itemView) {
            super(itemView);
            nombrePlaylist = itemView.findViewById(R.id.nombre_canciones_playlist_user);
            numeroCanciones = itemView.findViewById(R.id.numero_canciones_playlist_user);
            imagenPreview = itemView.findViewById(R.id.id_preview_playlist_user);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posicionDePlaylistCliqueado = getAdapterPosition();
                    Playlist playlist = listaPlaylist.get(posicionDePlaylistCliqueado);
                    notificadorPlaylistUser.notificarPlaylistUser(playlist);

                }
            });
        }

        public void armarCeldaPU(Playlist playlist) {
            nombrePlaylist.setText(playlist.getNombre());
            if(playlist.getListCanciones() !=null){   Integer numCanciones = playlist.getListCanciones().size();
                numeroCanciones.setText(numCanciones.toString() + " canciones.");}

            if(playlist.getImagenPlaylistUrl() != null){
                Picasso.get()
                        .load(playlist.getImagenPlaylistUrl())
                        .placeholder(R.drawable.placeholder)
                        .into(imagenPreview);
            }
        }
    }

    public interface NotificadorPlaylistCelda {
        public void notificarCeldaCliqueadaPlaylist(List<Playlist> playlists, int posicion);
    }

    public interface NotificadorPlaylistUser{
        public void notificarPlaylistUser(Playlist playlist);
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
