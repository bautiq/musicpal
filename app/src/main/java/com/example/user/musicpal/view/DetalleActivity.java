package com.example.user.musicpal.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.musicpal.FragmentHelper;
import com.example.user.musicpal.R;
import com.example.user.musicpal.model.pojo.Album;

import java.util.ArrayList;
import java.util.List;

public class DetalleActivity extends AppCompatActivity {

    public static final String POSICION_KEY = "clave_posicion";
    public static final String ID_RECYCLER_CLICKEADO = "clave_recycler_id";
    public static final String ALBUM_KEY = "clave_album";
    private FragmentDetalle fragmentDetalle;
    private FragmentManager fragmentManager;
    //esta lista contiene los fragments que se van a mostrar en el viewpager
    private List<FragmentDetalle> listaFragments;
    private Album albumRecibido;
    private int recyclerIdRecibido;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        viewPager = findViewById(R.id.viewPager_id);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        albumRecibido = (Album) bundle.getSerializable(ALBUM_KEY);
        recyclerIdRecibido = bundle.getInt(ID_RECYCLER_CLICKEADO);
        crearListaFragments(recyclerIdRecibido);
        FragmentDetallePagerAdapter detallePagerAdapter = new FragmentDetallePagerAdapter(getSupportFragmentManager(), listaFragments);
        viewPager.setAdapter(detallePagerAdapter);
        viewPager.setCurrentItem(recyclerIdRecibido);

        /* Este codigo es para que cargue solo un fragment
         fragmentDetalle = new FragmentDetalle();
        fragmentDetalle.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        FragmentHelper.cargarFragment(fragmentDetalle, R.id.contenedor_fragment_vista_previa, fragmentManager); */
    }

    public void crearListaFragments(int idRecycler) {
        listaFragments = new ArrayList<>();
        switch (idRecycler){
            case R.id.recycler_clasicos_id:
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                break;
            case R.id.recycler_populares_id:
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                break;
            case R.id.recycler_recomendaciones_id:
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                break;
            case R.id.recycler_top_id:
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                listaFragments.add(FragmentDetalle.dameUnFragment());
                break;
        }
        listaFragments.add(FragmentDetalle.dameUnFragment(albumRecibido));
    }

}
