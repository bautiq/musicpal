package com.example.user.musicpal.view;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.musicpal.R;
import com.example.user.musicpal.utils.FragmentHelper;

public class FragmentReproductorChico extends Fragment {

    private Button botonRewind;
    private Button botonForward;
    private Button botonPlay;
    private LinearLayout linearLayout;
    private LinearLayout linearLayoutReproductor;
    private FragmentReproductor fragmentReproductor;
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reproductor_chico, container, false);

        linearLayout = view.findViewById(R.id.linear_layout_reproductor_chico);
        botonPlay = view.findViewById(R.id.boton_play);
        botonForward = view.findViewById(R.id.boton_forward);
        botonRewind = view.findViewById(R.id.boton_rewind);
        fragmentManager = getActivity().getSupportFragmentManager();

        final MediaPlayer mP = MediaPlayer.create(getContext(), R.raw.bitter_sweet_symphony);
        botonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mP.isPlaying()) {
                    mP.pause();
                    botonPlay.setBackgroundResource(R.drawable.ic_play_circle);
                } else {
                    mP.start();
                    botonPlay.setBackgroundResource(R.drawable.ic_pause_circle_outline);
                }
            }
        });

        botonRewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Click Rewind", Toast.LENGTH_SHORT).show();
            }
        });

        botonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Click Forward", Toast.LENGTH_SHORT).show();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentReproductor = new FragmentReproductor();
                FragmentHelper.cargarFragment(fragmentReproductor, R.id.viewPager_id, fragmentManager);
            }
        });
        return view;
    }

}
