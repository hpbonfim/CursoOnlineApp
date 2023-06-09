package com.example.curso_online.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;

import com.example.curso_online.dao.AlunoDao;
import com.example.curso_online.dao.CursoDao;
import com.example.curso_online.entities.Aluno;
import com.example.curso_online.entities.Curso;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {Curso.class, Aluno.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public static final Executor databaseWriteExecutor = Executors.newSingleThreadExecutor();
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "CursosOnline")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract CursoDao cursoDao();
    public abstract AlunoDao alunoDao();

}
