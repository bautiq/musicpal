package com.example.user.musicpal.model.dao.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.user.musicpal.model.pojo.Artista;

import java.util.List;

@Dao
public interface ArtistaDao {

    @Query("select * from artista")
    List<Artista> getTodosLosArtistas();

    @Insert
    void insertarArtistas(List<Artista> lista);

    @Query("delete from artista")
    void eliminarArtitas();
}
