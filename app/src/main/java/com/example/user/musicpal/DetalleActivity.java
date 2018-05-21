package com.example.user.musicpal;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetalleActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        DetalleFragment fragmentDetalle = new DetalleFragment();
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
