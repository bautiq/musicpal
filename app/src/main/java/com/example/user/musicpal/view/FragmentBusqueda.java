package com.example.user.musicpal.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.musicpal.R;
import com.example.user.musicpal.controller.ControllerGlobal;
import com.example.user.musicpal.model.adapters.AdapterFragmentBusqueda;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.utils.ResultListener;
import com.example.user.musicpal.utils.SimpleDividerItemDecoration;

import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */

//LA BUSQUEDA DEVUELVE UNA LISTA DE CANCIONES (QUE CONTIENEN NOMBRE DE CANCION, DE ARTISTA Y DE ALBUM, Y SUS RESPECTIVAS IMAGENES

public class FragmentBusqueda extends Fragment implements AdapterFragmentBusqueda.NotificadorSEARCHCancionesCelda {

    private NotificadorAActivityInicioDesdeFragmentBusqueda notificadorAActivityInicioDesdeFragmentBusqueda;

    private EditText editTextBusqueda;

    private ImageView imagenLupaSearch;
    private TextView textArtista;
    private TextView textAlbum;

    private RecyclerView recyclerViewBusqueda;
    private LinearLayoutManager linearLayoutManagerBusqueda;
    private AdapterFragmentBusqueda adapterBusqueda;
    private List<Cancion> listaCancionesBusqueda;

    private ControllerGlobal controllerGlobalBusqueda;
    private FragmentDetalleAlbum.NotificadorCancion notificadorCancion;

    private Boolean isLoading;
    private static final int CANTIDAD_ELEMENTOS_PARA_NUEVO_PEDIDO = 3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this frag ment
        View view = inflater.inflate(R.layout.fragment_busqueda, container, false);
        editTextBusqueda = view.findViewById(R.id.edit_text_busqueda);
        imagenLupaSearch = view.findViewById(R.id.imagen_search_fragment_busqueda);
        isLoading = false;
        adapterBusqueda = new AdapterFragmentBusqueda(getActivity(), this);
        linearLayoutManagerBusqueda = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,
                false);
        recyclerViewBusqueda = view.findViewById(R.id.id_recycler_busqueda);
        recyclerViewBusqueda.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        recyclerViewBusqueda.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoading) {
                    return;
                }
                int ultimaPosicion = linearLayoutManagerBusqueda.getItemCount();
                int posicionActual = linearLayoutManagerBusqueda.findLastVisibleItemPosition();

                if (ultimaPosicion - posicionActual <= CANTIDAD_ELEMENTOS_PARA_NUEVO_PEDIDO) {
                    obtenerBusqueda();
                }
            }
        });

        setAdapterLinear(recyclerViewBusqueda, linearLayoutManagerBusqueda, adapterBusqueda);
        controllerGlobalBusqueda = new ControllerGlobal(getActivity());

        //metodo para esconder el teclado al buscar en editTextBusqueda
        editTextBusqueda.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    obtenerBusqueda();
                    return true;
                }
                return false;
            }
        });

        imagenLupaSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerBusqueda();
                editTextBusqueda.requestFocus();
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTextBusqueda.getWindowToken(), 0);

            }
        });




        return view;
    }

    public void setAdapterLinear(RecyclerView recyclerView,
                                 LinearLayoutManager linearLayoutManager,
                                 RecyclerView.Adapter adapter) {

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


    private void obtenerBusqueda() {
        controllerGlobalBusqueda.obtenerBusquedaCancionesEditText(editTextBusqueda.getText().toString(), new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                adapterBusqueda.agregarCanciones(resultado);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificadorAActivityInicioDesdeFragmentBusqueda = (NotificadorAActivityInicioDesdeFragmentBusqueda) context;
    }

    @Override
    public void notificarCeldaClickeadaDeCancion(Cancion cancion) {

        notificadorAActivityInicioDesdeFragmentBusqueda.notificarCancion(cancion);
    }

    public interface NotificadorAActivityInicioDesdeFragmentBusqueda {

        public void notificarCancion(Cancion cancion);
    }
}
