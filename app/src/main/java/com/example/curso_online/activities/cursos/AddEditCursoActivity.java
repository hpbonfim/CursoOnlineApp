package com.example.curso_online.activities.cursos;

import android.content.Intent;
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

    public static final String EXTRA_ID =
            "com.example.curso_online.EXTRA_ID";
    public static final String EXTRA_NOME =
            "com.example.curso_online.EXTRA_NOME";
    public static final String EXTRA_HORAS =
            "com.example.curso_online.EXTRA_HORAS";
    public static final int EDIT_CURSO_REQUEST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_curso);

        editTextNome = findViewById(R.id.edit_text_title);
        editTextHoras = findViewById(R.id.edit_text_hours);
        buttonSave = findViewById(R.id.button_save);

        cursoViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(CursoViewModel.class);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle(R.string.edit_course);
            editTextNome.setText(intent.getStringExtra(EXTRA_NOME));
            editTextHoras.setText(String.valueOf(intent.getIntExtra(EXTRA_HORAS, 0)));
        } else {
            setTitle(R.string.add_course);
        }

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
            Toast.makeText(this, R.string.please_insert, Toast.LENGTH_SHORT).show();
            return;
        }

        Curso curso = new Curso(nomeCurso, qtdeHoras);

        Intent data = new Intent();
        data.putExtra(EXTRA_NOME, nomeCurso);
        data.putExtra(EXTRA_HORAS, qtdeHoras);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            curso.setCursoId(id);
            cursoViewModel.update(curso);
            data.putExtra(EXTRA_ID, id);
            Toast.makeText(this, R.string.course_updated, Toast.LENGTH_SHORT).show();
        } else {
            cursoViewModel.insert(curso);
            Toast.makeText(this, R.string.course_saved, Toast.LENGTH_SHORT).show();
        }

        setResult(RESULT_OK, data);
        finish();
    }

}
