package com.example.curso_online.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.curso_online.dao.CursoDao;
import com.example.curso_online.database.AppDatabase;
import com.example.curso_online.entities.Curso;

import java.util.List;

public class CursoRepository {
    private CursoDao cursoDao;
    private LiveData<List<Curso>> allCursos;

    public CursoRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        cursoDao = db.cursoDao();
        allCursos = cursoDao.getAll();
    }

    public LiveData<List<Curso>> getAllCursos() {
        return allCursos;
    }

    public void insert(Curso curso) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            cursoDao.insert(curso);
        });
    }

    public void update(Curso curso) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            cursoDao.update(curso);
        });
    }

    public void delete(Curso curso) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            cursoDao.delete(curso);
        });
    }

    public void deleteAllCursos() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            cursoDao.deleteAllCursos();
        });
    }

    public LiveData<Curso> getCursoById(int cursoId) {
        return cursoDao.getCursoById(cursoId);
    }

    public void deleteById(int cursoId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            cursoDao.deleteById(cursoId);
        });
    }
}
