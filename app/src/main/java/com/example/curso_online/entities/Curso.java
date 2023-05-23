package com.example.curso_online.entities;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Curso")
public class Curso {
    @PrimaryKey(autoGenerate = true)
    private int cursoId;

    private String nomeCurso;

    private int qtdeHoras;

    // Construtor, getters e setters
    public Curso(String nomeCurso, int qtdeHoras) {
        this.nomeCurso = nomeCurso;
        this.qtdeHoras = qtdeHoras;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public int getQtdeHoras() {
        return qtdeHoras;
    }

    public void setQtdeHoras(int qtdeHoras) {
        this.qtdeHoras = qtdeHoras;
    }
}
