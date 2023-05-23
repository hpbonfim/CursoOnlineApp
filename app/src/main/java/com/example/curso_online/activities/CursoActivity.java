package com.example.curso_online.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.curso_online.R;
import com.example.curso_online.adapters.CursoAdapter;
import com.example.curso_online.entities.Curso;
import com.example.curso_online.viewmodels.CursoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CursoActivity extends AppCompatActivity {
    public static final int ADD_CURSO_REQUEST = 1;
    private CursoViewModel cursoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CursoAdapter adapter = new CursoAdapter();
        recyclerView.setAdapter(adapter);

        cursoViewModel = new ViewModelProvider(this).get(CursoViewModel.class);
        cursoViewModel.getAllCursos().observe(this, adapter::submitList);

        FloatingActionButton buttonAddCurso = findViewById(R.id.button_add_curso);
        buttonAddCurso.setOnClickListener(v -> {
            Intent intent = new Intent(CursoActivity.this, AddEditCursoActivity.class);
            startActivityForResult(intent, ADD_CURSO_REQUEST);
        });

        adapter.setOnItemClickListener(curso -> {
            Intent intent = new Intent(CursoActivity.this, AddEditCursoActivity.class);
            intent.putExtra(AddEditCursoActivity.EXTRA_TITLE, curso.getNomeCurso());
            intent.putExtra(AddEditCursoActivity.EXTRA_PRIORITY, curso.getQtdeHoras());
            intent.putExtra(CursoAdapter.EXTRA_CURSO_ID, curso.getCursoId());
            startActivityForResult(intent, ADD_CURSO_REQUEST);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.delete_all_cursos) {
            cursoViewModel.deleteAllCursos();
            Toast.makeText(this, "Todos os cursos foram deletados", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_CURSO_REQUEST) {
            if (resultCode == RESULT_OK) {
                String title = data.getStringExtra(AddEditCursoActivity.EXTRA_TITLE);
                int priority = data.getIntExtra(AddEditCursoActivity.EXTRA_PRIORITY, 1);

                Curso curso = new Curso(title, priority);
                cursoViewModel.insert(curso);

                Toast.makeText(this, "Curso salvo", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
