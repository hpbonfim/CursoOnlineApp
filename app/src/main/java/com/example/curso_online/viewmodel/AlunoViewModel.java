package com.example.curso_online.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.curso_online.entities.Aluno;
import com.example.curso_online.repository.AlunoRepository;

import java.util.List;

public class AlunoViewModel extends AndroidViewModel {
    private AlunoRepository repository;
    private LiveData<List<Aluno>> allAlunos;

    public AlunoViewModel(Application application) {
        super(application);
        repository = new AlunoRepository(application);
        allAlunos = repository.getAllAlunos();
    }

    public LiveData<List<Aluno>> getAllAlunos() {
        return allAlunos;
    }

    public void insert(Aluno aluno) {
        repository.insert(aluno);
    }

    public void update(Aluno aluno) {
        repository.update(aluno);
    }

    public void delete(Aluno aluno) {
        repository.delete(aluno);
    }

    public void deleteAllAlunos() {
        repository.deleteAllAlunos();
    }

    public LiveData<Aluno> getAlunoById(int alunoId) {
        return repository.getAlunoById(alunoId);
    }

    public void deleteById(int alunoId) {
        repository.deleteById(alunoId);
    }
}
