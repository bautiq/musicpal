package com.example.user.musicpal.model.dao.room;

import android.arch.persistence.room.TypeConverter;

import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.model.pojo.ContCanciones;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CancionConverter {
    private Gson gson;

    public CancionConverter() {
        gson = new Gson();
    }

    @TypeConverter
    public List<Cancion> convertirStringACancion(String s) {
        Type collectionType = new TypeToken<Collection<Cancion>>() {
        }.getType();
        Collection<Cancion> canciones = gson.fromJson(s, collectionType);
        return (List<Cancion>) canciones;
        // ContCanciones contenedorCanciones = gson.fromJson(s, ContCanciones.class);
        //return contenedorCanciones.getCanciones();
    }


    @TypeConverter
    public String convertirCancionAString(List<Cancion> listCancion) {
        return gson.toJson(listCancion);
    }
}
