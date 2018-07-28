package com.example.user.musicpal.model.dao.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.user.musicpal.model.pojo.Cancion;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.FAIL;
import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface CancionDao {

    @Query("select * from cancion")
    List<Cancion> getTodasLasCanciones();

    @Query("select * from cancion where id = :id")
    Cancion getCancionPorId(String id);

    @Insert(onConflict = FAIL)
    Long insertarCancion(Cancion cancion);

    @Insert
    Long[] insertarCanciones(List<Cancion> cancionList);
}
