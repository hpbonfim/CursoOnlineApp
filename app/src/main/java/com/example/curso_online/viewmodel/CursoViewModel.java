package com.example.curso_online.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.curso_online.entities.Curso;
import com.example.curso_online.repository.CursoRepository;

import java.util.List;

public class CursoViewModel extends AndroidViewModel {
    private CursoRepository repository;
    private LiveData<List<Curso>> allCursos;

    public CursoViewModel(Application application) {
        super(application);
        repository = new CursoRepository(application);
        allCursos = repository.getAllCursos();
    }

    public LiveData<List<Curso>> getAllCursos() {
        return allCursos;
    }

    public void insert(Curso curso) {
        repository.insert(curso);
    }

    public void update(Curso curso) {
        repository.update(curso);
    }

    public void deleteAllCursos() {
        repository.deleteAllCursos();
    }

    public LiveData<Curso> getCursoById(int cursoId) {
        return repository.getCursoById(cursoId);
    }

    public LiveData<Curso> getCursoByName(String cursoName) {
        return repository.getCursoByName(cursoName);
    }


    public void deleteById(int cursoId) {
        repository.deleteById(cursoId);
    }
}
