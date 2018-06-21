package com.example.user.musicpal.model.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.musicpal.R;
import com.example.user.musicpal.model.pojo.Cancion;

import java.util.List;

public class CancionesAdapter extends RecyclerView.Adapter {

    private List<Cancion> listaDeCanciones;
    private FragmentManager fragmentManager;
    private Cancion cancion;
    private NotificadorCancionCelda notificadorCancionCelda;

    public CancionesAdapter(List<Cancion> listaDeCanciones, FragmentManager fragmentManager, NotificadorCancionCelda notificadorCancionCelda) {
        this.listaDeCanciones = listaDeCanciones;
        this.fragmentManager = fragmentManager;
        this.notificadorCancionCelda = notificadorCancionCelda;
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
                    Cancion cancionAreproducir = listaDeCanciones.get(getAdapterPosition());
                    notificadorCancionCelda.notificarCeldaClikeada(cancionAreproducir);
                }
            });
        }

        public void cargarCancion(Cancion cancion) {
            textViewNombre.setText(cancion.getTitle());
        }
    }

    public interface NotificadorCancionCelda {
        public void notificarCeldaClikeada(Cancion cancion);
    }
}
