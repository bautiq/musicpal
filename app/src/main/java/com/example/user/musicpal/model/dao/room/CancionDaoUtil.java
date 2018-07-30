package com.example.user.musicpal.model.dao.room;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.utils.ResultListener;

public class CancionDaoUtil {
    private Context context;
    private AppDatabase appDatabase;

    public CancionDaoUtil(Context context) {
        this.context = context;
        this.appDatabase = AppDatabase.getInstance(context);
    }


    public void insertarCanciones() {
    }

    public void obtenerTodasLasCanciones() {
    }

    public void updateCanciones() {
    }

    public void obtenerCancionPorId(Cancion cancion, ResultListener<Cancion> resultListener) {
        ObtenerCancionPorIdTask obtenerCancionPorIdTask = new ObtenerCancionPorIdTask(cancion, resultListener);
        obtenerCancionPorIdTask.execute();
    }

    public void insertarCancion(Cancion cancion, ResultListener<Long> resultListener) {
        InsertarCancionTask insertarCancionTask = new InsertarCancionTask(cancion,resultListener);
        insertarCancionTask.execute();

    }

    private class ObtenerCancionPorIdTask extends AsyncTask<Void, Void, Cancion> {
        private Cancion cancion;
        private ResultListener<Cancion> resultListener;

        public ObtenerCancionPorIdTask(Cancion id, ResultListener<Cancion> listenerController) {
            cancion = id;
            this.resultListener = listenerController;
        }

        @Override
        protected Cancion doInBackground(Void... voids) {
            String id = cancion.getId();
            return appDatabase.cancionDao().getCancionPorId(id);
        }

        @Override
        protected void onPostExecute(Cancion cancion) {
            resultListener.finish(cancion);

        }
    }

    private class InsertarCancionTask extends AsyncTask<Void, Void, Long> {
        private Cancion cancion;
        private ResultListener<Long> resultListener;

        public InsertarCancionTask(Cancion cancion, ResultListener<Long> resultListener) {
            this.cancion = cancion;
            this.resultListener = resultListener;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return appDatabase.cancionDao().insertarCancion(cancion);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            resultListener.finish(aLong);
        }
    }
}
