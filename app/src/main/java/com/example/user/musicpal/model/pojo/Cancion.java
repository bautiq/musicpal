package com.example.user.musicpal.model.pojo;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Created by DH on 15/5/2018.
 */

public class Cancion implements Serializable {
    private String title;

    public Cancion(String title) {
        this.title = title;
    }

    protected Cancion(Parcel in) {
        title = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
