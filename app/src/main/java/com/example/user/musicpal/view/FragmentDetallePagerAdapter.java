package com.example.user.musicpal.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class FragmentDetallePagerAdapter extends FragmentStatePagerAdapter {
private List<FragmentDetalle> listafragmentDetalle;

    public FragmentDetallePagerAdapter(FragmentManager fm, List<FragmentDetalle> listafragmentDetalle) {
        super(fm);
        this.listafragmentDetalle = listafragmentDetalle;
    }

    @Override
    public Fragment getItem(int position) {
        return listafragmentDetalle.get(position);
    }

    @Override
    public int getCount() {
        return listafragmentDetalle.size();
    }


}
