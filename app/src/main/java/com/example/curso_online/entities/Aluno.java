package com.example.curso_online.entities;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Aluno", foreignKeys = @ForeignKey(entity = Curso.class, parentColumns = "cursoId", childColumns = "cursoId", onDelete = ForeignKey.CASCADE))
public class Aluno {
    @PrimaryKey(autoGenerate = true)
    private int alunoId;
    private int cursoId;
    private String nomeAluno;
    private String emailAluno;
    private String telefoneAluno;

    public Aluno(int cursoId, String nomeAluno, String emailAluno, String telefoneAluno) {
        this.cursoId = cursoId;
        this.nomeAluno = nomeAluno;
        this.emailAluno = emailAluno;
        this.telefoneAluno = telefoneAluno;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

     public String getEmailAluno() {
        return emailAluno;
    }

    public String getTelefoneAluno() {
        return telefoneAluno;
    }
}
