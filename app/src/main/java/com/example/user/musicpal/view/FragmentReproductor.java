package com.example.user.musicpal.view;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.musicpal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentReproductor extends Fragment {
    private TextView textViewPlayFrom;
    private TextView textViewPlayType;
    private ImageView imagen;
    private ImageView buttonPlayPausa;
    private ImageView buttonForward;
    private ImageView buttonBack;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reproductor, container, false);
        textViewPlayFrom = view.findViewById(R.id.text_reproductor_playfrom);
        textViewPlayType = view.findViewById(R.id.text_reproductor_playtype);
        imagen = view.findViewById(R.id.imagen_reproductor);
        buttonPlayPausa = view.findViewById(R.id.button_play_reproductorGrande);
        buttonForward = view.findViewById(R.id.button_forward_reproductorGrande);
        buttonBack = view.findViewById(R.id.button_back_reproductorGrande);
        return view;
    }

}
