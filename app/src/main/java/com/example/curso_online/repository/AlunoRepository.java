package com.example.curso_online.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.curso_online.dao.AlunoDao;
import com.example.curso_online.database.AppDatabase;
import com.example.curso_online.entities.Aluno;

import java.util.List;

public class AlunoRepository {
    private AlunoDao alunoDao;
    private LiveData<List<Aluno>> allAlunos;

    public AlunoRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        alunoDao = db.alunoDao();
        allAlunos = alunoDao.getAll();
    }

    public LiveData<List<Aluno>> getAllAlunos() {
        return allAlunos;
    }

    public void insert(Aluno aluno) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            alunoDao.insert(aluno);
        });
    }

    public void update(Aluno aluno) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            alunoDao.update(aluno);
        });
    }

    public void delete(Aluno aluno) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            alunoDao.delete(aluno);
        });
    }

    public void deleteAllAlunos() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            alunoDao.deleteAllAlunos();
        });
    }

    public LiveData<Aluno> getAlunoById(int alunoId) {
        return alunoDao.getAlunoById(alunoId);
    }

    public void deleteById(int alunoId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            alunoDao.deleteById(alunoId);
        });
    }
}
