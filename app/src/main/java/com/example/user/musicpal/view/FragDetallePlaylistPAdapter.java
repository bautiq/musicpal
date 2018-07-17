package com.example.user.musicpal.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class FragDetallePlaylistPAdapter extends FragmentStatePagerAdapter {

    private List<FragmentDetallePlaylist> listaFragmentDetallePlaylist;

    public FragDetallePlaylistPAdapter(FragmentManager fm, List<FragmentDetallePlaylist> listaFragmentDetallePlaylist) {
        super(fm);
        this.listaFragmentDetallePlaylist = listaFragmentDetallePlaylist;
    }

    @Override
    public Fragment getItem(int position) {
        return listaFragmentDetallePlaylist.get(position);
    }

    @Override
    public int getCount() {
        return listaFragmentDetallePlaylist.size();
    }
}
