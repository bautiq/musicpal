package com.example.user.musicpal.model.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.musicpal.R;
import com.example.user.musicpal.model.pojo.Playlist;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter {
    private List<Playlist> listaPlaylist;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_recycler_playlist, parent, false);
        ViewHolderPlaylist viewHolderPlaylist = new ViewHolderPlaylist(view);
        return viewHolderPlaylist;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Playlist playlist = listaPlaylist.get(position);
        ViewHolderPlaylist viewHolderPlaylist = (ViewHolderPlaylist) holder;
        viewHolderPlaylist.bindPlaylist(playlist);
    }

    @Override
    public int getItemCount() {
        if (listaPlaylist != null) {
            return listaPlaylist.size();
        } else {
            return 0;
        }
    }

    public class ViewHolderPlaylist extends RecyclerView.ViewHolder {
        private TextView textViewNombre;

        public ViewHolderPlaylist(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.id_nombre_playlist);
        }

        public void bindPlaylist(Playlist playlist) {
            textViewNombre.setText(playlist.getNombre());
        }
    }
}
