package com.example.curso_online.activities;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.curso_online.R;
import com.example.curso_online.entities.Curso;
import com.example.curso_online.viewmodel.CursoViewModel;

public class AddEditCursoActivity extends AppCompatActivity {

    private EditText editTextNome, editTextHoras;
    private Button buttonSave;
    private CursoViewModel cursoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_curso);

        editTextNome = findViewById(R.id.edit_text_title);
        editTextHoras = findViewById(R.id.edit_text_hours);
        buttonSave = findViewById(R.id.button_save);

        cursoViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(CursoViewModel.class);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCurso();
            }
        });
    }

    private void saveCurso() {
        String nomeCurso = editTextNome.getText().toString();
        int qtdeHoras = Integer.parseInt(editTextHoras.getText().toString());

        if (nomeCurso.trim().isEmpty() || qtdeHoras == 0) {
            Toast.makeText(this, "Please insert a name and hours", Toast.LENGTH_SHORT).show();
            return;
        }

        Curso curso = new Curso(nomeCurso, qtdeHoras);
        cursoViewModel.insert(curso);

        Toast.makeText(this, "Course saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}
