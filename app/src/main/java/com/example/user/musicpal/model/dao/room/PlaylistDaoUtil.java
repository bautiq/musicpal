package com.example.user.musicpal.model.dao.room;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.musicpal.model.pojo.Playlist;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

public class PlaylistDaoUtil {
    private AppDatabase appDatabase;
    private Context context;

    public PlaylistDaoUtil(Context context) {
        this.appDatabase = AppDatabase.getInstance(context);
        this.context = context;
    }

    public void insertarPlaylists(List<Playlist> lista) {
        InsertarPlaylistTask insertarPlaylistTask = new InsertarPlaylistTask(lista);
        insertarPlaylistTask.execute();
    }

    public void obtenerPlaylists(ResultListener<List<Playlist>> resultListenerController) {
        ObtenerPlaylistsTask obtenerPlaylistsTask = new ObtenerPlaylistsTask(resultListenerController);
        obtenerPlaylistsTask.execute();
    }

    private class InsertarPlaylistTask extends AsyncTask<Void, Void, Void> {
        private List<Playlist> playlistList;

        public InsertarPlaylistTask(List<Playlist> lista) {
            playlistList = lista;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<Playlist> listaDb = appDatabase.playlistDao().obtenerPlaylists();
            if (playlistList != null && playlistList.size() > 0) {
                if (listaDb == null || listaDb.size() == 0) {
                    appDatabase.playlistDao().insertarListaPlaylists(playlistList);
                } else {
                    appDatabase.playlistDao().eliminarTodasLasPlaylists();
                    appDatabase.playlistDao().insertarListaPlaylists(playlistList);
                }
            }
            return null;
        }
    }


    private class ObtenerPlaylistsTask extends AsyncTask<Void, Void, List<Playlist>>{
        private ResultListener<List<Playlist>> resultlistener;

        public ObtenerPlaylistsTask(ResultListener<List<Playlist>> resultListenerController) {
            this.resultlistener = resultListenerController;
        }

        @Override
        protected List<Playlist> doInBackground(Void... voids) {
            return appDatabase.playlistDao().obtenerPlaylists();
        }

        @Override
        protected void onPostExecute(List<Playlist> list) {
            resultlistener.finish(list);
        }
    }
}
