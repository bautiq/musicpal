package com.example.user.musicpal;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivitySecundariaPaginaPrincipal extends AppCompatActivity {
    public static final String CLAVE_ARTISTA = "clave_artista";
    public static final String CLAVE_ALBUM = "clave_album";
    public static final String CLAVE_ANIO = "clave_anio";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria_pagina_principal);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        FragmentClickEnPaginaPrincipal fragmentDetalle = new FragmentClickEnPaginaPrincipal();
        fragmentDetalle.setArguments(bundle);
        cargarFragment(fragmentDetalle);
    }


    public void cargarFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contenedor_fragment_vista_previa, fragment);
        fragmentTransaction.commit();

    }
}
