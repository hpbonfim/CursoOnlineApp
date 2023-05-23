package com.example.curso_online.dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.curso_online.entities.Curso;

import java.util.List;

@Dao
public interface CursoDao {
    @Query("SELECT * FROM Curso")
    LiveData<List<Curso>> getAll();

    @Query("SELECT * FROM Curso WHERE cursoId = :id")
    LiveData<Curso> getCursoById(int id);

    @Insert
    void insert(Curso curso);

    @Update
    void update(Curso curso);

    @Delete
    void delete(Curso curso);

    @Query("DELETE FROM Curso")
    void deleteAllCursos();

    @Query("DELETE FROM Curso WHERE cursoId = :cursoId")
    void deleteById(int cursoId);
}
