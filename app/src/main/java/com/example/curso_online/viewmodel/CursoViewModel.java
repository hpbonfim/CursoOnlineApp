package com.example.curso_online.viewmodel;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.curso_online.entities.Curso;
import com.example.curso_online.repository.CursoRepository;

import java.util.List;

public class CursoViewModel extends AndroidViewModel {
    private CursoRepository mRepository;
    private LiveData<List<Curso>> mAllCursos;

    public CursoViewModel (Application application) {
        super(application);
        mRepository = new CursoRepository(application);
        mAllCursos = mRepository.getAllCursos();
    }

    public LiveData<List<Curso>> getAllCursos() {
        return mAllCursos;
    }

    public void insert(Curso curso) {
        mRepository.insert(curso);
    }

    public void update(Curso curso) {
        mRepository.update(curso);
    }

    public void delete(Curso curso) {
        mRepository.delete(curso);
    }
}
