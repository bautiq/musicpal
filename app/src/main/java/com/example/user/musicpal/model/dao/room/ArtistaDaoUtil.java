package com.example.user.musicpal.model.dao.room;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.musicpal.model.pojo.Artista;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

public class ArtistaDaoUtil {
    private AppDatabase appDatabase;
    private Context context;

    public ArtistaDaoUtil(Context context) {
        this.appDatabase = AppDatabase.getInstance(context);
        this.context = context;
    }

    public void insertarArtistas(List<Artista> list){
        InsertarArtistasTask insertarArtistasTask = new InsertarArtistasTask(list);
        insertarArtistasTask.execute();
    }

    public void obtenerArtistas(ResultListener<List<Artista>> resultListenerController){
        ObtenerArtistasTask obtenerArtistasTask = new ObtenerArtistasTask(resultListenerController);
        obtenerArtistasTask.execute();
    }

    private class InsertarArtistasTask extends AsyncTask<Void, Void, Void> {
        private List<Artista> artistaList;

        public InsertarArtistasTask(List<Artista> artistaList) {
            this.artistaList = artistaList;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<Artista> listaDelaDB = appDatabase.artistaDao().getTodosLosArtistas();
            if (artistaList != null || artistaList.size() > 0) {
                if (listaDelaDB == null || listaDelaDB.size() == 0) {
                    appDatabase.artistaDao().insertarArtistas(artistaList);
                } else {
                    appDatabase.artistaDao().eliminarArtitas();
                    appDatabase.artistaDao().insertarArtistas(artistaList);

                }

            }
            return null;
        }
    }

    private class ObtenerArtistasTask extends AsyncTask<Void, Void, List<Artista>> {
        private ResultListener<List<Artista>> resultListener;

        public ObtenerArtistasTask(ResultListener<List<Artista>> resultListener) {
            this.resultListener = resultListener;
        }

        @Override
        protected List<Artista> doInBackground(Void... voids) {
            return appDatabase.artistaDao().getTodosLosArtistas();
        }

        @Override
        protected void onPostExecute(List<Artista> artistas) {
            resultListener.finish(artistas);
        }
    }
}
