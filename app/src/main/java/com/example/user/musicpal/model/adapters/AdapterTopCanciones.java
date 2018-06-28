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
import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.model.pojo.Cancion;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterTopCanciones extends RecyclerView.Adapter {
    private NotificadorAdapterTopCanciones notificadorAdapterTopCanciones;
    private List<Cancion> listaDeCanciones;
    private Context context;

    public AdapterTopCanciones(NotificadorAdapterTopCanciones notificadorAdapterTopCanciones, List<Cancion> listaDeCanciones, Context context) {
        this.notificadorAdapterTopCanciones = notificadorAdapterTopCanciones;
        this.listaDeCanciones = listaDeCanciones;
        this.context = context;
    }

    public void setListaDeCanciones(List<Cancion> listaDeCanciones) {
        for (Cancion cancionAgregar : listaDeCanciones) {
            if (!this.listaDeCanciones.contains(cancionAgregar)) {
                this.listaDeCanciones.add(cancionAgregar);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_recycler_scroll, parent, false);
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

    public class ViewHolderCancion extends RecyclerView.ViewHolder {
        private ImageView imagen;
        private TextView textNombre;
        private TextView textArtista;

        public ViewHolderCancion(View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imagen_fragment_album);
            textNombre = itemView.findViewById(R.id.title_fragment_album);
            textArtista = itemView.findViewById(R.id.title_fragment_artista);
        }

        public void armarCeldaCancion(Cancion cancion) {
            Picasso.with(context).load(cancion.getArtista().getImagenUrl()).placeholder(R.drawable.placeholder).into(imagen);
            textNombre.setText(cancion.getTitle());
            textArtista.setText(cancion.getArtista().getNombre());
        }
    }

    public interface NotificadorAdapterTopCanciones {
        public void notificar(Cancion cancion);
    }
}
