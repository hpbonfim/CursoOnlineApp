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

    @Insert
    void insert(Curso curso);

    @Update
    void update(Curso curso);

    @Delete
    void delete(Curso curso);
}
