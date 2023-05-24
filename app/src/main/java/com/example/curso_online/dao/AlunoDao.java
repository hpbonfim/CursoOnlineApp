package com.example.curso_online.dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.curso_online.entities.Aluno;

import java.util.List;

@Dao
public interface AlunoDao {
    @Query("SELECT * FROM Aluno")
    LiveData<List<Aluno>> getAll();

    @Query("SELECT * FROM Aluno WHERE alunoId = :id")
    LiveData<Aluno> getAlunoById(int id);

    @Insert
    void insert(Aluno aluno);

    @Update
    void update(Aluno aluno);

    @Delete
    void delete(Aluno aluno);

    @Query("DELETE FROM Aluno")
    void deleteAllAlunos();

    @Query("DELETE FROM Aluno WHERE alunoId = :alunoId")
    void deleteById(int alunoId);
}
