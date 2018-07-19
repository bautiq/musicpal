package com.example.user.musicpal.view;


import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.facebook.Profile;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        nombreUsuario = view.findViewById(R.id.nombre_usuario);
        nombreUsuario.setSelected(true);
        imagenPerfil = view.findViewById(R.id.imagen_perfil);
        botonCerrarSesion = view.findViewById(R.id.cerrar_sesion);

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
                }
            }
        });
        cargarFotoDelUsuario();
        return view;
    }

    private void cargarFotoDelUsuario() {
      /*  if (Profile.getCurrentProfile() != null) {
            Uri photoUri = Profile.getCurrentProfile().getProfilePictureUri(200, 200);
            String nombre= Profile.getCurrentProfile().getName();
            nombreUsuario.setText(nombre);
            Picasso.get().load(photoUri).into(imagenPerfil);
        }*/

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            nombreUsuario.setText(email);
            Picasso.get().load(photoUrl).placeholder(R.drawable.perfil).into(imagenPerfil);
        }
    }
}
