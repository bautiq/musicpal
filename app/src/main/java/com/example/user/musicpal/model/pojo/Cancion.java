package com.example.user.musicpal.model.pojo;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DH on 15/5/2018.
 */

public class Cancion implements Serializable {

    private String title;
    @SerializedName("preview")
    private String urlPreview;

    public String getUrlPreview() {
        return urlPreview;
    }

    public Cancion(String title) {
        this.title = title;
    }

    // que es esto dani?
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
