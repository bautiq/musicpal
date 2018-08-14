package com.example.user.musicpal.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.user.musicpal.model.dao.room.CancionDaoUtil;
import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

public class RoomControllerCancion {
    //Este controller va a hacer el trabajo de insertar, buscar, eliminar y updatear la AppDatabase
    //Atraves del utils y sus asynctasks

    private Context context;
    private CancionDaoUtil cancionDaoUtil;

    public RoomControllerCancion(Context context) {
        this.context = context;
        cancionDaoUtil = new CancionDaoUtil(context);

    }

    public void insertarCanciones(List<Cancion> list){
        cancionDaoUtil.insertarCanciones(list);
    }

    public void obtenerCanciones(final ResultListener<List<Cancion>> resultListenerVista){
        if(!(hayInternet())){
            cancionDaoUtil.obtenerTodasLasCanciones(new ResultListener<List<Cancion>>() {
                @Override
                public void finish(List<Cancion> resultado) {
                    resultListenerVista.finish(resultado);
                }
            });
        }else{
            resultListenerVista.finish(null);
        }

    }

    public void InsertarUobtenerCancionPorId(Cancion cancion, final ResultListener<Cancion> resultListenerVista) {
       if(hayInternet()){
           cancionDaoUtil.insertarCancion(cancion);
       }else{
           cancionDaoUtil.obtenerCancionPorId(cancion, new ResultListener<Cancion>() {
               @Override
               public void finish(Cancion resultado) {
                   resultListenerVista.finish(resultado);
               }
           });
       }


    }

    public void insertarCancion(Cancion cancion, final ResultListener<Long> resultListenerVista) {

    }

    private boolean hayInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
