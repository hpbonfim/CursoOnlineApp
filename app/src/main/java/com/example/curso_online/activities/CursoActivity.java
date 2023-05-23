package com.example.curso_online.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    private Button addCourseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        addCourseButton = findViewById(R.id.button_add_course);
        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CursoActivity.this, AddEditCursoActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CursoAdapter adapter = new CursoAdapter();
        recyclerView.setAdapter(adapter);


        cursoViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(CursoViewModel.class);

        cursoViewModel.getAllCursos().observe(this, new Observer<List<Curso>>() {
            @Override
            public void onChanged(List<Curso> cursos) {
                // update RecyclerView
                Toast.makeText(CursoActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
                adapter.setCursos(cursos);
            }
        });
    }
}
