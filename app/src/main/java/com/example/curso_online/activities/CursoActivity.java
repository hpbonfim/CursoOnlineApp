package com.example.curso_online.activities;

import static com.example.curso_online.activities.AddEditCursoActivity.EDIT_CURSO_REQUEST;

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
import com.example.curso_online.entities.Curso;
import com.example.curso_online.adapters.CursoAdapter;
import com.example.curso_online.viewmodel.CursoViewModel;

import java.util.List;

public class CursoActivity extends AppCompatActivity {
    private CursoViewModel cursoViewModel;
    private com.google.android.material.floatingactionbutton.FloatingActionButton addCourseButton;
    private com.google.android.material.floatingactionbutton.FloatingActionButton deleteAllCoursesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        addCourseButton = findViewById(R.id.button_add_course);
        deleteAllCoursesButton = findViewById(R.id.fab_delete_all_courses);

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CursoActivity.this, AddEditCursoActivity.class);
                startActivity(intent);
            }
        });

        deleteAllCoursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllCursos();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CursoAdapter adapter = new CursoAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CursoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Curso curso) {
                Intent intent = new Intent(CursoActivity.this, AddEditCursoActivity.class);
                intent.putExtra(AddEditCursoActivity.EXTRA_ID, curso.getCursoId());
                intent.putExtra(AddEditCursoActivity.EXTRA_NOME, curso.getNomeCurso());
                intent.putExtra(AddEditCursoActivity.EXTRA_HORAS, curso.getQtdeHoras());
                startActivityForResult(intent, EDIT_CURSO_REQUEST);
            }
        });

        adapter.setOnItemLongClickListener(new CursoAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(Curso curso) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CursoActivity.this);
                builder.setTitle(R.string.delete_course)
                        .setMessage(R.string.delete_course_message)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cursoViewModel.deleteById(curso.getCursoId());
                                Toast.makeText(CursoActivity.this, R.string.course_deleted, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
            }
        });

          cursoViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(CursoViewModel.class);

        cursoViewModel.getAllCursos().observe(this, new Observer<List<Curso>>() {
            @Override
            public void onChanged(List<Curso> cursos) {
                adapter.setCursos(cursos);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_CURSO_REQUEST) {
            if (resultCode == RESULT_OK) {
                int id = data.getIntExtra(AddEditCursoActivity.EXTRA_ID, -1);

                if (id != -1) {
                    String title = data.getStringExtra(AddEditCursoActivity.EXTRA_NOME);
                    int hours = data.getIntExtra(AddEditCursoActivity.EXTRA_HORAS, 1);

                    Curso curso = new Curso(title, hours);
                    curso.setCursoId(id);
                    cursoViewModel.update(curso);

                }
            }
        }
    }



    private void deleteAllCursos() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.delete_all_courses);
        builder.setMessage(R.string.delete_all_courses_message);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cursoViewModel.deleteAllCursos();
                Toast.makeText(CursoActivity.this, R.string.all_courses_deleted, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.no, null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
