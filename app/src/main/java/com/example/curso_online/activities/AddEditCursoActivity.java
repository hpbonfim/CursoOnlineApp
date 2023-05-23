package com.example.curso_online.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.curso_online.R;
import com.example.curso_online.entities.Curso;
import com.example.curso_online.viewmodels.CursoViewModel;


public class AddEditCursoActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "com.example.curso_online.EXTRA_TITLE";
    public static final String EXTRA_PRIORITY = "com.example.curso_online.EXTRA_PRIORITY";
    public static final String EXTRA_CURSO_ID = "com.example.curso_online.EXTRA_CURSO_ID";
    private CursoViewModel cursoViewModel;
    private EditText editTextTitle;
    private NumberPicker numberPickerPriority;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_curso);

        editTextTitle = findViewById(R.id.edit_text_title);
        numberPickerPriority = findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(100);

        cursoViewModel = new ViewModelProvider(this).get(CursoViewModel.class);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        int priority = getIntent().getIntExtra(EXTRA_PRIORITY, 1);

        if (title != null) {
            editTextTitle.setText(title);
            numberPickerPriority.setValue(priority);
        }
    }

    public void saveCurso(View v) {
        String title = editTextTitle.getText().toString();
        int priority = numberPickerPriority.getValue();

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Por favor, insira um título.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (getIntent().hasExtra(EXTRA_CURSO_ID)) {
            int cursoId = getIntent().getIntExtra(EXTRA_CURSO_ID, -1);
            if (cursoId != -1) {
                Curso cursoExistente = new Curso(title, priority);
                cursoExistente.setCursoId(cursoId);
                cursoViewModel.update(cursoExistente);
            }
        } else {
            Curso novoCurso = new Curso(title, priority);
            cursoViewModel.insert(novoCurso);
        }

        Toast.makeText(this, "Curso salvo com sucesso.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
