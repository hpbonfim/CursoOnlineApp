package com.example.curso_online.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.curso_online.dao.CursoDao;
import com.example.curso_online.database.AppDatabase;
import com.example.curso_online.entities.Curso;

import java.util.List;

public class CursoRepository {
    private CursoDao cursoDao;
    private LiveData<List<Curso>> allCursos;

    public CursoRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        cursoDao = database.cursoDao();
        allCursos = cursoDao.getAllCursos();
    }

    public void insert(Curso curso) {
        new InsertCursoAsyncTask(cursoDao).execute(curso);
    }

    public void update(Curso curso) {
        new UpdateCursoAsyncTask(cursoDao).execute(curso);
    }

    public void delete(Curso curso) {
        new DeleteCursoAsyncTask(cursoDao).execute(curso);
    }

    public void deleteAllCursos() {
        new DeleteAllCursosAsyncTask(cursoDao).execute();
    }

    public LiveData<List<Curso>> getAllCursos() {
        return allCursos;
    }

    private static class InsertCursoAsyncTask extends AsyncTask<Curso, Void, Void> {
        private CursoDao cursoDao;

        private InsertCursoAsyncTask(CursoDao cursoDao) {
            this.cursoDao = cursoDao;
        }

        @Override
        protected Void doInBackground(Curso... cursos) {
            cursoDao.insert(cursos[0]);
            return null;
        }
    }

    private static class UpdateCursoAsyncTask extends AsyncTask<Curso, Void, Void> {
        private CursoDao cursoDao;

        private UpdateCursoAsyncTask(CursoDao cursoDao) {
            this.cursoDao = cursoDao;
        }

        @Override
        protected Void doInBackground(Curso... cursos) {
            cursoDao.update(cursos[0]);
            return null;
        }
    }

    private static class DeleteCursoAsyncTask extends AsyncTask<Curso, Void, Void> {
        private CursoDao cursoDao;

        private DeleteCursoAsyncTask(CursoDao cursoDao) {
            this.cursoDao = cursoDao;
        }

        @Override
        protected Void doInBackground(Curso... cursos) {
            cursoDao.delete(cursos[0]);
            return null;
        }
    }

    private static class DeleteAllCursosAsyncTask extends AsyncTask<Void, Void, Void> {
        private CursoDao cursoDao;

        private DeleteAllCursosAsyncTask(CursoDao cursoDao) {
            this.cursoDao = cursoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cursoDao.deleteAllCursos();
            return null;
        }
    }
}
