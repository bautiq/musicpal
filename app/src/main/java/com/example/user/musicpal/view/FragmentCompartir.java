package com.example.user.musicpal.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.musicpal.R;
import com.example.user.musicpal.model.adapters.RedSocialAdapter;
import com.example.user.musicpal.model.pojo.RedSocial;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCompartir extends Fragment {

    private RecyclerView recyclerViewRedSocial;
    private RedSocialAdapter redSocialAdapter;
    private List<RedSocial> redesSociales;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_compartir, container, false);
        recyclerViewRedSocial = view.findViewById(R.id.recycler_red_social_id);

        armarListadoRedes();

        redSocialAdapter = new RedSocialAdapter(redesSociales);
        recyclerViewRedSocial.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        recyclerViewRedSocial.setHasFixedSize(true);
        recyclerViewRedSocial.setAdapter(redSocialAdapter);

        return view;
    }

    private void armarListadoRedes() {
        redesSociales = new ArrayList<>();
        redesSociales.add(new RedSocial("Facebook", R.drawable.logof));
        redesSociales.add(new RedSocial("Instagram", R.drawable.logoinst));
        redesSociales.add(new RedSocial("Twitter", R.drawable.logotwi));
        redesSociales.add(new RedSocial("WhatsApp", R.drawable.logowhat));
        redesSociales.add(new RedSocial("Snapchat", R.drawable.logosnap));
        redesSociales.add(new RedSocial("VK",R.drawable.logovk));
    }

}
