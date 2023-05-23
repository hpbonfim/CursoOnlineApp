package com.example.curso_online.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;


import com.example.curso_online.entities.Curso;

import java.util.List;

@Dao
public interface CursoDao {
    @Insert
    void insert(Curso curso);

    @Update
    void update(Curso curso);

    @Delete
    void delete(Curso curso);

    @Query("DELETE FROM Curso")
    void deleteAllCursos();

    @Query("SELECT * FROM Curso ORDER BY qtdeHoras DESC")
    LiveData<List<Curso>> getAllCursos();
}
