package com.example.user.musicpal.model.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.ControllerGlobal;
import com.example.user.musicpal.controller.MediaPlayerGlobal;
import com.example.user.musicpal.model.pojo.Cancion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdapterCanciones extends RecyclerView.Adapter {

    private List<Cancion> listaDeCanciones;
    private FragmentManager fragmentManager;
    private Cancion cancion;
    private NotificadorCancionCelda notificadorCancionCelda;
    private Context context;
    private boolean esDeFavoritos;

    public AdapterCanciones(List<Cancion> listaDeCanciones, FragmentManager fragmentManager,
                            NotificadorCancionCelda notificadorCancionCelda, Context context, boolean esDeFavoritos) {
        this.context = context;
        this.listaDeCanciones = listaDeCanciones;
        this.fragmentManager = fragmentManager;
        this.notificadorCancionCelda = notificadorCancionCelda;
        this.esDeFavoritos = esDeFavoritos;

    }

    public AdapterCanciones(FragmentManager fragmentManager, NotificadorCancionCelda notificadorCancionCelda, Context context, boolean esDeFavoritos) {
        listaDeCanciones = new ArrayList<>();
        this.fragmentManager = fragmentManager;
        this.notificadorCancionCelda = notificadorCancionCelda;
        this.context = context;
        this.esDeFavoritos = esDeFavoritos;
    }

    public List<Cancion> getListaDeCanciones() {
        return listaDeCanciones;
    }

    public void setListaDeCanciones(List<Cancion> listaDeCanciones) {
        this.listaDeCanciones = listaDeCanciones;
        notifyDataSetChanged();
    }

    public void agregarPlaylist(Cancion cancionNueva) {
        if (listaDeCanciones.contains(cancionNueva)) {
            return;
        }
        listaDeCanciones.add(cancionNueva);
        notifyItemChanged(listaDeCanciones.size() - 1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_recycler_cancion, parent, false);
        if (esDeFavoritos) {
            ViewHolderFavoritos viewHolderFavoritos = new ViewHolderFavoritos(view);
            return viewHolderFavoritos;
        } else {
            ViewHolderCancion viewHolderCancion = new ViewHolderCancion(view);
            return viewHolderCancion;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        cancion = listaDeCanciones.get(position);
        if (esDeFavoritos) {
            ViewHolderFavoritos viewHolderFavoritos = (ViewHolderFavoritos) holder;
            viewHolderFavoritos.cargarCancion(cancion);
        } else {
            ViewHolderCancion viewHolderCancion = (ViewHolderCancion) holder;
            viewHolderCancion.cargarCancion(cancion);
        }
    }

    @Override
    public int getItemCount() {
        if (listaDeCanciones != null) {
            return listaDeCanciones.size();
        } else {
            return 0;
        }
    }

    private class ViewHolderFavoritos extends RecyclerView.ViewHolder {
        private TextView textViewNombre;
        private ImageView imagenPlay;

        public ViewHolderFavoritos(View itemView) {
            super(itemView);
            this.textViewNombre = itemView.findViewById(R.id.nombre_canciones_id);
            this.imagenPlay = itemView.findViewById(R.id.play_chico);
            textViewNombre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cancion cancionAreproducir = listaDeCanciones.get(getAdapterPosition());
                    try {
                        MediaPlayerGlobal mediaPlayerGlobal = MediaPlayerGlobal.getInstance();
                        mediaPlayerGlobal.setearPlaylist(listaDeCanciones, true, getAdapterPosition());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    notificadorCancionCelda.notificarCeldaClikeada(cancionAreproducir);
                }
            });
            imagenPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cancion cancionAreproducir = listaDeCanciones.get(getAdapterPosition());
                    try {
                        MediaPlayerGlobal mediaPlayerGlobal = MediaPlayerGlobal.getInstance();
                        mediaPlayerGlobal.setearPlaylist(listaDeCanciones, true, getAdapterPosition());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    notificadorCancionCelda.notificarCeldaClikeada(cancionAreproducir);
                }
            });
        }

        public void cargarCancion(Cancion cancion) {
            textViewNombre.setText(cancion.getTitle());
        }
    }

    private class ViewHolderCancion extends RecyclerView.ViewHolder implements AdapterView.OnItemSelectedListener {
        private ControllerGlobal controllerGlobal;
        private TextView textViewNombre;
        private ImageView imagenPlay;
        private Spinner spinner;
        private List<String> listaRecibida;

        public ViewHolderCancion(final View itemView) {
            super(itemView);
            listaRecibida = new ArrayList<>();
            this.textViewNombre = itemView.findViewById(R.id.nombre_canciones_id);
            this.imagenPlay = itemView.findViewById(R.id.play_chico);
            this.spinner = itemView.findViewById(R.id.id_spinner);
            controllerGlobal = new ControllerGlobal(context);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                    R.array.drop_spinner_menu, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);

            textViewNombre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cancion cancionAreproducir = listaDeCanciones.get(getAdapterPosition());
                    try {
                        MediaPlayerGlobal mediaPlayerGlobal = MediaPlayerGlobal.getInstance();
                        mediaPlayerGlobal.setearPlaylist(listaDeCanciones, true, getAdapterPosition());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    notificadorCancionCelda.notificarCeldaClikeada(cancionAreproducir);
                }
            });
            imagenPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cancion cancionAreproducir = listaDeCanciones.get(getAdapterPosition());
                    try {
                        MediaPlayerGlobal mediaPlayerGlobal = MediaPlayerGlobal.getInstance();
                        mediaPlayerGlobal.setearPlaylist(listaDeCanciones, true, getAdapterPosition());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    notificadorCancionCelda.notificarCeldaClikeada(cancionAreproducir);
                }
            });

        }

        public void cargarCancion(Cancion cancion) {
            textViewNombre.setText(cancion.getTitle());
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //aca va la logica de agregar a la playlist
            if (adapterView.getItemAtPosition(i).equals("Agregar a favoritos")) {
               // agregarAFB();
            } else {
                //aca va la logica de compartir
            }
        }

        private void agregarAFB() {
            Cancion cancion = listaDeCanciones.get(getAdapterPosition());
            controllerGlobal.pushearCancion(cancion);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            //no hacer nada
        }
    }

    public interface NotificadorCancionCelda {
        public void notificarCeldaClikeada(Cancion cancion);
    }
}
