package com.example.user.musicpal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter {
    private List<Album> albumLista;

    public AlbumAdapter(List<Album> albumLista) {
        this.albumLista = albumLista;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_recycler_recomendaciones, parent, false);
        ViewHolderRecomendaciones viewHolderRecomendaciones = new ViewHolderRecomendaciones(view);
        return viewHolderRecomendaciones;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Album album = albumLista.get(position);
        ViewHolderRecomendaciones viewHolderRecomendaciones= (ViewHolderRecomendaciones) holder;
        viewHolderRecomendaciones.cargarAlbum(album);
    }

    @Override
    public int getItemCount() {
        if (albumLista != null) {
            return albumLista.size();
        } else {
            return 0;
        }
    }

    public class ViewHolderRecomendaciones extends RecyclerView.ViewHolder {

        private TextView textViewNombreAlbum;
        private TextView textViewNombreArtista;
        private ImageView imagenAlbum;
        private LinearLayout container;

        public ViewHolderRecomendaciones(View itemView) {
            super(itemView);
            textViewNombreAlbum = itemView.findViewById(R.id.titulo_fragment_album);
            textViewNombreArtista = itemView.findViewById(R.id.titulo_fragment_artista);
            imagenAlbum = itemView.findViewById(R.id.imagen_fragment_album);
            container = itemView.findViewById(R.id.linear_container);
        }

        public void cargarAlbum(Album album) {
            textViewNombreAlbum.setText(album.getTitulo());
            textViewNombreArtista.setText(album.getArtista().getNombre());
            imagenAlbum.setImageResource(album.getImagenAlbum());
        }
    }

    ;
}
