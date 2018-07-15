package com.example.user.musicpal.view;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.musicpal.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPlaylist extends Fragment {
private TextView textAgregar;
private ImageView buttonAgregar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_playlist, container, false);
        textAgregar = view.findViewById(R.id.text_agregar_playlist);
        buttonAgregar = view.findViewById(R.id.button_agregar_playlist);
        textAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirVentanaAgregar();
            }
        });
        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirVentanaAgregar();
            }
        });
        return view;
    }

    private void abrirVentanaAgregar() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ventana_nueva_playlist, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setView(view);
        EditText editText = view.findViewById(R.id.edit_nueva_playlist);
        alertDialog.setCancelable(true);
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

}
