package com.example.curso_online.repository;
import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.curso_online.dao.CursoDao;
import com.example.curso_online.database.AppDatabase;
import com.example.curso_online.entities.Curso;

import java.util.List;

public class CursoRepository {
    private CursoDao mCursoDao;
    private LiveData<List<Curso>> mAllCursos;


    public CursoRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mCursoDao = db.cursoDao();
        mAllCursos = mCursoDao.getAll();
    }

    public LiveData<List<Curso>> getAllCursos() {
        return mAllCursos;
    }

    public void insert(Curso curso) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mCursoDao.insert(curso);
        });
    }

    public void update(Curso curso) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mCursoDao.update(curso);
        });
    }

    public void delete(Curso curso) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mCursoDao.delete(curso);
        });
    }
}
