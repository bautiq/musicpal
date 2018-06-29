package com.example.user.musicpal.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class FragmentDetalleAlbumPagerAdapter extends FragmentStatePagerAdapter {
    private List<FragmentDetalleAlbum> listafragmentDetalleAlbum;

    public FragmentDetalleAlbumPagerAdapter(FragmentManager fm, List<FragmentDetalleAlbum> listafragmentDetalleAlbum) {
        super(fm);
        this.listafragmentDetalleAlbum = listafragmentDetalleAlbum;
    }

    @Override
    public Fragment getItem(int position) {
        return listafragmentDetalleAlbum.get(position);
    }

    @Override
    public int getCount() {
        return listafragmentDetalleAlbum.size();
    }


}
