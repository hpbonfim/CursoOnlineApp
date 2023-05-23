package com.example.curso_online.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.curso_online.entities.Curso;
import com.example.curso_online.repository.CursoRepository;
import java.util.List;

public class CursoViewModel extends AndroidViewModel {
    private CursoRepository repository;
    private LiveData<List<Curso>> allCursos;

    public CursoViewModel(@NonNull Application application) {
        super(application);
        repository = new CursoRepository(application);
        allCursos = repository.getAllCursos();
    }

    public void insert(Curso curso) {
        repository.insert(curso);
    }

    public void update(Curso curso) {
        repository.update(curso);
    }

    public void delete(Curso curso) {
        repository.delete(curso);
    }

    public void deleteAllCursos() {
        repository.deleteAllCursos();
    }

    public LiveData<List<Curso>> getAllCursos() {
        return allCursos;
    }
}
