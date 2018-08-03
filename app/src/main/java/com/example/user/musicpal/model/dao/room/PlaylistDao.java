package com.example.user.musicpal.model.dao.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.user.musicpal.model.pojo.Playlist;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.FAIL;

@Dao
public interface PlaylistDao {
    @Query("select * from playlist")
    List<Playlist> obtenerPlaylists();

    @Query("delete from playlist")
    void eliminarTodasLasPlaylists();

    @Insert(onConflict = FAIL)
    void insertarListaPlaylists(List<Playlist> list);
}
