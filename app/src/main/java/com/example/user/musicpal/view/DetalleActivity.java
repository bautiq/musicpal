package com.example.user.musicpal.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.musicpal.FragmentHelper;
import com.example.user.musicpal.R;

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
        FragmentHelper.cargarFragment(fragmentDetalle, R.id.contenedor_fragment_vista_previa, fragmentManager);
    }

}
