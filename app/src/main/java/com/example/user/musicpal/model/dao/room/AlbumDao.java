package com.example.user.musicpal.model.dao.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.user.musicpal.model.pojo.Album;

import java.util.List;

@Dao
public interface AlbumDao {

    @Query("select * from album")
    List<Album> getListaAlbumes();

    @Insert
    void insertarListaAlbumes(List<Album> albumList);

    @Query("delete from album")
    void eliminarAlbumes();
}
