package com.example.user.musicpal.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class FragDetalleArtistaPAdapter extends FragmentStatePagerAdapter {
    private List<FragmentDetalleArtista> listafragmentDetalleArtistas;

    public FragDetalleArtistaPAdapter(FragmentManager fm, List<FragmentDetalleArtista> listafragmentDetalleArtistas) {
        super(fm);
        this.listafragmentDetalleArtistas = listafragmentDetalleArtistas;
    }

    @Override
    public Fragment getItem(int position) {
        return listafragmentDetalleArtistas.get(position);
    }

    @Override
    public int getCount() {
        return listafragmentDetalleArtistas.size();
    }


}