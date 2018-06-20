package com.example.user.musicpal.model.adapters;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.musicpal.R;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.utils.FragmentHelper;
import com.example.user.musicpal.view.FragmentDetalle;
import com.example.user.musicpal.view.FragmentReproductor;

import java.util.List;

public class CancionesAdapter extends RecyclerView.Adapter {

    private List<Cancion> listaDeCanciones;
    private FragmentManager fragmentManager;
    private Cancion cancion;

    public CancionesAdapter(List<Cancion> listaDeCanciones, FragmentManager fragmentManager) {
        this.listaDeCanciones = listaDeCanciones;
        this.fragmentManager = fragmentManager;
    }

    public void setListaDeCanciones(List<Cancion> listaDeCanciones) {
        this.listaDeCanciones = listaDeCanciones;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_recycler_cancion, parent, false);
        ViewHolderCancion viewHolderCancion = new ViewHolderCancion(view);
        return viewHolderCancion;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        cancion = listaDeCanciones.get(position);
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

        public ViewHolderCancion(final View itemView) {
            super(itemView);
            this.textViewNombre = itemView.findViewById(R.id.nombre_canciones_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentReproductor fragmentReproductor = new FragmentReproductor();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(cancion, FragmentReproductor.CANCION_KEY);
                    FragmentHelper.cargarFragment(fragmentReproductor, R.id.drawer_layout, fragmentManager);
                }
            });
        }

        public void cargarCancion(Cancion cancion) {
            textViewNombre.setText(cancion.getTitle());
        }
    }

}
