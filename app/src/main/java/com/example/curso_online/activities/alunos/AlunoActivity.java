package com.example.curso_online.activities.alunos;

import static com.example.curso_online.activities.alunos.AddEditAlunoActivity.EDIT_ALUNO_REQUEST;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.curso_online.R;
import com.example.curso_online.entities.Aluno;
import com.example.curso_online.entities.Curso;
import com.example.curso_online.viewmodel.AlunoViewModel;
import com.example.curso_online.viewmodel.CursoViewModel;

import java.util.List;

public class AlunoActivity extends AppCompatActivity {
    private AlunoViewModel alunoViewModel;
    private CursoViewModel cursoViewModel;

    private com.google.android.material.floatingactionbutton.FloatingActionButton addAlunoButton;
    private com.google.android.material.floatingactionbutton.FloatingActionButton deleteAllAlunosButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        addAlunoButton = findViewById(R.id.button_add_student);
        deleteAllAlunosButton = findViewById(R.id.fab_delete_all_students);

        addAlunoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlunoActivity.this, AddEditAlunoActivity.class);
                startActivity(intent);
            }
        });

        deleteAllAlunosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllAlunos();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final AlunoAdapter adapter = new AlunoAdapter(new ViewModelProvider(this).get(CursoViewModel.class), this);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AlunoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Aluno aluno) {
                Intent intent = new Intent(AlunoActivity.this, AddEditAlunoActivity.class);
                intent.putExtra(AddEditAlunoActivity.EXTRA_ID, aluno.getAlunoId());
                intent.putExtra(AddEditAlunoActivity.EXTRA_NOME, aluno.getNomeAluno());

                cursoViewModel.getCursoById(aluno.getCursoId()).observe(AlunoActivity.this, new Observer<Curso>() {
                    @Override
                    public void onChanged(Curso curso) {
                        if (curso != null) {
                            intent.putExtra(AddEditAlunoActivity.EXTRA_CURSO, curso.getNomeCurso());
                        } else {
                            intent.putExtra(AddEditAlunoActivity.EXTRA_CURSO, "");
                        }

                        intent.putExtra(AddEditAlunoActivity.EXTRA_EMAIL, aluno.getEmailAluno());
                        intent.putExtra(AddEditAlunoActivity.EXTRA_TELEFONE, aluno.getTelefoneAluno());
                        startActivityForResult(intent, EDIT_ALUNO_REQUEST);
                    }
                });
            }
        });

        adapter.setOnItemLongClickListener(new AlunoAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(Aluno aluno) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlunoActivity.this);
                builder.setTitle(R.string.delete_student)
                        .setMessage(R.string.delete_student_message)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alunoViewModel.deleteById(aluno.getAlunoId());
                                Toast.makeText(AlunoActivity.this, R.string.student_deleted, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
            }
        });

        alunoViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(AlunoViewModel.class);
        cursoViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(CursoViewModel.class);

        alunoViewModel.getAllAlunos().observe(this, new Observer<List<Aluno>>() {
            @Override
            public void onChanged(List<Aluno> alunos) {
                adapter.setAlunos(alunos);
            }
        });

    }

    private void deleteAllAlunos() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.delete_all_students);
        builder.setMessage(R.string.delete_all_students_message);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alunoViewModel.deleteAllAlunos();
                Toast.makeText(AlunoActivity.this, R.string.all_students_deleted, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.no, null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
