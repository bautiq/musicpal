package com.example.user.musicpal.model.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.musicpal.R;
import com.example.user.musicpal.model.pojo.RedSocial;

import java.util.List;

public class AdapterRedSocial extends RecyclerView.Adapter {

    private List<RedSocial> redesSociales;

    public AdapterRedSocial(List<RedSocial> redesSociales) {
        this.redesSociales = redesSociales;
    }

    public void setRedesSociales(List<RedSocial> redesSociales) {
        this.redesSociales = redesSociales;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_recycler_red_social, parent, false);
        AdapterRedSocial.ViewHolderRedSocial viewHolderRedSocial = new ViewHolderRedSocial(view);
        return viewHolderRedSocial;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RedSocial redSocial = redesSociales.get(position);
        ViewHolderRedSocial viewHolderRedSocial = (ViewHolderRedSocial) holder;
        viewHolderRedSocial.cargarRedSocial(redSocial);
    }

    @Override
    public int getItemCount() {
        if (redesSociales != null) {
            return redesSociales.size();
        } else {
            return 0;
        }
    }

    public class ViewHolderRedSocial extends RecyclerView.ViewHolder {

        private TextView textViewNombre;
        private ImageView imagenRed;

        public ViewHolderRedSocial(View itemView) {
            super(itemView);
            this.textViewNombre = itemView.findViewById(R.id.nombre_red_social_id);
            this.imagenRed = itemView.findViewById(R.id.imagen_red_social_id);

        }

        public void cargarRedSocial(RedSocial redSocial) {
            textViewNombre.setText(redSocial.getNombre());
            imagenRed.setImageResource(redSocial.getImagenRedSocial());
        }
    }
}
