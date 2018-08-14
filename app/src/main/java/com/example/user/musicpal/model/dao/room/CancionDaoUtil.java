package com.example.user.musicpal.model.dao.room;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.musicpal.model.pojo.Cancion;
import com.example.user.musicpal.utils.ResultListener;

import java.util.List;

public class CancionDaoUtil {
    private Context context;
    private AppDatabase appDatabase;

    public CancionDaoUtil(Context context) {
        this.context = context;
        this.appDatabase = AppDatabase.getInstance(context);
    }


    public void insertarCanciones(List<Cancion> list) {
        InsertarListaCancionesTask insertarListaCancionesTask = new InsertarListaCancionesTask(list);
        insertarListaCancionesTask.execute();
    }

    public void obtenerTodasLasCanciones(ResultListener<List<Cancion>> resultListener) {
        ObtenerTodasLasCancionesTask obtenerTodasLasCancionesTask = new ObtenerTodasLasCancionesTask(resultListener);
        obtenerTodasLasCancionesTask.execute();
    }

    public void updateCanciones() {
    }

    public void obtenerCancionPorId(Cancion cancion, ResultListener<Cancion> resultListener) {
        ObtenerCancionPorIdTask obtenerCancionPorIdTask = new ObtenerCancionPorIdTask(cancion, resultListener);
        obtenerCancionPorIdTask.execute();
    }

    public void insertarCancion(Cancion cancion) {
        InsertarCancionTask insertarCancionTask = new InsertarCancionTask(cancion);
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

    private class InsertarCancionTask extends AsyncTask<Void, Void, Void> {
        private Cancion cancion;


        public InsertarCancionTask(Cancion cancion) {
            this.cancion = cancion;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            appDatabase.cancionDao().insertarCancion(cancion);
            return null;
        }


    }

    private class InsertarListaCancionesTask extends AsyncTask<Void, Void, Void> {
        private List<Cancion> lista;

        public InsertarListaCancionesTask(List<Cancion> cancionList) {
            this.lista = cancionList;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<Cancion> cancionesDB = appDatabase.cancionDao().getTodasLasCanciones();
            if (lista != null && lista.size() > 0) {
                if (cancionesDB == null || cancionesDB.size() == 0) {
                    appDatabase.cancionDao().insertarCanciones(lista);
                } else {
                    appDatabase.cancionDao().eliminarTodasLasCanciones();
                    appDatabase.cancionDao().insertarCanciones(lista);
                }
            }
            return null;
        }
    }

    private class ObtenerTodasLasCancionesTask  extends AsyncTask<Void, Void, List<Cancion>>{
        private ResultListener<List<Cancion>> resultListener;
        public ObtenerTodasLasCancionesTask(ResultListener<List<Cancion>> resultListener) {
            this.resultListener = resultListener;
        }


        @Override
        protected List<Cancion> doInBackground(Void... voids) {
            return appDatabase.cancionDao().getTodasLasCanciones();
        }

        @Override
        protected void onPostExecute(List<Cancion> cancionList) {
            resultListener.finish(cancionList);
        }
    }
}
