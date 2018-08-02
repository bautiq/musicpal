package com.example.user.musicpal.model.dao.room;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;

import com.example.user.musicpal.model.pojo.Album;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

public class AlbumDaoUtil {
    private Context context;
    private AppDatabase appDatabase;

    public AlbumDaoUtil(Context context) {
        this.context = context;
        appDatabase = AppDatabase.getInstance(context);
    }

    public void insertarListaAlbumes(List<Album> list) {
        InsertarListaAlbumsTask insertarListaAlbumsTask = new InsertarListaAlbumsTask(list);
        insertarListaAlbumsTask.execute();
    }

    public void obtenerListaAlbumes(final ResultListener<List<Album>> resultListenerController){
        ObtenerListaAlbumsTask obtenerListaAlbumsTask = new ObtenerListaAlbumsTask(resultListenerController);
        obtenerListaAlbumsTask.execute();
    }

    private class ObtenerListaAlbumsTask extends AsyncTask<Void, Void, List<Album>> {
        private ResultListener<List<Album>> resultListener;

        public ObtenerListaAlbumsTask(ResultListener<List<Album>> resultListener) {
            this.resultListener = resultListener;
        }

        @Override
        protected List<Album> doInBackground(Void... voids) {
            return appDatabase.albumDao().getListaAlbumes();
        }

        @Override
        protected void onPostExecute(List<Album> aVoid) {
            resultListener.finish(aVoid);
        }
    }

    private class InsertarListaAlbumsTask extends AsyncTask<Void, Void, Void> {

        private List<Album> albumList;

        public InsertarListaAlbumsTask(List<Album> list) {
            albumList = list;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<Album> albums = appDatabase.albumDao().getListaAlbumes();
            if(albums == null || albums.size() == 0){
                appDatabase.albumDao().insertarListaAlbumes(albumList);
            }else{
                appDatabase.albumDao().updateListaAlbumes(albumList);
            }
            return null;
        }
    }

}
