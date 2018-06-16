package com.example.user.musicpal.model.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter {

    private List<Album> albumLista;
    private NotificadorAlbumCelda notificadorAlbumCelda;
    private String categoria;
    private Context context;

    public AlbumAdapter(List<Album> albumLista, NotificadorAlbumCelda notificadorAlbumCelda, String categoria, Context context) {
        this.albumLista = albumLista;
        this.notificadorAlbumCelda = notificadorAlbumCelda;
        this.categoria = categoria;
        this.context = context;
    }

    public void setAlbumLista(List<Album> albumLista) {
        this.albumLista = albumLista;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_recycler_scroll, parent, false);
        ViewHolderAlbum viewHolderAlbum = new ViewHolderAlbum(view);
        return viewHolderAlbum;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Album album = albumLista.get(position);
        ViewHolderAlbum viewHolderAlbum = (ViewHolderAlbum) holder;
        viewHolderAlbum.cargarAlbum(album);
    }

    @Override
    public int getItemCount() {
        if (albumLista != null) {
            return albumLista.size();
        } else {
            return 0;
        }
    }

    public void agregarAlbunes(List<Album> albumLista) {

        for (Album albumAAgregar : albumLista) {
            if (!this.albumLista.contains(albumAAgregar)) {
                this.albumLista.add(albumAAgregar);
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolderAlbum extends RecyclerView.ViewHolder {

        private TextView textViewNombreAlbum;
        private TextView textViewNombreArtista;
        private ImageView imagenAlbum;

        public ViewHolderAlbum(final View itemView) {
            super(itemView);
            textViewNombreAlbum = itemView.findViewById(R.id.titulo_fragment_album);
            textViewNombreArtista = itemView.findViewById(R.id.titulo_fragment_artista);
            imagenAlbum = itemView.findViewById(R.id.imagen_fragment_album);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicionAlbumClickeado = getAdapterPosition();
                    notificadorAlbumCelda.notificarCeldaClickeada(albumLista, posicionAlbumClickeado, categoria);
                }
            });
        }

        public void cargarAlbum(Album album) {
            Picasso.with(context).load(album.getUrl()).into(imagenAlbum);
            textViewNombreAlbum.setText(album.getTitulo());
            textViewNombreArtista.setText(album.getArtista().getNombre());

        }
    }

    public interface NotificadorAlbumCelda {
        public void notificarCeldaClickeada(List<Album> listaAlbums, int posicion, String categoria);
    }
}
