package com.example.user.musicpal.view;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.musicpal.R;
import com.example.user.musicpal.model.adapters.AdapterPlaylist;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.model.pojo.Playlist;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPlaylist
        extends Fragment
        implements AdapterPlaylist.NotificadorPlaylistCelda {

    private TextView textAgregar;
    private ImageView buttonAgregar;
    private RecyclerView recyclerView;
    private FirebaseAuth firebaseAuth;
    private Intent intent;
    private AdapterPlaylist adapterPlaylist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        recyclerView = view.findViewById(R.id.recycler_fragment_playlist);
        adapterPlaylist = new AdapterPlaylist(getContext(), this, "user");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapterPlaylist);
        textAgregar = view.findViewById(R.id.text_agregar_playlist);
        buttonAgregar = view.findViewById(R.id.button_agregar_playlist);
        FirebaseApp.initializeApp(getContext());
        firebaseAuth = FirebaseAuth.getInstance();
        intent = new Intent(getContext(), LoginActivity.class);
        textAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chequearSiEstaLogueado();
            }
        });
        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chequearSiEstaLogueado();
            }
        });
        return view;
    }

    private void abrirVentanaAgregar() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ventana_nueva_playlist, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setView(view);
        final EditText editText = view.findViewById(R.id.edit_nueva_playlist);
        alertDialog.setCancelable(true);
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //nada
            }
        });
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Playlist playlistNueva = new Playlist(editText.getText().toString(), new ArrayList<Cancion>(), "1");
                adapterPlaylist.agregarPlaylist(playlistNueva);
            }
        });
        alertDialog.show();
    }


    public void chequearSiEstaLogueado() {
        if (firebaseAuth.getCurrentUser() == null) {
            startActivity(intent);

        } else {
            abrirVentanaAgregar();
        }
    }

    @Override
    public void notificarCeldaCliqueadaPlaylist(List<Playlist> playlists, int posicion) {

    }
}
