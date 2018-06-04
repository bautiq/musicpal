package com.example.user.musicpal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPlaylist extends Fragment {
private TextView textAgregar;
private ImageView buttonAgregar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_playlist, container, false);
        textAgregar = view.findViewById(R.id.text_agregar_playlist);
        buttonAgregar = view.findViewById(R.id.button_agregar_playlist);
        textAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Click agregar", Toast.LENGTH_SHORT).show();
            }
        });
        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Click agregar", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
