package com.example.user.musicpal;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivitySecundariaPaginaPrincipal extends AppCompatActivity {
private Bundle bundle;
    public static final String CLAVE_ID = "clave_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria_pagina_principal);
        Intent intent = getIntent();
        bundle = intent.getExtras();
        cargarFragment(fragmentClickImagenUno);
    }
    final FragmentClickEnPaginaPrincipal fragmentClickImagenUno = FragmentClickEnPaginaPrincipal.fabricaClickPrincipal("Linkin Park", "Meteora", "2003", bundle.getInt(CLAVE_ID));

    public void cargarFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contenedor_fragment_vista_previa, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
