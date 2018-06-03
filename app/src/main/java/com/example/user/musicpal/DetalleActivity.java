package com.example.user.musicpal;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetalleActivity extends AppCompatActivity {

private FragmentDetalle fragmentDetalle;
private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        fragmentDetalle = new FragmentDetalle();
        fragmentDetalle.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        FragmentHelper.cargarFragmemt(fragmentDetalle, R.id.contenedor_fragment_vista_previa, fragmentManager);
    }

}
