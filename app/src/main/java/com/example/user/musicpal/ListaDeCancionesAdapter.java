package com.example.user.musicpal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ListaDeCancionesAdapter extends RecyclerView.Adapter {

    private List<Cancion> listaDeCanciones;


    public ListaDeCancionesAdapter(List<Cancion> listaDeCanciones) {
        this.listaDeCanciones = listaDeCanciones;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_recycler_cancion, parent, false);
        ViewHolderCancion viewHolderCancion = new ViewHolderCancion(view);
        return viewHolderCancion;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Cancion cancion = listaDeCanciones.get(position);
        ViewHolderCancion viewHolderCancion = (ViewHolderCancion) holder;
        viewHolderCancion.cargarCancion(cancion);
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

        private TextView textViewNombre;
        private LinearLayout container;

        public ViewHolderCancion(View itemView) {
            super(itemView);
            this.textViewNombre = itemView.findViewById(R.id.nombre_canciones_id);
            this.container=itemView.findViewById(R.id.linear_container_cancion);
        }

        public void cargarCancion(Cancion cancion) {
            this.textViewNombre.setText(cancion.getTitulo());
        }
    }

}
