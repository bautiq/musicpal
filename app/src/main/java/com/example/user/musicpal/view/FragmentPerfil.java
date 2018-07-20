package com.example.user.musicpal.view;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.musicpal.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPerfil extends Fragment {

    private TextView nombreUsuario;
    private Button botonCerrarSesion;
    private ImageView imagenPerfil;
    private FirebaseAuth firebaseAuth;
    private Intent intent;
    private Button botonIniciarSesion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        nombreUsuario = view.findViewById(R.id.nombre_usuario);
        nombreUsuario.setSelected(true);
        imagenPerfil = view.findViewById(R.id.imagen_perfil);
        botonCerrarSesion = view.findViewById(R.id.cerrar_sesion);
        botonIniciarSesion = view.findViewById(R.id.iniciar_sesion);
        firebaseAuth = FirebaseAuth.getInstance();
        intent = new Intent(getContext(), LoginActivity.class);
        chequearSiEstaLogueado();

        botonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    if (AccessToken.getCurrentAccessToken() != null) {
                        LoginManager.getInstance().logOut();
                    }
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(getActivity(),
                            "¡Cerró Sesión Correctamente!",
                            Toast.LENGTH_SHORT).show();

                    botonCerrarSesion.setVisibility(view.INVISIBLE);
                    nombreUsuario.setText("Usuario Desconocido");
                    imagenPerfil.setImageResource(R.drawable.perfil);
                    botonIniciarSesion.setVisibility(View.VISIBLE);
                }
            }
        });
        cargarDatosDelUsuario();
        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        return view;
    }

    public void chequearSiEstaLogueado() {
        if (firebaseAuth.getCurrentUser() == null) {
            startActivity(intent);

        } else {
            botonCerrarSesion.setVisibility(View.VISIBLE);
            botonIniciarSesion.setVisibility(View.INVISIBLE);
        }
    }

    private void cargarDatosDelUsuario() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            Picasso.get().load(photoUrl).placeholder(R.drawable.perfil).into(imagenPerfil);
            if (name != null) {
                nombreUsuario.setText(name);
            } else if (email != null) {
                nombreUsuario.setText(email);
            }
        }
    }
}
