package com.example.user.musicpal;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetalleActivity extends AppCompatActivity {

private DetalleFragment detalleFragment;
private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        detalleFragment = new DetalleFragment();
        detalleFragment.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        FragmentHelper.cargarFragmemt(detalleFragment, R.id.contenedor_fragment_vista_previa, fragmentManager);
    }

}
