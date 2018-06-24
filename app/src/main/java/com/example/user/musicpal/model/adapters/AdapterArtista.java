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
import com.example.user.musicpal.model.pojo.Artista;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterArtista extends RecyclerView.Adapter {
    private Context context;
    private List<Artista> artistaList;
    private NotificadorArtistaAdapter notificadorArtistaAdapter;

    public AdapterArtista(Context context, NotificadorArtistaAdapter notificadorArtistaAdapter) {
        this.context = context;
        this.artistaList = artistaList;
        this.notificadorArtistaAdapter = notificadorArtistaAdapter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.celda_recycler_scroll, parent, false);
        ViewHolderArtista viewHolderArtista = new ViewHolderArtista(view);
        return viewHolderArtista;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Artista artista = artistaList.get(position);
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

    private class ViewHolderArtista extends RecyclerView.ViewHolder {
        private TextView textViewNombre;
        private ImageView imagen;

        public ViewHolderArtista(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.title_fragment_album);
        }

        public void armarCelda(Artista artista) {
            textViewNombre.setText(artista.getNombre());
            Picasso.with(context).load(artista.getImagenUrl()).into(imagen);
        }

    }

    public interface NotificadorArtistaAdapter {
        public void notificar(Artista artista);
    }
}
